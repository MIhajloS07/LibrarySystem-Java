package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
import model.*;

/**
 * Provides operations for managing a Library instance.
 * Supports adding, removing, lending, returning, searching, and sorting books.
 */

public class LibraryController implements ILibraryController {
	private Library library;
	private LocalDate dateLocal;
	
	/**
	 * Creates a new LibraryController bound to the given Library.
	 *
	 * @param library the Library instance to control
	 */
	public LibraryController(Library library) { this.library = library; }
	
	/**
	 * Adds a book to the library collection.
	 *
	 * @param b the Book object to add
	 */
	@Override
	public void addBook(Book b) {
		library.getBooks().add(b);
	}
	
	/**
	 * Removes a book from the library by its title.
	 *
	 * @param title the title of the book to remove
	 */
	@Override
	public void removeBook(String title) {
		library.getBooks()
				.removeIf(book -> book.getTitle().equals(title));
	}
	
	/**
	 * Lends a book to a user. The book is removed from the library
	 * and added to the user's borrowed books list.
	 *
	 * @param title the title of the book to lend
	 * @param user the User borrowing the book
	 */
	@Override
	public void lendBook(String title, User user) {
		Book bookToLend = null;
		for (Book b : library.getBooks()) {
			if (b.getTitle().equals(title)) {
				bookToLend = b;	
				break;
			}
		}
		if (bookToLend != null) {
			dateLocal = LocalDate.now();
			user.getBorrowedBooks().add(bookToLend);
			library.getBooks().remove(bookToLend);
			System.out.println(String.format("%s borrowed a book - %s | %02d.%02d.%d", 
					user.getName(),
					title,
					dateLocal.getDayOfMonth(),
					dateLocal.getMonthValue(),
					dateLocal.getYear()
			));
		} else {
		    System.out.println("A book with the title " + title + " was not found in the library.");
		}
	}
	
	/**
	 * Returns a book from a user back to the library.
	 *
	 * @param title the title of the book to return
	 * @param user the User returning the book
	 */
	@Override
	public void returnBook(String title, User user) {
		Book bookToReturn = null;
		for (Book b : user.getBorrowedBooks()) {
			if (b.getTitle().equals(title)) {
				bookToReturn = b;	
				break;
			}
		}
		if (bookToReturn != null) {
			dateLocal = LocalDate.now();
			user.getBorrowedBooks().remove(bookToReturn);
			library.getBooks().add(bookToReturn);
			System.out.println(String.format("%s returned a book - %s | %02d.%02d.%d", 
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
	
	/**
	 * Finds all books written by the specified author.
	 *
	 * @param author the author's name
	 * @return a list of books by the given author
	 */
	@Override
	public ArrayList<Book> findBooksByAuthor(String author) {
		ArrayList<Book> authorBooks = new ArrayList<>();
		for (Book book : library.getBooks()) {
			if (book.getAuthor().equals(author)) authorBooks.add(book);				
		}  return authorBooks;	
	}
	
	/**
	 * Finds all books published in the specified year.
	 *
	 * @param year the publication year
	 * @return a list of books published that year
	 */
	@Override
	public ArrayList<Book> findBooksByYear(int year) {
		ArrayList<Book> booksInYear = new ArrayList<>();
		for (Book book : library.getBooks()) {
			if (book.getYear() == year) {
				booksInYear.add(book);
			} else continue;
		}
		return booksInYear;
	}
	
	/**
	 * Sorts the library's books by publication year in ascending order.
	 */
	@Override
	public void sortBooksByYear() {
		library.getBooks().sort(Comparator.comparingInt(Book::getYear));
	}
	
	/**
	 * Sorts the library's books alphabetically by title.
	 */ 
	@Override
	public void sortBooksByTitle() {
		library.getBooks().sort(Comparator.comparing(Book::getTitle));
	}
}