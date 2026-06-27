package controller;

import java.sql.Connection;

/**
 * Defines the contract for establishing a database connection.
 * Implementations provide the logic to connect to a specific database.
 */
public interface IDbConnectionController {
	/**
     * Establishes and returns a database connection.
     *
     * @return a Connection object if successful, otherwise null
     */
	Connection connect();
}
