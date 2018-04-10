package com.ypk.postgres;

import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresAppTestConn {
	
	static class Blog
	{
	  public int id;
	  public String subject;
	  public String permalink;
	}
	
	public static void main(String[] args)
	{
	    //new PostgresAppTestConn();
	    PostgresAppTestConn();
	}
	
	public static void PostgresAppTestConn() 
	{
		Connection conn = null;
		LinkedList listOfBlogs = new LinkedList();
		
	    // connect to the database
	    conn = connectToDatabaseOrDie();
	    if(conn!=null) 
	    	System.out.println("OK");
	    
	    // get the data
	    populateListOfTopics(conn, listOfBlogs);
	    
	    // print the results
	    printTopics(listOfBlogs);
	    
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static Connection connectToDatabaseOrDie()
	{
	    Connection conn = null;
	    try
	    {
	      Class.forName("org.postgresql.Driver");
	      String url = "jdbc:postgresql://192.168.1.114/testdb1";
	      conn = DriverManager.getConnection(url,"heuristic", "g691813");
/*
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT id, subject, permalink FROM blogs ORDER BY id");
		    while ( rs.next() )
		    {
		      Blog blog = new Blog();
		      blog.id        = rs.getInt    ("id");
		      blog.subject   = rs.getString ("subject");
		      blog.permalink = rs.getString ("permalink");
		      //listOfBlogs.add(blog);
		    }
		    rs.close();
		    st.close();
*/	      
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	      System.exit(2);
	    }
	    
	    return conn;
	}
	
	private static void printTopics(LinkedList listOfBlogs)
	{
	  Iterator it = listOfBlogs.iterator();
	  while (it.hasNext())
	  {
	    Blog blog = (Blog)it.next();
	    System.out.println("id: " + blog.id + ", subject: " + blog.subject);
	  }
	}
	
	private static void populateListOfTopics(Connection conn, LinkedList listOfBlogs)
	{
	  try 
	  {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery("SELECT id, subject, permalink FROM blogs ORDER BY id");
	    while ( rs.next() )
	    {
	      Blog blog = new Blog();
	      blog.id        = rs.getInt    ("id");
	      blog.subject   = rs.getString ("subject");
	      blog.permalink = rs.getString ("permalink");
	      listOfBlogs.add(blog);
	    }
	    rs.close();
	    st.close();
	  }
	  catch (SQLException se) {
	    System.err.println("Threw a SQLException creating the list of blogs.");
	    System.err.println(se.getMessage());
	  }
	}


}
