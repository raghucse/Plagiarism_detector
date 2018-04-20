package edu.neu.comparison;
import java.util.ArrayList;

/**
 * This class returns the line numbers of the similarities in two files as one object
 * @author Ananta Rajesh Arora
 * @version 1.0
 * @since 2018-04-19
 */
public class GetContent {


    ArrayList<Integer> file1MatCont = new ArrayList<>();
    ArrayList<Integer> file2MatCont = new ArrayList<>();

    /**
     * Empty constructor
     */
    public GetContent(){
        //empty
    }

    /**
     * Returns the matching line numbers of the first file
     * @return returns an array list of integers which represents the line numbers
     */
    public ArrayList<Integer> getMatchedContentF1(){
        return this.file1MatCont;
    }

    /**
     * This method creates a list of line numbers for the first file
     * @param matCont is an array list of integers containing the line numbers of
     *                the similar content
     */
    public void setMatchedContentF1(ArrayList<Integer> matCont){
        this.file1MatCont = matCont;
    }

    /**
     * Returns the matching line numbers of the second file
     * @return returns an array list of integers which represents the line numbers
     */
    public ArrayList<Integer> getMatchedContentF2(){
        return this.file2MatCont;
    }

    /**
     * This method creates a list of line numbers for the second file
     * @param matCont is an array list of integers containing the line numbers of
     *                the similar content
     */
    public void setMatchedContentF2(ArrayList<Integer> matCont){
        this.file2MatCont = matCont;
    }
}

