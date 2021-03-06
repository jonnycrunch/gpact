package net.consensys.gpact.test.soliditywrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.7.0-SNAPSHOT.
 */
@SuppressWarnings("rawtypes")
public class BlsSignatureTest extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b1a806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063bbb82d891461003b578063f6e548e914610057575b600080fd5b61004361016d565b604080519115158252519081900360200190f35b61016b6004803603606081101561006d57600080fd5b81019060208101813564010000000081111561008857600080fd5b82018360208201111561009a57600080fd5b803590602001918460018302840111640100000000831117156100bc57600080fd5b9193909290916020810190356401000000008111156100da57600080fd5b8201836020820111156100ec57600080fd5b8035906020019184600183028401116401000000008311171561010e57600080fd5b91939092909160208101903564010000000081111561012c57600080fd5b82018360208201111561013e57600080fd5b8035906020019184600183028401116401000000008311171561016057600080fd5b509092509050610176565b005b60005460ff1681565b61017e610a4b565b6101bd87878080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525061026592505050565b90506101c7610a70565b61020684848080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525061034d92505050565b905061024a8287878080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152508692506103ef915050565b6000805460ff19169115159190911790555050505050505050565b61026d610a4b565b60408051600480825260a082019092526060916020820160808036833701905050905060205b815160200281116102ae578381015182820152602001610293565b50806000815181106102bc57fe5b60209081029190910101518251528051819060019081106102d957fe5b60209081029190910101518251600160200201528051819060029081106102fc57fe5b6020026020010151826020015160006002811061031557fe5b602002015280518190600390811061032957fe5b6020026020010151826020015160016002811061034257fe5b602002015250919050565b610355610a70565b604080516002808252606080830184529260208301908036833701905050905060205b81516020028111610393578381015182820152602001610378565b5060405180604001604052806000815260200160008152509150806000815181106103ba57fe5b6020026020010151826000018181525050806001815181106103d857fe5b602002602001015182602001818152505050919050565b60408051600280825260608281019093526000929190816020015b610412610a70565b81526020019060019003908161040a57505060408051600280825260608083019093529293509091602082015b610447610a4b565b81526020019060019003908161043f579050509050610465846104eb565b8260008151811061047257fe5b60200260200101819052506104868561056e565b8260018151811061049357fe5b60200260200101819052506104a6610618565b816000815181106104b357fe5b602002602001018190525085816001815181106104cc57fe5b60200260200101819052506104e182826106d8565b9695505050505050565b6104f3610a70565b7f30644e72e131a029b85045b68181585d97816a916871ca8d3c208c16d87cfd4761051d83610976565b1561053d5750506040805180820190915260008082526020820152610569565b6040518060400160405280846000015181526020018285602001518161055f57fe5b0683038152509150505b919050565b610576610a70565b815160208301206000610587610a70565b60ff8216830161059e610598610990565b826109b1565b91506105a982610976565b6105b35750610610565b600190920191600a60ff84161061060a576040805162461bcd60e51b815260206004820152601660248201527511985a5b1959081d1bc81b585c081d1bc81c1bda5b9d60521b604482015290519081900360640190fd5b50610587565b949350505050565b610620610a4b565b50604080516080810182527f198e9393920d483a7260bfb731fb5d25f1aa493335a9e71297e485b7aef312c28183019081527f1800deef121f1e76426a00665e5c4479674322d4f75edadd46debd5cd992f6ed6060830152815281518083019092527f090689d0585ff075ec9e99ad690c3395bc4b313370b38ef355acdadcd122975b82527f12c85ea5db8c6deb4aab71808dcb408fe3d1e7690c43d37b4ce6cc0166fa7daa60208381019190915281019190915290565b60008151835114610728576040805162461bcd60e51b81526020600482015260156024820152742837b4b73a1031b7bab73a1036b4b9b6b0ba31b41760591b604482015290519081900360640190fd5b82516006810260608167ffffffffffffffff8111801561074757600080fd5b50604051908082528060200260200182016040528015610771578160200160208202803683370190505b50905060005b838110156108f65786818151811061078b57fe5b6020026020010151600001518282600602600001815181106107a957fe5b6020026020010181815250508681815181106107c157fe5b6020026020010151602001518282600602600101815181106107df57fe5b6020026020010181815250508581815181106107f757fe5b60209081029190910101515151825183906002600685020190811061081857fe5b60200260200101818152505085818151811061083057fe5b6020908102919091010151516001602002015182826006026003018151811061085557fe5b60200260200101818152505085818151811061086d57fe5b60200260200101516020015160006002811061088557fe5b602002015182826006026004018151811061089c57fe5b6020026020010181815250508581815181106108b457fe5b6020026020010151602001516001600281106108cc57fe5b60200201518282600602600501815181106108e357fe5b6020908102919091010152600101610777565b506108ff610a8a565b6000602082602086026020860160086107d05a03fa905080610968576040805162461bcd60e51b815260206004820152601960248201527f50616972696e67206f7065726174696f6e206661696c65642e00000000000000604482015290519081900360640190fd5b505115159695505050505050565b805160009015801561098a57506020820151155b92915050565b610998610a70565b5060408051808201909152600181526002602082015290565b6109b9610a70565b6109c1610aa8565b83518152602080850151908201526040810183905260006109e0610a70565b60408160608560076107d05a03fa915081610a42576040805162461bcd60e51b815260206004820152601c60248201527f506f696e74206d756c7469706c69636174696f6e206661696c65642e00000000604482015290519081900360640190fd5b95945050505050565b6040518060400160405280610a5e610ac6565b8152602001610a6b610ac6565b905290565b604051806040016040528060008152602001600081525090565b60405180602001604052806001906020820280368337509192915050565b60405180606001604052806003906020820280368337509192915050565b6040518060400160405280600290602082028036833750919291505056fea2646970667358221220ea6e9767b5effe854538a726ad6f53f4f8366e2384d4889d2b19ccc71d53e96264736f6c63430007040033";

    public static final String FUNC_VERIFIED = "verified";

    public static final String FUNC_VERIFYSIGNATURE = "verifySignature";

    @Deprecated
    protected BlsSignatureTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BlsSignatureTest(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BlsSignatureTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BlsSignatureTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> verified() {
        final Function function = new Function(FUNC_VERIFIED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public String getRLP_verified() {
        final Function function = new Function(
                FUNC_VERIFIED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return org.web3j.abi.FunctionEncoder.encode(function);
    }

    public RemoteFunctionCall<TransactionReceipt> verifySignature(byte[] _publicKey, byte[] _message, byte[] _signature) {
        final Function function = new Function(
                FUNC_VERIFYSIGNATURE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_publicKey), 
                new org.web3j.abi.datatypes.DynamicBytes(_message), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public String getRLP_verifySignature(byte[] _publicKey, byte[] _message, byte[] _signature) {
        final Function function = new Function(
                FUNC_VERIFYSIGNATURE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_publicKey), 
                new org.web3j.abi.datatypes.DynamicBytes(_message), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Collections.<TypeReference<?>>emptyList());
        return org.web3j.abi.FunctionEncoder.encode(function);
    }

    @Deprecated
    public static BlsSignatureTest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlsSignatureTest(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BlsSignatureTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlsSignatureTest(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BlsSignatureTest load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BlsSignatureTest(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BlsSignatureTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BlsSignatureTest(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BlsSignatureTest> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BlsSignatureTest.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BlsSignatureTest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BlsSignatureTest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BlsSignatureTest> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BlsSignatureTest.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BlsSignatureTest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BlsSignatureTest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
