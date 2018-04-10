package com.ypk.MongoListCollections;

import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	MongoDatabase database = mongoClient.getDatabase("testdb");    	
    	
    	for (String name : database.listCollectionNames()) {	    
    	    System.out.println(name);
    	}
    	
    	mongoClient.close();
    	
    	get_all_db_and_collections();
    	    	
    }
    
    private static void get_all_db_and_collections()
    {
    	System.out.println("Get all databases and their collections");
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	// get the list of database
    	List<String> dbList = mongoClient.getDatabaseNames();

    	// iterating using for each 
    	for(String db : dbList) {
    		// printing the db name
    		System.out.println("Database: " + db);
    		MongoDatabase database = mongoClient.getDatabase(db);	
    		for (String name : database.listCollectionNames()) {    		    
    		    System.out.println("  " + name);
    		}
    	}
    	
    	mongoClient.close();
    	
    }

}
