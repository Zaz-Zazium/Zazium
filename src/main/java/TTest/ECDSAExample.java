package TTest;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
        System.out.println("Max Key Size for AES : " + maxKeySize);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(priv);
        ecdsa.update(str.getBytes());
        byte[] realSig = ecdsa.sign();

        String priii = new BigInteger(1, priv.getEncoded()).toString(11);
        System.out.println(priii);
        System.out.println(priii.length());
        //System.out.println("Signature: " + new BigInteger(1, priv.getEncoded()).toString(16));

        Signature veri = Signature.getInstance("SHA1withECDSA");
        veri.initVerify(pub);
        veri.update(str.getBytes());
        boolean aa = veri.verify(realSig);

        System.out.println(aa);
    }

}