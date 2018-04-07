package training;



import edu.neu.astgeneration.ASTUtils;
import edu.neu.comparison.CosineSimilarity;
import edu.neu.comparison.LCS;
import edu.neu.comparison.LVDistance;

import java.util.ArrayList;


public class TrainData {

    ArrayList<String> ast1 = new ArrayList<>();
    ArrayList<String> ast2 = new ArrayList<>();

    private double[] weights = new double[] {0.3333, 0.3333, 0.3333};
    private double MossLV, MossLCS,MossCos;
    private double mossScore = 90, increment = 0.1, decrement = 0.05;


    public double[] train(ArrayList<String> ast1,ArrayList<String> ast2)
    {
        int ast1Length = ast1.size();
        int ast2Length = ast2.size();
        int iterations = 15;
        int iter_count = 1;
        LVDistance lvDistance = new LVDistance(new ASTUtils());
        LCS lcs = new LCS(new ASTUtils());
        CosineSimilarity cs = new CosineSimilarity(new ASTUtils());

        while(iter_count<=iterations){
            double lvScore = lvDistance.calculateLD(ast1, ast2);
            lvScore*=100;
            double LCSScore = lcs.calculateLCS(ast1,ast2);
            LCSScore*=100;
            double CosSimScore = cs.cosSim(ast1,ast2);
            CosSimScore*=100;
            MossLV= mossScore - lvScore;
            MossLCS = mossScore - LCSScore;
            MossCos = mossScore - CosSimScore;

            if(MossLV> MossLCS && MossLV> MossCos){
                weights[0] += increment;
                weights[1] -= decrement;
                weights[2] += decrement;
            }
            else if(MossLCS > MossLV && MossLCS > MossCos){

                weights[0] -= decrement;
                weights[1] += increment;
                weights[2] += decrement;
            }
            else{

                weights[0] -= decrement;
                weights[1] += decrement;
                weights[2] += increment;
            }

        }

        return weights;
    }





}