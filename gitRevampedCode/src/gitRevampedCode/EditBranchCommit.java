package gitRevampedCode;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;


public class EditBranchCommit {

    public static void main(String[] args) throws IOException, GitAPIException, URISyntaxException {
     //final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
  	final String REMOTE_URL = "https://github.com/Harini1422/myrepo.git";
     final String FOLDER_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit1";
   //  final String FOLDER_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit/workflows";
        // prepare a new test-repository
        File localPath = new File(FOLDER_PATH);
       
		
		  Git result = Git.cloneRepository().setURI(REMOTE_URL)
		  .setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini1422",
		  "Harini_4022")) .setCloneAllBranches(false) .setBranch("master")
		  .setDirectory(localPath).call();
	
		 
     //    Repository localRepo = new FileRepository(FOLDER_PATH);
       // Git git = new Git(localRepo);
        // add remote repo:
   //     Git git = Git.open(new File(FOLDER_PATH));
     //   FileUtils.copyDirectoryToDirectory(FOLDER_PATH, FOLDER_PATH);
       
        RemoteAddCommand remoteAddCommand = result.remoteAdd();
        remoteAddCommand.setName("origin");
        //remoteAddCommand.setUri(new URIish("https://github.com/.git"));//url corretected
        remoteAddCommand.setUri(new URIish(REMOTE_URL));//url corretected
        // you can add more settings here if needed
        remoteAddCommand.call();
      
        AddCommand add = git.add();
        add.addFilepattern("."); // the directory of your files for recursive add
        add.call();

        CommitCommand commit = git.commit();
        commit.setMessage("Initial import");
        commit.call();
        // push to remote:
        PushCommand pushCommand = git.push();
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini1422", "Harini_4022"));
        // you can add more settings here if needed
        pushCommand.call();
    /*        createCommit(result, "masterFile", str);
	
        result.push().setRemote(REMOTE_URL).
        setCredentialsProvider(new UsernamePasswordCredentialsProvider("Harini1422", "Harini_4022")).
        setRefSpecs(new RefSpec("refs/heads/master:refs/heads/master")).call();
      
                System.out.println("Committed file "  + " to repository ");
            //    FileUtils.deleteDirectory(localPath);
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
    }*/

    }}