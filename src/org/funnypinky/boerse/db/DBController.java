package org.funnypinky.boerse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
	
	private static final DBController dbcontroller = new DBController();
	private static Connection connection;
    private static final String DB_PATH = "database/" + "database.db";
    
    private static final String createFirstTable = "CREATE TABLE company (\r\n" + 
    		"    CompanyName     STRING PRIMARY KEY\r\n" + 
    		"                           UNIQUE,\r\n" + 
    		"    Waehrung        STRING,\r\n" + 
    		"    Land            STRING,\r\n" + 
    		"    Sektor          STRING,\r\n" + 
    		"    Buchwert        DOUBLE,\r\n" + 
    		"    Diviende        DOUBLE,\r\n" + 
    		"    Divienderendite DOUBLE\r\n" + 
    		");";
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }
    
    private DBController(){
    }
    
    public static DBController getInstance(){
        return dbcontroller;
    }
    
    public void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
