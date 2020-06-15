package gitIntegration;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;


public class ShowBlame {

    public static void main(String[] args) throws IOException, GitAPIException {
        // prepare a new test-repository
        	 final String REMOTE_URL = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
       	     final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
       	     File localPath = new File(FILE_PATH);
                   Git git = Git.cloneRepository().setURI(REMOTE_URL)
                           .setCredentialsProvider(new UsernamePasswordCredentialsProvider("harini.v", "appviewx&123"))
                           .setCloneAllBranches(false)
                           .setBranch("master")
                                .setDirectory(localPath).call();
            BlameCommand blamer = new BlameCommand(git.getRepository());
            ObjectId commitID = git.getRepository().resolve("HEAD~~");
            blamer.setStartCommit(commitID);
            blamer.setFilePath("test.json");
            BlameResult blame = blamer.call();

            // read the number of lines from the given revision, this excludes changes from the last two commits due to the "~~" above
            int lines = countLinesOfFileInCommit(git.getRepository(), commitID, "test.json");
            for (int i = 0; i < lines; i++) {
               
                PersonIdent commiter = blame.getSourceCommitter(i);
                PersonIdent author = blame.getSourceAuthor(i);
              
                System.out.println("Line: " + i + ": " + commiter);
                System.out.println("Line: " + i + ": " + author);
            }

           
        }
    

    private static int countLinesOfFileInCommit(Repository repository, ObjectId commitID, String name) throws IOException {
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit commit = revWalk.parseCommit(commitID);
            RevTree tree = commit.getTree();
            System.out.println("Having tree: " + tree);

            // now try to find a specific file
            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);
                treeWalk.setFilter(PathFilter.create(name));
                if (!treeWalk.next()) {
                    throw new IllegalStateException("Did not find expected file");
                }

                ObjectId objectId = treeWalk.getObjectId(0);
                ObjectLoader loader = repository.open(objectId);

                // load the content of the file into a stream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                loader.copyTo(stream);

                revWalk.dispose();

                return IOUtils.readLines(new ByteArrayInputStream(stream.toByteArray()), "UTF-8").size();
            }
        
    }}}