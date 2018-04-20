package edu.neu.comparison;


import java.util.ArrayList;

public class GetContent {


    ArrayList<Integer> file1MatCont = new ArrayList<>();
    ArrayList<Integer> file2MatCont = new ArrayList<>();

    public GetContent(){
        //empty
    }


    public ArrayList<Integer> getMatchedContentF1(){
        return this.file1MatCont;
    }

    public void setMatchedContentF1(ArrayList<Integer> matCont){
        this.file1MatCont = matCont;
    }


    public ArrayList<Integer> getMatchedContentF2(){
        return this.file2MatCont;
    }

    public void setMatchedContentF2(ArrayList<Integer> matCont){
        this.file2MatCont = matCont;
    }
}

