package gitIntegration;
import org.eclipse.jgit.api.CloneCommand;  
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class PullRemoteRepository {
    private static final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";

    public static void main(String[] args) throws Exception {
        File localPath = new File("/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit");
		/*
		 * Collection<String> branchesToClone = new ArrayList<String>();
		 * branchesToClone.add("harini_new_branch");
		 */     
        if(validateRepository(REMOTE_URL,"harini.v","appviewx@123")) {
        
                try (Git result = Git.cloneRepository().setURI(REMOTE_URL)
                        .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx@123"))
                   .setCloneAllBranches(false)
                   .setBranch("master")
                        .setDirectory(localPath).call()) {

                    System.out.println("Having repository: " + result.status());
                }}
             
             
    
}
    public static boolean validateRepository(String repositoryURL, String username, String password) throws Exception {
	    boolean result = false;
	    Repository db = FileRepositoryBuilder.create(new File("/tmp"));
	    Git git = Git.wrap(db);
	    final LsRemoteCommand lsCmd = git.lsRemote();
	    lsCmd.setRemote(repositoryURL);
	    if (username != null && password != null) {
	        lsCmd.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
	    }
	    if (null != lsCmd.call()){
	        result = true;
	    }
	    return result;
	}
    }