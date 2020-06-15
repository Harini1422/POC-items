package gitRevampedCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.Charsets;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class AddFolderCommit {
	final static String REMOTE_URL = "https://Harini_1242@bitbucket.org/Harini_1242/testrepo.git";
    final static String FOLDER_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/test";
    final static String CLONE_LOCATION = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
	public static void main(String[] args) throws NoFilepatternException, GitAPIException, IOException {
		/*
		 * FileRepositoryBuilder builder = new FileRepositoryBuilder(); File
		 * clone_location = new File(CLONE_LOCATION); Repository repository =
		 * builder.setGitDir(new File(FOLDER_PATH))
		 * .readEnvironment().findGitDir().setup().build(); CloneCommand clone =
		 * Git.cloneRepository(); clone.setBare(false).setCloneAllBranches(true);
		 * clone.setDirectory(clone_location).setURI(REMOTE_URL); try { clone.call(); }
		 * catch (GitAPIException e) { e.printStackTrace(); }
		 * Files.write("testing it...", new File(FOLDER_PATH + "/test2.txt"),
		 * Charsets.UTF_8); Git g = new Git(repository);
		 * g.add().addFilepattern(".").call();
		 */
		File folderPath = new File(FOLDER_PATH);
		/*  Git git = Git.cloneRepository().setURI(REMOTE_URL)
                  .setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini_1242", "Appviewx#1234"))
                  .setCloneAllBranches(true)*/
                 
         //              .setDirectory(folderPath).call();
		UsernamePasswordCredentialsProvider upc = new UsernamePasswordCredentialsProvider(
                "Harini_1242", "Appviewx#1234");
		
		Git result = Git.init().setDirectory(folderPath).call();
	    Repository testRepo = result.getRepository();
	    File localpath = testRepo.getWorkTree();
	    
	    File file = new File(FOLDER_PATH +"/test1");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
                File newfile = new File(FOLDER_PATH +"/test1/samplefile");
                newfile.createNewFile();
            }
        }
        File file1 = new File(FOLDER_PATH +"/test2");
        if (!file1.exists()) {
            if (file1.mkdir()) {
                System.out.println("Directory is created not commited!");
                File newfile = new File(FOLDER_PATH +"/test2/samplefile");
                newfile.createNewFile();
            }
        }
        File file2 = new File(FOLDER_PATH +"/test3");
        if (!file2.exists()) {
            if (file2.mkdir()) {
                System.out.println("Directory is created not commited!");
                File newfile = new File(FOLDER_PATH +"/test3/samplefile");
                newfile.createNewFile();
            }
        }
        result.reset().setMode( ResetType.HARD ).call();
        result.add().addFilepattern("test2").call();    
        result.commit().setOnly("test2").setMessage("Added "+ "Project").call();
		
		
		//git.add().addFilepattern(fileName).call();  
		//git.commit().setMessage("Added "+ fileName).call();

		PushCommand pc = result.push();
		pc.setCredentialsProvider(upc).setForce(true);
		pc.call();
		result.getRepository().close();
	}

}
