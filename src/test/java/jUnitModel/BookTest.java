package jUnitModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Book;

public class BookTest {
	@Test
	public void testConstructorAndGetters() {
		Book book = new Book("1984", "George Orwell", 1949);
		assertEquals("1984", book.getTitle());
		assertEquals("George Orwell", book.getAuthor());
		assertEquals(1949, book.getYear());
	}
	@Test
	public void testSetters() {
		Book book = new Book("Old title", "Old Author", 2000);
		book.setTitle("New Title");
		book.setAuthor("New Author");
		book.setYear(2021);
		assertEquals("New Title", book.getTitle());
		assertEquals("New Author", book.getAuthor());
		assertEquals(2021, book.getYear());
	}
	@Test
	public void testToString() {
		Book book = new Book("Test Title", "Test Author", 2022);
		String expected = "title: Test Title | author: Test Author | year: 2022";
		assertEquals(expected, book.toString());
	}
}
