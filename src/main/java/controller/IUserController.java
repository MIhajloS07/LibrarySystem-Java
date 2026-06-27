package controller;

import model.Book;

/**
 * Defines operations for managing user interactions with books.
 * Provides methods for borrowing and returning books.
 */
public interface IUserController {
	/**
     * Borrows a book for the user.
     * The book is added to the user's borrowed books list.
     *
     * @param b the Book object to borrow
     */
	void borrowBook(Book b);
	
	/**
     * Returns a book by its title.
     * The book is removed from the user's borrowed books list.
     *
     * @param title the title of the book to return
     */
	void returnBook(String title);
}
