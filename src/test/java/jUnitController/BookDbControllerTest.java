package jUnitController;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.Statement;
import controller.DbConnectionController;
import controller.BookDbController;
import model.Book;
import java.util.List;

public class BookDbControllerTest {
	private BookDbController controller;
	
	// This will be executed before each test method, because we need to create table before manipulating 
	@BeforeEach
	public void setup() {
		// this runs before every @Test method
		DbConnectionController contrl = new DbConnectionController();
		controller = new BookDbController();
		controller.createTable();
		// clear table
		try(Connection conn = contrl.connect();
		    Statement stmt = conn.createStatement()){
			stmt.execute("DELETE FROM books");
		} catch (Exception e) {
			fail("Failed to reset books table: " + e.getMessage());
		}
	}
	
	@Test
	public void testInsertAndGetAllBooks() {
		Book book = new Book("1984", "George Orwell", 1949);
		controller.insertBook(book);
		List<Book> books = controller.getAllBooks();
		assertFalse(books.isEmpty(), "Books list should not be empty");
		assertEquals(1, books.size());
		assertEquals(book.getTitle(), books.get(0).getTitle());
		assertEquals(book.getAuthor(), books.get(0).getAuthor());
		assertEquals(book.getYear(), books.get(0).getYear());
	}
	
	@Test
	public void testUpdateBookTitle() {
		Book book = new Book("1984", "George Orwell", 1949);
		controller.insertBook(book);
		controller.updateBookTitle(book, "New Title");
		List<Book> books = controller.getAllBooks();
		assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("New Title")), 
				"Book title should be updated to New Title");
	}
	
	@Test
	public void testUpdateBookAuthor() {
		Book book = new Book("1984", "George Orwell", 1949);
		controller.insertBook(book);
		controller.updateBookAuthor(book, "New Author");
		List<Book> books = controller.getAllBooks();
		assertTrue(books.stream().anyMatch(b -> b.getAuthor().equals("New Author")), 
				"Book author should be updated to New Author");
	}
	
	@Test
	public void testUpdateBookYear() {
		Book book = new Book("1984", "George Orwell", 1949);
		controller.insertBook(book);
		controller.updateBookYear(book, 1950);
		List<Book> books = controller.getAllBooks();
		assertTrue(books.stream().anyMatch(b -> b.getYear() == 1950), 
				"Book year should be updated from 1949 to 1950");
	}
	
	@Test 
	public void testDeleteBook() {
		Book book = new Book("1984", "George Orwell", 1949);
		controller.insertBook(book);
		controller.deleteBook(book.getTitle());
		List<Book> books = controller.getAllBooks();
		assertTrue(books.isEmpty());	
	}
}