package gitIntegration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;


public class ListBranches {

    public static void main(String[] args) throws IOException, GitAPIException {
       
            System.out.println("Listing local branches:");
        	final String REMOTE = "https://github.com/Harini1422/myrepo.git";
          // 	 final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
       	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
       	     File localPath = new File(FILE_PATH);
                   Git git = Git.cloneRepository().setURI(REMOTE)
                           .setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini1422", "Harini_4022"))
                           .setCloneAllBranches(false)
                           .setBranch("master")
                                .setDirectory(localPath).call();
                   // local 
                   
					
					  List<Ref> call = git.branchList().call(); 
						/*
						 * for (Ref ref : call) { System.out.println("Branch: " + ref + " " +
						 * ref.getName() + " " + ref.getObjectId().getName()); }
						 */
					 

                System.out.println("Now including remote branches:");
                call = git.branchList().setListMode(ListMode.ALL).call();
                for (Ref ref : call) {
                    System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
                
                    FileUtils.deleteDirectory(localPath);
        }
    }}
    