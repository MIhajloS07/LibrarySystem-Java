package model;
import java.util.ArrayList;

/**
 * Represents a library that stores a collection of books.
 * Provides access to the list of books.
 */
public class Library {
	protected ArrayList<Book> books;
	
	/**
     * Creates a new Library instance with an empty collection of books.
     */
	public Library() { books = new ArrayList<>(); }
	
	/**
     * Gets the list of books in the library.
     *
     * @return an ArrayList containing all books in the library
     */
	public ArrayList<Book> getBooks() { return books; }
}