package jUnitController;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import model.*;
import controller.LibraryController;

public class LibraryControllerTest {
	@Test
	public void testContructor() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		assertNotNull(controller, "Controller should be created");
		assertTrue(library.getBooks().isEmpty(), "Library should start empty");
	}
	
	@Test
	public void testAddBook() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		Book book = new Book("1984", "George Orwell", 1949);
		controller.addBook(book);
		assertEquals(1, library.getBooks().size(), "Library should have one book");
		assertEquals(book.getTitle(), library.getBooks().get(0).getTitle());
		assertEquals(book.getAuthor(), library.getBooks().get(0).getAuthor());
		assertEquals(book.getYear(), library.getBooks().get(0).getYear());
	}
	
	@Test 
	public void testRemoveBook() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		Book book = new Book("1984", "George Orwell", 1949);
		controller.addBook(book);
		controller.removeBook(book.getTitle());
		assertEquals(0, library.getBooks().size(), "Library should be empty after removing the book");
	}
	
	@Test 
	public void testRemoveNonExistingBook() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		controller.removeBook("NonExistingTitle");
		assertTrue(library.getBooks().isEmpty(), "Library should remain empty if book does not exist");
	}
	
	@Test
	public void testLendBook() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		Book book = new Book("1984", "George Orwell", 1949);
		User user = new User("TestUser");
		controller.addBook(book);
		controller.lendBook(book.getTitle(), user);
		assertTrue(library.getBooks().isEmpty(), "Library should be empty after lending the book");
		assertEquals(1, user.getBorrowedBooks().size(), "User should have one borrowed book");
		assertEquals(book.getTitle(), user.getBorrowedBooks().get(0).getTitle());
	}
	
	@Test
	public void testReturnBook() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		Book book = new Book("1984", "George Orwell", 1949);
		User user = new User("TestUser");
		controller.addBook(book);
		controller.lendBook(book.getTitle(), user);
		controller.returnBook(book.getTitle(), user);
		assertFalse(library.getBooks().isEmpty(), "Library should not be empty after returning the book");
		assertEquals(0, user.getBorrowedBooks().size(), "User should not have books after returning");
	}
	
	@Test
	public void testFindBooksByAuthor() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		List<Book> books = new ArrayList<>(Arrays.asList(
			new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997),
			new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998),
			new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999)
		));
		controller.addBook(books.get(0));
		controller.addBook(books.get(1));
		controller.addBook(books.get(2));
		List<Book> result = controller.findBooksByAuthor("J.K. Rowling");
		assertEquals(3, result.size(), "Should return 3 books by J.K. Rowling");
		for (Book b : result) 
			assertEquals("J.K. Rowling", b.getAuthor());
		assertEquals(books.get(0).getTitle(), result.get(0).getTitle());
		assertEquals(books.get(1).getTitle(), result.get(1).getTitle());
		assertEquals(books.get(2).getTitle(), result.get(2).getTitle());
	}
	
	@Test
	public void testFindBooksByUnknownAuthor() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		controller.addBook(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997));
		controller.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998));
		controller.addBook(new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999));
		List<Book> result = controller.findBooksByAuthor("George Orwell");
		assertTrue(result.isEmpty(), "Should return empty list for unknown author");
	}
	
	@Test
	public void testFindBooksByYear() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		controller.addBook(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997));
		controller.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998));
		controller.addBook(new Book("Tuesdays with Morrie", "Mitch Albom", 1997));
		List<Book> result = controller.findBooksByYear(1997);
		for (Book b : result) assertEquals(1997, b.getYear());
	}
	
	@Test
	public void testFindBooksByYearNoResults() {
	    Library library = new Library();
	    LibraryController controller = new LibraryController(library);
	    controller.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998));
	    List<Book> result = controller.findBooksByYear(2000);
	    assertTrue(result.isEmpty(), "Should return empty list if no books match the year");
	}
	
	@Test
	public void testSortBooksByYear() {
		Library library = new Library();
		LibraryController controller = new LibraryController(library);
		controller.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998));
	    controller.addBook(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997));
	    controller.addBook(new Book("Tuesdays with Morrie", "Mitch Albom", 1997));
		controller.sortBooksByYear();
		assertEquals(1997, library.getBooks().get(0).getYear());
		assertEquals("Harry Potter and the Philosopher's Stone", library.getBooks().get(0).getTitle());
		assertEquals(1997, library.getBooks().get(1).getYear());
		assertEquals("Tuesdays with Morrie", library.getBooks().get(1).getTitle());
		assertEquals(1998, library.getBooks().get(2).getYear());
		assertEquals("Harry Potter and the Chamber of Secrets", library.getBooks().get(2).getTitle());
	}
	
	@Test
	public void testSortBooksByTitle() {
		 Library library = new Library();
		 LibraryController controller = new LibraryController(library);
		 controller.addBook(new Book("Tuesdays with Morrie", "Mitch Albom", 1997));
		 controller.addBook(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997));
		 controller.addBook(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998));
		 controller.sortBooksByTitle();
		 assertEquals("Harry Potter and the Chamber of Secrets", library.getBooks().get(0).getTitle());
		 assertEquals("Harry Potter and the Philosopher's Stone", library.getBooks().get(1).getTitle());
		 assertEquals("Tuesdays with Morrie", library.getBooks().get(2).getTitle());
	}
}