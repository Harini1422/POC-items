
package gitIntegration;

import java.io.File; 
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class CreateBranch {
	 private static final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
	
	  public static void main(String[] args) throws Exception {
		   File localPath = new File("/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit");
		   Git result = Git.cloneRepository().setURI(REMOTE_URL)
                   .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "sn9KV1TVxrpP15jbiMcW"))
              .setCloneAllBranches(false)
              .setBranch("master")
                   .setDirectory(localPath).call();
		   System.out.println("Having repository: " + result.status());
		   
		   List<Ref> call = result.branchList().call();
           for (Ref ref : call) {
               System.out.println("Branch-Before: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
           }
           result.branchCreate()
           .setName("testbranchNew")
           .setStartPoint("master")
           .call();
           call = result.branchList().call();
           for (Ref ref : call) {
               System.out.println("Branch-After: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
           }
      
       
           
           result.push()
           .setRemote(REMOTE_URL)
           .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
           .setRefSpecs(new RefSpec("testbranchNew:testbranchNew"))
           .call();
           
           FileUtils.deleteDirectory(localPath);
		
	               
}}

