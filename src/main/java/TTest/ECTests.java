package TTest;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECTests {
    static PrivateKey privatekey;
    static PublicKey publickey;

    public static void main(String[] args) throws GeneralSecurityException {
        generateKeyPair();

        String strTest = "Hello World! This is a test";

        byte[] sign = generateSignature(privatekey, strTest.getBytes());
        boolean isSignValid = verifySignature(publickey, strTest.getBytes(), sign);

        System.out.println(isSignValid);
    }

    public static KeyPair generateKeyPair() throws GeneralSecurityException {
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ec = new ECGenParameterSpec("secp256r1");
        keyPair.initialize(ec, new SecureRandom());
//        privatekey = keyPair.genKeyPair().getPrivate();
//        publickey = keyPair.genKeyPair().getPublic();

        KeyPair keypair = keyPair.genKeyPair();

        privatekey = keypair.getPrivate();
        publickey = keypair.getPublic();

        System.out.println(privatekey.getAlgorithm());
        System.out.println(privatekey.getFormat());
        System.out.println(publickey.getAlgorithm());
        System.out.println(publickey.getFormat());

        return keyPair.generateKeyPair();
    }

    public static byte[] generateSignature(PrivateKey ecPrivate, byte[] input) throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ecPrivate, new SecureRandom());
        signature.update(input);
        return signature.sign();
    }

    public static boolean verifySignature(PublicKey ecPublic, byte[] input, byte[] encSignature) throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(ecPublic);
        signature.update(input);
        boolean state = signature.verify(encSignature);
        return state;
    }
}
