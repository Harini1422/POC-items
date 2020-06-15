package gitIntegration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.arquillian.algeron.git.PassphraseUserInfo;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.util.FS;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


	 
class SshTransportConfigCallback implements TransportConfigCallback {

    private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
        @Override
        protected void configure(OpenSshConfig.Host hc, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
        }

        @Override
        protected JSch createDefaultJSch(FS fs) throws JSchException {
            JSch jSch = super.createDefaultJSch(fs);
            jSch.addIdentity("/home/harini.v/.ssh/id_rsa");
            return jSch;
        }
    };

    @Override
    public void configure(Transport transport) {
        SshTransport sshTransport = (SshTransport) transport;
        sshTransport.setSshSessionFactory(sshSessionFactory);
    }

}
public class SSHAuth {
    public static void main(String[] args) throws InvalidRemoteException, GitAPIException {
    //	 final String REMOTE_URL = "git@matrix.appviewx.in:harini.v/gitintegrationtest.git";
    //	final String REMOTE_URL = "git@github.com:Harini1422/myrepo.git";
    	final static String REMOTE = "http://matrix.appviewx.in/harini.v/gitintegrationtest.git";
         final String FILE_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/testGit";
         String keyPath = "/home/harini.v/.ssh/id_rsa";
			
			  File localPath = new File(FILE_PATH); TransportConfigCallback
			  transportConfigCallback = new SshTransportConfigCallback(); 
			  Git git =
			  Git.cloneRepository() .setDirectory(localPath)
			  .setTransportConfigCallback(transportConfigCallback) .setURI(REMOTE_URL)
			  .call();
			 
      /*   Path filePath = Paths.get(FILE_PATH);
         Path keyPath1 = Paths.get(keyPath);
        
         Git git = cloneRepository(REMOTE_URL,filePath,"",keyPath1);*/
    }
  //   static Git cloneRepository(final String remoteUrl, final Path localPath, final String passphrase,
    	/*    final Path privateKey) {

    	SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host host, Session session) {
                session.setUserInfo(new PassphraseUserInfo(passphrase));
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                if (privateKey != null) {
                    JSch defaultJSch = super.createDefaultJSch(fs);
                    defaultJSch.addIdentity(privateKey.toFile().getAbsolutePath());
                    return defaultJSch;
                } else {
                    return super.createDefaultJSch(fs);
                }
            }
        };

        try {
            return Git.cloneRepository()
                .setURI(remoteUrl)
                .setTransportConfigCallback(transport -> {
                    SshTransport sshTransport = (SshTransport) transport;
                    sshTransport.setSshSessionFactory(sshSessionFactory);
                })
                .setDirectory(localPath.toFile())
                .call();
        } catch (GitAPIException e) {
            throw new IllegalStateException(e);
        }
}}*/
    		 
    		 
}