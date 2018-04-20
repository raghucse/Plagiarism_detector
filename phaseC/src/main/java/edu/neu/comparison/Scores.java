package edu.neu.comparison;

import java.io.IOException;
import java.io.Serializable;

public class Scores implements Serializable{

	double totalScore;
	String subScores;
	
	public Scores() {	
	}

	/**
	 * This constructor is used for setting the total score of the
	 * strategies and the scores of each run
	 * @param totalScore is the combined score of all the strategies
	 * @param subScores is the score of each strategy
	 */
	public Scores(double totalScore, String subScores) {	
		this.totalScore = totalScore;
		this.subScores = subScores;
	}

	/**
	 * @return the combined score of all strategies
	 */
	public double getTotalScore() {
		return totalScore;
	}

	/**
	 * This function is used to set the total score
	 * @param totalScore is the value to be set as the total score
	 */
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	/*
	 * @return the score of each strategy
	 */
	public String getSubScores() {
		return subScores;
	}

	/**
	 * This function is used to set the sub score
	 * @param subScores is the value to be set as the sub score
	 */
	public void setSubScores(String subScores) {
		this.subScores = subScores;
	}

	/**
	 * Used for writing the data
	 * @param out is the object of ObjectOutputStream
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeDouble(totalScore);
		out.writeUTF(subScores);
	}

	/**
	 * Used for reading the data
	 * @param in is the object of the ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		totalScore = in.readDouble();
		subScores = in.readUTF();
	}

	/**
	 * This function is used to return all the information as a string
	 * @return the total score and sub score as a string
	 */
	public String toString() {
		return this.totalScore + " : " + subScores;
	}
	
}
