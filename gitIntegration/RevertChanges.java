package gitIntegration;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class RevertChanges {

    public static void main(String[] args) throws IOException, GitAPIException {
        
            System.out.println("Listing local branches:");
            final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
            final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
               // prepare a new test-repository
               File localPath = new File(FILE_PATH);
              
               Git result = Git.cloneRepository().setURI(REMOTE_URL)
                       .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
                  .setCloneAllBranches(false)
                  .setBranch("master")
                       .setDirectory(localPath).call();
                String fileName = "temptFile.txt";
                File tempFile = new File(result.getRepository().getDirectory().getParentFile(), fileName);
                if(!tempFile.createNewFile()) {
                    throw new IOException("Could not create temporary file " + tempFile);
                }
                Path tempFilePath = tempFile.toPath();

                // write some initial text to it
                String initialText = "Initial Text";
                System.out.println("Writing text [" + initialText + "] to file [" + tempFile.toString() + "]");
                Files.write(tempFilePath, initialText.getBytes());

                // add the file and commit it
                result.add().addFilepattern(fileName).call();
                result.commit().setMessage("Added untracked file " + fileName + "to repo").call();
                
                result.push().setRemote(REMOTE_URL).
        	    setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123")).
        	    setRefSpecs(new RefSpec("refs/heads/master:refs/heads/master")).call();

                // modify the file
                Files.write(tempFilePath, "Some modifications".getBytes(), StandardOpenOption.APPEND);

                // assert that file's text does not equal initialText
                if (initialText.equals(getTextFromFilePath(tempFilePath))) {
                    throw new IllegalStateException("Modified file's text should not equal " +
                            "its original state after modification");
                }

                System.out.println("File now has text [" + getTextFromFilePath(tempFilePath) + "]");

                // revert the changes
                result.checkout().addPath(fileName).call();

                // text should no longer have modifications
                if (!initialText.equals(getTextFromFilePath(tempFilePath))) {
                    throw new IllegalStateException("Reverted file's text should equal its initial text");
                }

                System.out.println("File modifications were reverted. " +
                        "File now has text [" + getTextFromFilePath(tempFilePath) + "]");
                result.push().setRemote(REMOTE_URL).
        	    setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123")).
        	    setRefSpecs(new RefSpec("refs/heads/master:refs/heads/master")).call();
          
                    System.out.println("Committed file "  + " to repository ");
                    FileUtils.deleteDirectory(localPath);
                
            }
	 


    private static String getTextFromFilePath(Path file) throws IOException {
        byte[] bytes = Files.readAllBytes(file);
        CharBuffer chars = Charset.defaultCharset().decode(ByteBuffer.wrap(bytes));
        return chars.toString();
}}