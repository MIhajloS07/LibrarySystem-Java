package controller;

import model.Book;
import java.util.ArrayList;

/**
 * Defines CRUD operations for managing books in the database.
 * Provides methods for creating the table, inserting, updating,
 * deleting, and retrieving books.
 */
public interface IBookDbController {
	/**
     * Creates the books table in the database if it does not already exist.
     */
	void createTable();
	
	/**
     * Inserts a new book into the database.
     *
     * @param book the Book object to insert
     */
	void insertBook(Book book);
	
	/**
     * Deletes a book from the database by its title.
     *
     * @param title the title of the book to delete
     */
	void deleteBook(String title);
	
	/**
     * Updates the title of a book in the database.
     *
     * @param book the Book object to update
     * @param newTitle the new title to set
     */
	void updateBookTitle(Book book, String newTitle);
	
	/**
     * Updates the author of a book in the database.
     *
     * @param book the Book object to update
     * @param newAuthor the new author to set
     */
	void updateBookAuthor(Book book, String newAuthor);
	
	/**
     * Updates the publication year of a book in the database.
     *
     * @param book the Book object to update
     * @param year the new year to set
     */
	void updateBookYear(Book book, int year);
	
	/**
     * Retrieves all books from the database.
     *
     * @return a list of Book objects
     */
	ArrayList<Book> getAllBooks();	
}
