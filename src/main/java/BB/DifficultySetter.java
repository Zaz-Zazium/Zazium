package BB;

public class DifficultySetter {

    //in seconds
    int block_generator_interval = 10;

    //difficulty
    int difficulty_adjust_interval = 10;

    public void getDifficulty(Blockchain blockchain){
        Block latestBlock = Chainers.getLatestBlock();
        if(latestBlock.getIndex() == 0){
        }
    }
}
