package com.zazium.TTest;




//import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
public class AB {

    public static void main(String[] args) throws Exception {
        //Security.addProvider(new BouncyCastleFipsProvider());

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

        keyGen.initialize(ecSpec, new SecureRandom());

        KeyPair keyPair = keyGen.generateKeyPair();
        Signature signature = Signature.getInstance("ECDSA", "BC");

        // generate a signature

        //signature.initSign(keyPair.getPrivate(), Utils.createFixedRandom());

        byte[] message = new byte[]{(byte) 'a', (byte) 'b', (byte) 'c'};

        signature.update(message);

        byte[] sigBytes = signature.sign();

        // verify a signature

        signature.initVerify(keyPair.getPublic());

        signature.update(message);

        if (signature.verify(sigBytes)) {
            System.out.println("signature verification succeeded.");
        } else {
            System.out.println("signature verification failed.");
        }
    }
}

