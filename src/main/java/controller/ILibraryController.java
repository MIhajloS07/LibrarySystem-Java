package controller;

import java.util.ArrayList;

import model.Book;
import model.User;

/**
 * Defines operations for managing a Library instance.
 * Provides methods for adding, removing, lending, returning,
 * searching, and sorting books.
 */
public interface ILibraryController {
	/**
     * Sorts all books in the library by publication year in ascending order.
     */
	void sortBooksByYear();
	
	/**
     * Sorts all books in the library alphabetically by title.
     */
	void sortBooksByTitle();
	
	/**
     * Adds a book to the library collection.
     *
     * @param b the Book object to add
     */
	void addBook(Book b);
	
	/**
     * Removes a book from the library by its title.
     *
     * @param title the title of the book to remove
     */
	void removeBook(String title);
	
	/**
     * Lends a book to a user. The book is removed from the library
     * and added to the user's borrowed books list.
     *
     * @param title the title of the book to lend
     * @param user the User borrowing the book
     */
	void lendBook(String title, User user);
	
	/**
     * Returns a book from a user back to the library.
     *
     * @param title the title of the book to return
     * @param user the User returning the book
     */
	void returnBook(String title, User user);
	
	/**
     * Finds all books written by the specified author.
     *
     * @param author the author's name
     * @return a list of books by the given author
     */
	ArrayList<Book> findBooksByAuthor(String author);
	
	/**
     * Finds all books published in the specified year.
     *
     * @param year the publication year
     * @return a list of books published that year
     */
	ArrayList<Book> findBooksByYear(int year);
}
