package controller;

import model.Book;
import model.User;
import java.time.LocalDate;

/**
 * Controls user operations related to borrowing and returning books.
 * Each UserController instance manages a single User.
 */
public class UserController implements IUserController{
	private LocalDate dateLocal;
	private User user;
	
	/**
     * Creates a new UserController bound to the given user.
     *
     * @param user the User instance to control
     */
	public UserController(User user) { 
		this.user = user; 
	}
	
	 /**
     * Borrows a book for the user. The book is added to the user's
     * borrowed books list and the action is logged with the current date.
     *
     * @param b the Book object to borrow
     */
	@Override
	public void borrowBook(Book b) {
		dateLocal = LocalDate.now();
		user.getBorrowedBooks().add(b); 
		System.out.println(String.format("%s borrowed a book - %s  | %02d.%02d.%d",
				user.getName(),
				b.getTitle(),
				dateLocal.getDayOfMonth(),
				dateLocal.getMonthValue(),
				dateLocal.getYear()
		));
	}
	
	/**
     * Returns a book by title. If the user has borrowed the book,
     * it is removed from their borrowed list and the action is logged
     * with the current date. If not found, a message is displayed.
     *
     * @param title the title of the book to return
     */
	@Override
	public void returnBook(String title) {
		dateLocal = LocalDate.now();
		Book bookToReturn = null;
		for (Book b : user.getBorrowedBooks()) {
			if (b.getTitle().equals(title)) {
				bookToReturn = b; break;
			}
		}
		if (bookToReturn != null) {
			user.getBorrowedBooks().remove(bookToReturn);
			System.out.println(String.format("%s returned book - %s  | %02d.%02d.%d", 
					user.getName(),
					title,
					dateLocal.getDayOfMonth(),
					dateLocal.getMonthValue(),
					dateLocal.getYear()
			));
	    } else {
	        System.out.println("The user doesn't have book -> " + title);
	    }
	}	
}