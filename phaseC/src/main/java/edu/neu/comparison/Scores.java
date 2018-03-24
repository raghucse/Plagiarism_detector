package edu.neu.comparison;

import java.io.IOException;
import java.io.Serializable;

public class Scores implements Serializable{

	double totalScore;
	String subScores;
	
	public Scores() {	
	}
	
	public Scores(double totalScore, String subScores) {	
		this.totalScore = totalScore;
		this.subScores = subScores;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public String getSubScores() {
		return subScores;
	}

	public void setSubScores(String subScores) {
		this.subScores = subScores;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeDouble(totalScore);
		out.writeUTF(subScores);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		totalScore = in.readDouble();
		subScores = in.readUTF();
	}
	
	public String toString() {
		return this.totalScore + " : " + subScores;
	}
	
}
