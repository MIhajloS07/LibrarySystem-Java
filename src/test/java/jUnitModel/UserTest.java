package jUnitModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.User;
import model.Book;

public class UserTest {
	@Test
	public void testConstructorAndGetters() {
		User user = new User("TestUser");
		assertEquals("TestUser", user.getName());
		assertEquals(1, user.getId()); // ID=1 for first user
	}
	@Test
	public void testBorrowedBooksInitiallyEmpty() {
		User user = new User("TestUser");
		assertNotNull(user.getBorrowedBooks(), "Borrowed books list should not be null");
		assertTrue(user.getBorrowedBooks().isEmpty(), "Borrowed books list should be empty at start");
	}
	@Test
	public void testBorrowedBooksAddBook() {
		User user = new User("TestUser");
		Book book = new Book("1984", "George Orwell", 1949);
		user.getBorrowedBooks().add(book);
		assertEquals(1, user.getBorrowedBooks().size(), "Borrowed books list should contain 1 book");
		assertEquals("1984", user.getBorrowedBooks().get(0).getTitle(), "Book title should match");
	}
}
