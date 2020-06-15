package gitIntegration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;


public class EditBranchCommit {

    public static void main(String[] args) throws IOException, GitAPIException {
     final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
        // prepare a new test-repository
        File localPath = new File(FILE_PATH);
       
        Git result = Git.cloneRepository().setURI(REMOTE_URL)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
           .setCloneAllBranches(false)
           .setBranch("master")
                .setDirectory(localPath).call();
                // create the file
        String str = "Editted file to commit";
        
            createCommit(result, "masterFile", str);
	
        result.push().setRemote(REMOTE_URL).
        setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123")).
        setRefSpecs(new RefSpec("refs/heads/master:refs/heads/master")).call();
      
                System.out.println("Committed file "  + " to repository ");
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
                .setMessage("Added " + fileName).setAuthor("harini", "harini.venkatraman@appviewx.com")
                .call();

        System.out.println("Committed file " + myFile + " as " + revCommit + " to repository at " + git.getRepository().getDirectory());
    }

}
