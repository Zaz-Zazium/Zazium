package TTest;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;

public class QWERT {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("EC","BC");
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
        keyPair.initialize(spec, new SecureRandom());
        KeyPair keypair = keyPair.genKeyPair();

        PrivateKey privatekey = keypair.getPrivate();
        PublicKey publickey = keypair.getPublic();

        System.out.println(privatekey.getAlgorithm());
        System.out.println(privatekey.getFormat());

        String priii = new BigInteger(1, privatekey.getEncoded()).toString(16);
        System.out.println(priii);
        System.out.println(priii.length());


        byte[] a = privatekey.getEncoded();
        ArrayList<Integer> priv = new ArrayList<Integer>();
        for(int i = 0; i < a.length; i++) {
            priv.add(Byte.toUnsignedInt(a[i]));
        }
        System.out.println(priv.toString());
        System.out.println(priv.size());
        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////


    }
}
