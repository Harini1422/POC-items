package gitIntegration;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git; 
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class MergeChanges {
	 
    public static void main(String[] args) throws IOException, GitAPIException {
    	 final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/test";
	     File localPath = new File(FILE_PATH);
            Git git = Git.cloneRepository().setURI(REMOTE_URL)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
                    .setCloneAllBranches(false)
                    .setBranch("master")
                         .setDirectory(localPath).call();
            
            git.branchCreate().setName("changes").call();
            Ref checkout = git.checkout().setName("changes").call();
            System.out.println("Result of checking out the branch: " + checkout);
          //  createCommit(git, "branchFile", "content98");
            createCommit(git, "sharedFile", "content98");
            checkout = git.checkout().setName("master").call();
            System.out.println("Result of checking out master: " + checkout);
            ObjectId mergeBase = git.getRepository().resolve("changes");
       
          
            MergeResult merge = git.merge().
                    include(mergeBase).
                    setCommit(true).
                    setFastForward(MergeCommand.FastForwardMode.NO_FF).
                    //setSquash(false).
                    setMessage("Merged changes").
                    call();
         if (merge.getMergeStatus().equals(MergeResult.MergeStatus.CONFLICTING)){
            System.out.println(merge.getConflicts().toString());
      
        }
         else {
        	 System.out.println(merge.getMergeStatus().toString());
         }
         
         pushMerge(git);
    }

    private static void createCommit(Git git, String fileName, String content) throws IOException, GitAPIException {
        // create the file
        File myFile = new File(git.getRepository().getDirectory().getParent(), fileName);
        FileUtils.writeStringToFile(myFile, content, "UTF-8");

        // run the add
        git.add()
                .addFilepattern(fileName)
                .call();

        // and then commit the changes
        RevCommit revCommit = git.commit()
                .setMessage("Added " + fileName)
                .call();

        System.out.println("Committed file " + myFile + " as " + revCommit + " to repository at " + git.getRepository().getDirectory());
    }
    private static void pushMerge(Git git) throws GitAPIException, TransportException {
      	 final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";

    	   git.push().setRemote(REMOTE_URL).
           setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123")).
           setRefSpecs(new RefSpec("refs/heads/master:refs/heads/master")).call();
           System.out.println("Committed file "  + " to repository ");
    	
    }
}