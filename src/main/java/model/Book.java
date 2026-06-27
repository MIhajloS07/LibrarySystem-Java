package model;

/**
 * Represents a book in the library system.
 * Each book has a title, an author, and a year of publication.
 */
public class Book {
	private String title;
	private String author;
	private int year;
	
	/**
     * Creates a new Book instance with the given title, author, and year.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param year   the year the book was published
     */
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	/**
     * Sets the title of the book.
     *
     * @param title the new title
     */
	public void setTitle(String title) { this.title = title; }
	
	/**
     * Sets the author of the book.
     *
     * @param author the new author
     */
	public void setAuthor(String author) { this.author = author; }
	
	/**
     * Sets the publication year of the book.
     *
     * @param year the new year
     */
	public void setYear(int year) { this.year = year; }
	
	/**
     * Gets the title of the book.
     *
     * @return the book's title
     */
	public String getTitle() {return this.title;}
	
	/**
     * Gets the author of the book.
     *
     * @return the book's author
     */
	public String getAuthor() {return this.author;}
	
	/**
     * Gets the publication year of the book.
     *
     * @return the book's year
     */
	public int getYear() {return this.year;}
	
	/**
     * Returns a string representation of the book,
     * including its title, author, and year.
     *
     * @return formatted string with book details
     */
	@Override
	public String toString() {
		return String.format("title: %s | author: %s | year: %d", 
				this.title, 
				this.author,
				this.year
	    );
	}
}
