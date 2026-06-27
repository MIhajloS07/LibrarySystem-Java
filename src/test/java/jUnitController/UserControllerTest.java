package jUnitController;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.User;
import model.Book;
import controller.UserController;

public class UserControllerTest {
	@Test
	public void testBorrowBook() {
		User user = new User("TestUser");
		UserController controller = new UserController(user);
		Book book = new Book("1984", "George Orwell", 1949);
		controller.borrowBook(book);
		assertEquals(1, user.getBorrowedBooks().size(), "User should have 1 borrowed book");
		assertEquals("1984", user.getBorrowedBooks().get(0).getTitle());
		assertEquals("George Orwell", user.getBorrowedBooks().get(0).getAuthor());
		assertEquals(1949, user.getBorrowedBooks().get(0).getYear());
	}
	@Test
	public void testReturnBook() {
		User user = new User("TestUser");
		UserController controller = new UserController(user);
		Book book = new Book("1984", "George Orwell", 1949);
		controller.borrowBook(book);
		controller.returnBook("1984");
		assertTrue(user.getBorrowedBooks().isEmpty(), "Borrowed books list should be empty after returning");
	}
	@Test
	public void testReturnBookNotBorrowed() {
		User user = new User("TestUser");
		UserController controller = new UserController(user);
		Book book = new Book("1984", "George Orwell", 1949);
		controller.returnBook(book.getTitle());
		assertTrue(user.getBorrowedBooks().isEmpty(), "List should remain empty if book was never borrowed");
	}
	@Test
	public void testBorrowMultipleBooks() {
		User user = new User("TestUser");
		UserController controller = new UserController(user);
		Book book1 = new Book("1984", "George Orwell", 1949);
	    Book book2 = new Book("Brave New World", "Aldous Huxley", 1932);
	    controller.borrowBook(book1);
	    controller.borrowBook(book2);
	    assertEquals(2, user.getBorrowedBooks().size(), "User should have 2 borrowed books");
	    assertEquals(book2.getTitle(), user.getBorrowedBooks().get(1).getTitle());
	}
}
