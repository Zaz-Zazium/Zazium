package TTest;

import Misc.Fileread;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.Security;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;


public class Testers {
    static PrivateKey privatekey;
    static PublicKey publickey;
    //static BouncyCastleFipsProvider bcp = new BouncyCastleFipsProvider();

    public static void main(String[] args) throws Exception {

        generateKeyPair();
        String aaa = "hello this is a test";

        byte[] sign = generateSignature(privatekey,aaa.getBytes());

        ArrayList<Integer> we = new ArrayList<>();
        for(int i = 0; i < sign.length; i++) {
            //System.out.println();
            we.add(Byte.toUnsignedInt(sign[i]));
        }
        System.out.println(we.toString());

        ///
        ///
        String aaac = "complete different";
        boolean a = verifySignature(publickey, aaa.getBytes(), sign);
        System.out.println(a);

        System.out.println(privatekey.getAlgorithm());
        System.out.println(privatekey.getFormat());

    }

    public static KeyPair generateKeyPair()throws GeneralSecurityException {
        ArrayList<Integer> pub = new ArrayList<Integer>();
        ArrayList<Integer> priv = new ArrayList<Integer>();

        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ec = new ECGenParameterSpec("secp256r1");
        keyPair.initialize(ec, new SecureRandom());
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //keyPair.initialize(256,random);
        //keyPair.initialize(256);
        privatekey = keyPair.genKeyPair().getPrivate();
        publickey = keyPair.genKeyPair().getPublic();

        byte[] publicKey = keyPair.genKeyPair().getPublic().getEncoded();
        byte[] privateKey = keyPair.genKeyPair().getPrivate().getEncoded();
        for(int i = 0; i < privateKey.length; i++) {
            priv.add(Byte.toUnsignedInt(privateKey[i]));
        }
        System.out.println(priv.toString());

        return keyPair.generateKeyPair();
    }
    public static PrivateKey pr(KeyPairGenerator keyPair){
        privatekey = keyPair.genKeyPair().getPrivate();
        return privatekey;
    }
    public static PublicKey pu(KeyPairGenerator keyPair){
        publickey = keyPair.genKeyPair().getPublic();
        return publickey;
    }



    public static byte[] generateSignature(PrivateKey ecPrivate, byte[] input)
            throws GeneralSecurityException
    {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ecPrivate,new SecureRandom());
        signature.update(input);
        return signature.sign();
    }

    public static boolean verifySignature(PublicKey ecPublic, byte[] input, byte[] encSignature)
            throws GeneralSecurityException
    {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(ecPublic);
        signature.update(input);
        boolean a = signature.verify(encSignature);
        return a;
    }

    public static void b64Encode(String entropy) {
        ArrayList<String> dict = Fileread.base64String();
        ArrayList<String> encoded = new ArrayList<>();
        int min = 0;
        int max = 6;
        for (int i = 0; i < 22; i++) {
            encoded.add(dict.get(Integer.parseInt(entropy.substring(min, max), 2)));
            min += 6;
            max += 6;
        }
        System.out.println(encoded.toString());
        System.out.println(encoded.size());
    }

    public static String publicKey(ArrayList<String> privateKey) {
        String toKey = "";
        for (int i = 0; i < 12; i++) {
            toKey += privateKey.get(i);
        }
        String publickeys = DigestUtils.sha256Hex(toKey);
        //System.out.println(publickeys);
        return publickeys;
    }
}
