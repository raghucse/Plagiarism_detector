package edu.neu.models;

import edu.neu.Log;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to retrieve all the files
 * from the GitHub URL.
 */
public class GitSubmission implements Submission{
	
	private String studentName;
	private String gitURL;
	private List<File> files;

	/**
	 * The constructor is used to assign the GitHub url
	 * to the gitURL variable
	 * @param gitURL
	 */
	public GitSubmission(String studentName, String gitURL) {
		this.studentName = studentName;
		this.gitURL = gitURL;
	}

	/**
	 * This function is used to retrieve all the files
	 * from the specified GitHub URL
	 * @return the list of files that are retrieved
	 */
	public List<File> getFiles() {
		// To implement git clone and return a list of files
		
		if(files==null) {
			files = new ArrayList<>();
			String remoteUrl = gitURL;
			File localPath;
			try {
				localPath = File.createTempFile("jgitTemphelloworld", "");
				
				if(!localPath.delete()) {
		            Log.error("Could not delete temporary file " + localPath);
		        }
				
				// then clone
				Log.info("Cloning from " + remoteUrl + " to " + localPath);
		        try (Git result = Git.cloneRepository()
		                .setURI(remoteUrl)
		                .setDirectory(localPath)
		                .call()) {
					Log.info("Looping over files");
			        for(File f : listAllFiles(localPath))
			        {
			        		if(isValidFileForCheck(f)) {
								Log.info("Visited file : "+f.getName());
								files.add(f);
							}
			        }
		        }
				Log.info("Done cloning " + remoteUrl + " to " + localPath);
			} catch (Exception e) {
				Log.error("Error git cloning" + e.getStackTrace());
			}
		}

		return files;
	}
	
	public List<File> listAllFiles(File localPath) {
		List<File> resultFiles = new ArrayList<>();
		for (File f : localPath.listFiles()) {
			if(f.isDirectory()) {
				resultFiles.addAll(listAllFiles(f));
			}
			else {
				resultFiles.add(f);
			}
		}
		return resultFiles;
	}
	
	public boolean isValidFileForCheck(File f) {
		return f.getName().endsWith(".py"); // && !f.getName().equals(".git")
	}

	@Override
	public String getStudentName() {
		return studentName;
	}

}
