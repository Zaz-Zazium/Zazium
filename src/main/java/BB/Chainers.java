package BB;

import BB.Block;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Chainers {
    static ArrayList<Block> block = new ArrayList<>();

    public static Block genesisBlock() throws NoSuchAlgorithmException {
        Block geneBlock = new Block(0, "0ed162a8fc196451b7c9125c017e8042b2d40b5d71bc45e8ea0956acadcc5564",
                "", 1614876976, "Here", 1, 3);
        block.add(geneBlock);
        return geneBlock;
    }

    public ArrayList<Block> getBlockchain() {
        return block;
    }

    public static Block getLatestBlock() {
        return block.get(block.size() - 1);
    }

    public void generateNextBlock(int index, String currentHash, String previousHash,
                                   int timestamp, String datas, int difficulty, int nonce) throws NoSuchAlgorithmException {

        Block generateBlock = new Block(index, currentHash, previousHash,
                timestamp, datas, difficulty, nonce);

        block.add(generateBlock);
    }

    public boolean isValidBlock(Block previousBlock, Block nextBlock){
        boolean checker = false;
        if (previousBlock.getIndex() == nextBlock.getIndex()){
            return false;
        }else if(previousBlock.getCurrentHash() == nextBlock.getCurrentHash()){
            return false;
        }
        return true;
    }


}
