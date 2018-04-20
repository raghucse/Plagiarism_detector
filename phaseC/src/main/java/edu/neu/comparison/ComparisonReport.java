package edu.neu.comparison;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for generating the plagiarism report
 */
public class ComparisonReport implements Serializable{

	String studentname1;
	String studentname2;
    String filename1;
	String filename2;
	Scores scores;
	List<List<String>> diffContent;
	
	/**
	 * This function is used for returning the student name
	 * @return a student name which is a string
	 */
    public String getStudentname1() {
        return studentname1;
    }

	/**
	 * This function is used to set the name of the first student
	 * @param studentname1 is a string which determines the name to be
	 *                  used for the student
	 */
	public void setStudentname1(String studentname1) {
        this.studentname1 = studentname1;
    }

	/**
	 * This function is used for returning the student name
	 * @return a student name which is a string
	 */
    public String getStudentname2() {
        return studentname2;
    }

	/**
	 * This function is used to set the name of the second student
	 * @param studentname2 is a string which determines the name to be
	 *                  used for the student
	 */
	public void setStudentname2(String studentname2) {
        this.studentname2 = studentname2;
    }
	
	/**
	 * This function is used for returning the file name
	 * @return a file name which is a string
	 */
    public String getFilename1() {
        return filename1;
    }

	/**
	 * This function is used to set the name of the file
	 * @param filename1 is a string which determines the name to be
	 *                  used for the file
	 */
	public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

	/**
	 * This function is used for returning the file name
	 * @return a file name which is a string
	 */
    public String getFilename2() {
        return filename2;
    }

	/**
	 * This function is used to set the name of the file
	 * @param filename2 is a string which determines the name to be
	 *                  used for the file
	 */
    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

	/**
	 * This function is used to return the scores generated from the plagiarism run
	 * @return the score of the plagiarism run
	 */
	public Scores getScores() {
		return scores;
	}

	/**
	 * This function is used to set the scores
	 * @param scores is the value which is to be assigned to the score variable
	 */
	public void setScores(Scores scores) {
		this.scores = scores;
	}

	
	
	public List<List<String>> getDiffContent() {
		return diffContent;
	}

	public void setDiffContent(List<List<String>> diffContent) {
		this.diffContent = diffContent;
	}

	public ComparisonReport() {
	}

	/**
	 * This constructor sets the file names and the scores
	 * @param studentname1 is the name of the first student
	 * @param studentname2 is the name of the second student
	 * @param filename1 is the name of the first file
	 * @param filename2 is the name of the second file
	 * @param scores is the value to be assigned as the score
	 */
	
	public ComparisonReport(String studentname1, String studentname2, String filename1, String filename2, Scores scores, List<List<String>> diffContent) {
		this.studentname1 = studentname1;
		this.studentname2 = studentname2;
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.scores = scores;
		this.diffContent = diffContent;
	}

	/**
	 * This function is used to return all the information as a string
	 * @return the file names and the scores as a string
	 */

	public String toString() {
		return this.studentname1+":"+this.studentname2+"|"+this.filename1+":"+this.filename2+"|"+scores;
	}

	/**
	 * Used for writing the data
	 * @param out is the object of ObjectOutputStream
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(studentname1);
		out.writeUTF(studentname2);
		out.writeUTF(filename1);
		out.writeUTF(filename2);
		out.writeObject(scores);
		out.writeInt(diffContent.size());
		for(int i=0; i<diffContent.size(); i++) {
			out.writeInt(diffContent.get(i).size());
			for(int j=0; j<diffContent.get(i).size(); j++) {
				out.writeUTF(diffContent.get(i).get(j));
			}
		}
	}

	/**
	 * Used for reading the data
	 * @param in is the object of the ObjectInputStream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		studentname1 = in.readUTF();
		studentname2 = in.readUTF();
		filename1 = in.readUTF();
		filename2 = in.readUTF();
		scores = (Scores) in.readObject();
		diffContent = new ArrayList<>();
		int diffSize = in.readInt();
		for(int i=0; i<diffSize; i++) {
			List<String> innerDiff = new ArrayList<>();
			int diffSizeInner = in.readInt();
			for(int j=0; j<diffSizeInner; j++) {
				innerDiff.add(in.readUTF());
			}
			diffContent.add(innerDiff);
		}
	}
	
}
