package net.consensys.gpact.lockablestorage.test;

import org.junit.Test;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;

import java.math.BigInteger;

public class LockableStorageLockingUint256Test extends AbstractLockableStorageTest {


  // By default, locking is off. As such, setting a uint256 will not encounter any locks.
  @Test
  public void singleBlockchainNotLocked() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;

    this.storageWrapper.test_setUint256(theUint, BigInteger.ONE).send();
  }

  // By default, locking is off. As such, setting a uint256 as part of a cross-blockchain
  // call will not encounter any locks.
  @Test
  public void crossBlockchainNotLocked() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;
    BigInteger val = BigInteger.TEN;

    // Any non-zero Root Blockchain Id is deemed to indicate an active Cross-Blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ONE).send();
    // The mock CrossBlockchainControlContract should now indicate a cross-blockchain call.
    assert(!this.mockCrossBlockchainControlContract.isSingleBlockchainCall().send());

    this.storageWrapper.test_setUint256(theUint, val).send();

    // The contract should now be locked.
    assert(this.lockableStorageContract.locked().send());

    // Even when the value is in provisional storage, should be able to return the value.
    assert(this.storageWrapper.test_getUint256(theUint).send().compareTo(val) == 0);

  }

  // An exception is thrown if a locked contract is to be written to by a single blockchain call.
  @Test
  public void singleBlockchainLocked() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;

    // Any non-zero Root Blockchain Id is deemed to indicate an active Cross-Blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ONE).send();

    this.storageWrapper.test_setUint256(theUint, BigInteger.ONE).send();

    // Zero Root Blockchain Id is deemed to indicate an no active Cross-Blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ZERO).send();

    // This will fail as the contract is locked.
    try {
      this.storageWrapper.test_setUint256(theUint, BigInteger.ONE).send();
      throw new Exception("Unexpectedly, no revert thrown");
    } catch (TransactionException ex) {
      System.out.println("Exception thrown as expected: " + ex.getTransactionReceipt().get().getRevertReason());
    }
  }

  // An exception is thrown if a locked contract is to be written to by a single blockchain call.
  @Test
  public void crossBlockchainDifferentCall() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;

    assert(!this.lockableStorageContract.locked().send());
    // Ensure the lockable storage contract perceives it is in the midst of a cross-blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ONE).send();
    // Set a variable. This will lock the contract.
    this.storageWrapper.test_setUint256(theUint, BigInteger.ONE).send();
    assert(this.lockableStorageContract.locked().send());

    // This will work because contract thinks it is in the same segment.
    this.storageWrapper.test_setUint256(theUint, BigInteger.TWO).send();
    assert(this.storageWrapper.test_getUint256(theUint).send().compareTo(BigInteger.TWO) == 0);

    // Simulate the end of this cross-blockchain call by clearing the list of locked contracts.
    this.mockCrossBlockchainControlContract.clearListOfLockedContracts().send();

    // Attempt to set a value in the lockable storage contract. This should fail because the contract
    // is locked, and the cross-blockchain control contract will indicate that the contract was not
    // locked by this cross-blockchain segment.
    try {
      this.storageWrapper.test_setUint256(theUint, BigInteger.ONE).send();
      throw new Exception("Unexpectedly, no revert thrown");
    } catch (TransactionException ex) {
      System.out.println("Exception thrown as expected: " + ex.getTransactionReceipt().get().getRevertReason());
    }

    // This will fail as the contract is locked.
    try {
      this.storageWrapper.test_getUint256(theUint).send();
      throw new Exception("Unexpectedly, no revert thrown");
    } catch (ContractCallException ex) {
      // thrown as expected
    }
  }

  // Check that finalising with a commit = true works.
  @Test
  public void commit() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;
    BigInteger theMap = BigInteger.ONE;
    BigInteger theMapKey = BigInteger.TWO;

    BigInteger val1 = BigInteger.TEN;
    BigInteger val2 = BigInteger.valueOf(13);

    // Any non-zero Root Blockchain Id is deemed to indicate an active Cross-Blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ONE).send();

    // Set some values.
    this.storageWrapper.test_setUint256(theUint, val1).send();
    this.storageWrapper.test_setMapValue(theMap, theMapKey, val2).send();

    // Commit changes.
    this.lockableStorageContract.finalise(true).send();

    assert(!this.lockableStorageContract.locked().send());

    assert(this.storageWrapper.test_getUint256(theUint).send().compareTo(val1) == 0);
    assert(this.storageWrapper.test_getMapValue(theMap, theMapKey).send().compareTo(val2) == 0);
  }


  // Check that finalising with a commit = false works.
  @Test
  public void ignore() throws Exception {
    setupWeb3();
    deployContracts();

    BigInteger theUint = BigInteger.ZERO;
    BigInteger theMap = BigInteger.ONE;
    BigInteger theMapKey = BigInteger.TWO;

    BigInteger val1 = BigInteger.TEN;
    BigInteger val2 = BigInteger.valueOf(13);

    // Any non-zero Root Blockchain Id is deemed to indicate an active Cross-Blockchain call.
    this.mockCrossBlockchainControlContract.setRootBlockchainId(BigInteger.ONE).send();

    // Set some values.
    this.storageWrapper.test_setUint256(theUint, val1).send();
    this.storageWrapper.test_setMapValue(theMap, theMapKey, val2).send();

    // Commit changes.
    this.lockableStorageContract.finalise(false).send();

    assert(!this.lockableStorageContract.locked().send());

    assert(this.storageWrapper.test_getUint256(theUint).send().compareTo(BigInteger.ZERO) == 0);
    assert(this.storageWrapper.test_getMapValue(theMap, theMapKey).send().compareTo(BigInteger.ZERO) == 0);
  }

}