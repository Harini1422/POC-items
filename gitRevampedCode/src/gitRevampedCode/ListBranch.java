package gitRevampedCode;



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


public class ListBranch {

    public static void main(String[] args) throws IOException, GitAPIException {
       
            System.out.println("Listing local branches:");
        	final String REMOTE = "https://Harini_1242@bitbucket.org/Harini_1242/testrepo.git";
          //	final String REMOTE = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
       	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/test";
       	     File localPath = new File(FILE_PATH);
                   Git git = Git.cloneRepository().setURI(REMOTE)
                           .setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini_1242", "Appviewx#1234"))
                           .setCloneAllBranches(true)
                          
                                .setDirectory(localPath).call();
                   // local 
                   
					
				//  List<Ref> call = git.branchList().call(); 
						/*
						 * for (Ref ref : call) { System.out.println("Branch: " + ref + " " +
						 * ref.getName() + " " + ref.getObjectId().getName()); }
						 */
					 

              /*  System.out.println("Now including remote branches:");
                List<Ref>  call = git.branchList().setListMode(ListMode.ALL).call();
                for (Ref ref : call) {  
                	String branchName = ref.getName().toString();
                	int index = branchName.lastIndexOf("/");
                	String output =  branchName.substring(index+1);
                    System.out.println("Branch: "  + " " + output);*/
                    //+ " " + ref.getObjectId().getName());
                  
                   // FileUtils.deleteDirectory(localPath);
        }
    }
    