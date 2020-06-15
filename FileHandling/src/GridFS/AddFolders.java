package GridFS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class AddFolders {
    final static String FOLDER_PATH = "/home/harini.v/Documents/data/WorkArea/sampleGitProgram/gitIntegration/test";

	 public static void main(String[] args) throws IOException {
		 MongoClient mongoClient = new MongoClient( "localhost" , 5000 );
	//	 DB fetchDB = mongoClient.getDB("meetup");
		 MongoDatabase myDatabase = mongoClient.getDatabase("rent");
		 File gitRepo = new File(FOLDER_PATH);
		 GridFSBucket gridFSFilesBucket = GridFSBuckets.create(myDatabase, "gitRepo");
		// GridFS gfsRepoCopy = new GridFS(fetchDB, "git");
		  //  GridFSInputFile gfsFolder = gfsRepoCopy.createFile(gitRepo);
		  //  gfsFolder.setFilename("gitRepo");
		  //  gfsFolder.save();
		 
		 try {
			    InputStream streamToUploadFrom = new FileInputStream(gitRepo);
			    // Create some custom options
			    GridFSUploadOptions options = new GridFSUploadOptions()
			                                        .chunkSizeBytes(358400)
			                                        .metadata(new Document("type", "Git"));

			    ObjectId fileId = gridFSFilesBucket.uploadFromStream("gitDetails", streamToUploadFrom, options);
			} catch (FileNotFoundException e){
		e.printStackTrace();
			  
			}
		    
		   



}}
