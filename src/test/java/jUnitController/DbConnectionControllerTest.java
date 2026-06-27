package jUnitController;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.DbConnectionController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionControllerTest {
	@Test
	public void testConnectionToDatabase() {
		DbConnectionController dbController = new DbConnectionController();
		try (Connection conn = dbController.connect()) {
			assertNotNull(conn, "Connection should not be null");
			assertTrue(conn.isValid(2), "Connection should be valid");
			try(Statement stmt = conn.createStatement();
			    ResultSet rs = stmt.executeQuery("Select 1")) {
				assertTrue(rs.next(), "Query should return a result");
				assertEquals(1, rs.getInt(1), "Result should be 1");
			}
		} catch (SQLException ex) {
			fail(String.format("Database connection failed: %s", ex.getMessage()));
		}
	}
}
