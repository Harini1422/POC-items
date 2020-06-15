package gitIntegration;
import java.io.File;  
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.IndexDiff.StageState;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.lib.Repository;


public class ListUncommittedChanges {

    public static void main(String[] args) throws IOException, GitAPIException {
       
            System.out.println("Listing uncommitted changes:");
            final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
   	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
   	     File localPath = new File(FILE_PATH);
               Git git = Git.cloneRepository().setURI(REMOTE_URL)
                       .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
                       .setCloneAllBranches(false)
                       .setBranch("master")
                            .setDirectory(localPath).call();
               
               File myFile = new File(git.getRepository().getDirectory().getParent(), "masterFile");
               FileUtils.writeStringToFile(myFile, "TestContent", "UTF-8");

              
               git.add()
                      .addFilepattern("masterFile")
                      .call();
              
             
                Status status = git.status().call();
                Set<String> conflicting = status.getConflicting();
                for(String conflict : conflicting) {
                    System.out.println("Conflicting: " + conflict);
                }

                Set<String> added = status.getAdded();
                for(String add : added) {
                    System.out.println("Added: " + add);
                }

               

                Set<String> missing = status.getMissing();
                for(String miss : missing) {
                    System.out.println("Missing: " + miss);
                }


                Set<String> removed = status.getRemoved();
                for(String remove : removed) {
                    System.out.println("Removed: " + remove);
                }

                Set<String> uncommittedChanges = status.getUncommittedChanges();
                for(String uncommitted : uncommittedChanges) {
                    System.out.println("Uncommitted: " + uncommitted);
                }
                
                
                RevCommit revCommit = git.commit()
                        .setMessage("Added " + "masterFile")
                        .call();
                
              
                
                Set<String> changed = status.getChanged();
                for(String change : changed) {
                    System.out.println("Change: " + change);
                }
                Set<String> untracked = status.getUntracked();
                for(String untrack : untracked) {
                    System.out.println("Untracked: " + untrack);
                }

                

                Set<String> modified = status.getModified();
                for(String modify : modified) {
                    System.out.println("Modification: " + modify);
                }
                
                Set<String> untrackedFolders = status.getUntrackedFolders();
                for(String untrack : untrackedFolders) {
                    System.out.println("Untracked Folder: " + untrack);
                }

                Map<String, StageState> conflictingStageState = status.getConflictingStageState();
                for(Map.Entry<String, StageState> entry : conflictingStageState.entrySet()) {
                    System.out.println("ConflictingState: " + entry);
                }
                FileUtils.deleteDirectory(localPath);
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
        
    }