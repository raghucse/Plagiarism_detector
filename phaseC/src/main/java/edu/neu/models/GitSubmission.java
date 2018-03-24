package edu.neu.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import edu.neu.Log;

public class GitSubmission implements Submission{
	
	private String gitURL;
	private List<File> files;
	
	public GitSubmission(String gitURL) {
		this.gitURL = gitURL;
	}

	@Override
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
			        for(File f : localPath.listFiles())
			        {
			        		if(!f.getName().equals(".git")) {
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

}
