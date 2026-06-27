package jUnitModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Book;
import model.Library;

public class LibraryTest {
	@Test
	public void testConstructor() {
		Library library = new Library();
		assertEquals(0, library.getBooks().size());
	}
	@Test
	public void testGetBooks() {
		Library library = new Library();
		Book book = new Book("1984", "George Orwell", 1949);
		library.getBooks().add(book);
		assertEquals(1, library.getBooks().size());
		assertEquals("1984", library.getBooks().get(0).getTitle());
		assertEquals("George Orwell", library.getBooks().get(0).getAuthor());
		assertEquals(1949, library.getBooks().get(0).getYear());
	}
}
