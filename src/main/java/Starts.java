
import BB.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static Misc.Conv.bytesToHex;

public class Starts {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] genesisBytes = Chainers.genesisBlock().calcHash(Chainers.genesisBlock());
        String genesisHash = bytesToHex(genesisBytes);
        //System.out.println(genesisHash);

        //System.out.println(a.hashCode());
        Block aaa = new Block(0,"",
                1614876976, "Here", 1, 12);

        //System.out.println(Block.toStringHash(aaa));
        //System.out.println(Block.toString(aaa));
        findBlock(aaa);


    }

    public static void findBlock(Block block) throws NoSuchAlgorithmException {
        int nonce = 0;
        while(true){
            Block fBlock = new Block(0,"",
                    1614876976, "Here", 1, nonce);
            String stringers = Block.toStringHash(fBlock);
            if (stringers.startsWith("0")){
                System.out.println(stringers);
                System.out.println(nonce);
                break;
            }else{
                nonce++;
            }
        }
    }


}
