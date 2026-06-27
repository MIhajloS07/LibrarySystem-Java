package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides database connection functionality for the library system.
 * Uses SQLite as the underlying database engine.
 */
public class DbConnectionController implements IDbConnectionController{
	private static final String URL = "jdbc:sqlite:library.db";
	
	/**
     * Establishes a connection to the SQLite database.
     * If the connection is successful, it prints a confirmation message.
     * If an error occurs, it prints the exception message and returns null.
     *
     * @return a Connection object if successful, otherwise null
     */
	@Override
	public Connection connect() {
		try {
			Connection conn = DriverManager.getConnection(URL);
			System.out.println("Connection successful");
			return conn;
		} catch (SQLException ex) {
			System.out.println(String.format("%s", ex.getMessage()));
			return null; 
		}
	}
}
