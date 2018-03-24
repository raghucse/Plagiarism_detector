package edu.neu.comparison;

import java.io.IOException;
import java.io.Serializable;
import java.util.EnumMap;

public class ComparisonReport implements Serializable{

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    String filename1;
	String filename2;

	public double getScore() {
		return score;
	}

	double score;
	//EnumMap<STRATEGIES, Double> scores;
	
	public ComparisonReport() {
		//scores = new EnumMap<>(STRATEGIES.class);
	}
	
	public ComparisonReport(String filename1, String filename2, double score) {
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.score = score;
		//scores = new EnumMap<>(STRATEGIES.class);
	}

	/*
	public void putScore(STRATEGIES strategy, double score) {
		scores.put(strategy, score);
	}
	*/
	
	@Override
	public String toString() {
		return this.filename1+":"+this.filename2+"|"+score;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(filename1);
		out.writeUTF(filename2);
		out.writeDouble(score);
		//out.writeObject(scores);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		filename1 = in.readUTF();
		filename2 = in.readUTF();
		score = in.readDouble();
		//scores = (EnumMap<STRATEGIES, Double>) in.readObject();
	}
	
}
