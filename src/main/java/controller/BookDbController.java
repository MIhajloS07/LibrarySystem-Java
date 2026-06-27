package controller;

import model.Book;
import java.sql.*;
import java.util.ArrayList;

/**
 * Provides CRUD operations for books in the SQLite database.
 * Supports creating the table, inserting, updating, deleting, and retrieving books.
 */
public final class BookDbController implements IBookDbController{
	IDbConnectionController dbConn = new DbConnectionController();
	Connection conn = dbConn.connect();
	
	 /**
     * Creates the books table in the database if it does not already exist.
     */
	@Override
	public void createTable() {
		// sql query for create table for books
		String query = "CREATE TABLE IF NOT EXISTS books (" +
					 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
					 "title TEXT NOT NULL," +
					 "author TEXT NOT NULL," +
					 "year INTEGER)";
		try(Connection conn = dbConn.connect();
			Statement stmt = conn.createStatement()) {
			stmt.execute(query);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
     * Inserts a new book into the database.
     *
     * @param book the Book object to insert
     */
	@Override
	public void insertBook(Book book) {
		// sql query for insert values into books table
		String query = "INSERT INTO books(title, author, year) VALUES (?,?,?)";
		try(Connection conn = dbConn.connect();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getAuthor());
				pstmt.setInt(3, book.getYear());
				pstmt.executeUpdate();
				System.out.println("Book added in db");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
     * Retrieves all books from the database.
     *
     * @return a list of Book objects
     */
	@Override
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<>();
		// sql query for select all data from books
		String query = "SELECT * FROM books";
		try(Connection conn = dbConn.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) { // while next row have data
				books.add(new Book(
					rs.getString("title"),
					rs.getString("author"),
					rs.getInt("year")
				));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return books;
	}
	
	/**
     * Updates the title of a book in the database.
     *
     * @param book the Book object to update
     * @param newTitle the new title to set
     */
	@Override
	public void updateBookTitle(Book book, String newTitle) {
		String query = "UPDATE books SET title = ? WHERE title = ?";
		try(Connection conn = dbConn.connect();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, newTitle);
				pstmt.setString(2, book.getTitle());
				pstmt.executeUpdate();
				System.out.println("Book title changed");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
     * Updates the author of a book in the database.
     *
     * @param book the Book object to update
     * @param newAuthor the new author to set
     */
	@Override
	public void updateBookAuthor(Book book, String newAuthor) {
		String query = "UPDATE books SET author = ? WHERE title = ?";
		try(Connection conn = dbConn.connect();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, newAuthor);
				pstmt.setString(2, book.getTitle());
				pstmt.executeUpdate();
				System.out.println("Book author changed");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
     * Updates the publication year of a book in the database.
     *
     * @param book the Book object to update
     * @param year the new year to set
     */
	@Override
	public void updateBookYear(Book book, int year) {
		String query = "UPDATE books SET year = ? WHERE title = ?";
		try(Connection conn = dbConn.connect();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setInt(1, year);
				pstmt.setString(2, book.getTitle());
				pstmt.executeUpdate();
				System.out.println("Book year changed");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
     * Deletes a book from the database by its title.
     *
     * @param title the title of the book to delete
     */
	@Override
	public void deleteBook(String title) {
		// sql query for delete data from books table where title equals to title argument from method
		String query = "DELETE FROM books WHERE title = ?";
		try(Connection conn = dbConn.connect();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, title);
				pstmt.executeUpdate();
				System.out.println("Book deleted from db");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
