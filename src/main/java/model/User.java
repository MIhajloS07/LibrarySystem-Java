package model;
import java.util.ArrayList;


/**
 * Represents a user in the library system.
 * Each user has a unique ID, a name, and a list of borrowed books.
 */
public class User {
	private String name;
	private static int userNum = 0;
	private int id;
	private ArrayList<Book> borrowedBooks;
	
	/**
     * Creates a new User with the given name.
     * The ID is automatically generated and incremented to ensure uniqueness.
     *
     * @param name the name of the user
     */
	public User(String name) {
		this.name = name;
		this.id = ++userNum;
		borrowedBooks = new ArrayList<>();
	}
	
	/**
     * Gets the unique ID of the user.
     *
     * @return the user's ID
     */
	public int getId() { return id; }
	
	/**
     * Gets the name of the user.
     *
     * @return the user's name
     */
	public String getName() { return this.name; }
	
	/**
     * Gets the list of books currently borrowed by the user.
     *
     * @return a list of borrowed books
     */
	public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }	
}