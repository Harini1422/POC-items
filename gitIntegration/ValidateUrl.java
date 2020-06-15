package gitIntegration;



import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class ValidateUrl {
	private static final String REMOTE = "https://github.com/Harini1422/myrepo.git";
	//private static final String REMOTE = "ssh://git@github.com:Harini1422/myrepo.git";
	//final static String REMOTE = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
	public static void main(String[] args) throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		 // boolean result = false;
			/*
			 * Git result = Git.cloneRepository().setURI(REMOTE) .setCredentialsProvider(new
			 * UsernamePasswordCredentialsProvider(
			 * "2f24b23c6e1ebc4c420a87bae782e54468b8ab54", "")) .setCloneAllBranches(false)
			 * .setBranch("master") .setDirectory(localPath).call();
			 */ 
			/*
			 * Repository db = Git.cloneRepository().setURI(REMOTE).getRepository(); Git git
			 * = Git.wrap(db); final LsRemoteCommand lsCmd = git.lsRemote();
			 * lsCmd.setRemote(REMOTE); String username = "Harini1422"; String password =
			 * "Harini_4022"; if (username != null && password != null) {
			 * lsCmd.setCredentialsProvider(new
			 * UsernamePasswordCredentialsProvider(username, password)); } if (null !=
			 * lsCmd.call()){ result = true; }
			 */
		String username = "Harini1422"; String password =
				  "Harini_4022"; 
		boolean result = false;
		    try{
		    Repository db = FileRepositoryBuilder.create(new File("/home/harini.v/Documents"));
		    Git git = Git.wrap(db);
		    final LsRemoteCommand lsCmd = git.lsRemote();
		    lsCmd.setRemote(REMOTE);
		    if (username != null && password != null) {
		        lsCmd.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
		    }
		    if (null != lsCmd.call()){
		        result = true;
		    }}
		    
		  
		    catch(TransportException e) {
		    	result = false;
		    }
		   System.out.println(result);
	}
	
}
