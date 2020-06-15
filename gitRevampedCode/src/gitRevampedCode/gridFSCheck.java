package gitRevampedCode;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
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

public class gridFSCheck {
	final String REMOTE = "https://github.com/Harini1422/myrepo.git";
    //	final String REMOTE = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
 	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit/newfolder";
			/*
			 * File localPath = new File(FILE_PATH); Git git =
			 * Git.cloneRepository().setURI(REMOTE) .setCredentialsProvider(new
			 * UsernamePasswordCredentialsProvider("Harini1422", "Harini_4022"))
			 * .setCloneAllBranches(true) .setDirectory(localPath).call();
			 */
 	 
}
public ObjectId saveFile(InputStream inputStream, String filename, String folder) { 
     GridFSInputFile gInputFile = gridfs.createFile(inputStream, filename);
     gInputFile.put("path", folder);
     gInputFile.save();
     return ObjectId.massageToObjectId( gInputFile.getId() );
 }
