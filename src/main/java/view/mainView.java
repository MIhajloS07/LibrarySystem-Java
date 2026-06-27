package view;

import java.util.Scanner;
import model.*;
import controller.*;

/**
 * Entry point for the library management application.
 * Provides a console-based interface for interacting with the library,
 * including options to add, remove, lend, return, sort, and search books.
 */
public class mainView {
	/**
     * Starts the library system console application.
     * Displays the menu and handles user input for various operations:
     * - Add, remove, lend, and return books
     * - Show all books
     * - Sort books by title or year
     * - Find books by author or year
     * - Show borrowed books
     * - Exit the application with countdown
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		// Library
		Library library = new Library();
		LibraryController libraryController = new LibraryController(library);
		// User
		User user = new User("Mihajlo");
		//UserController userController = new UserController(user);
		// Db 
		BookDbController dbController = new BookDbController();
		dbController.createTable(); 
		while (true) {
			MainUI.menuUI();
			int choice = scn.nextInt();
			scn.nextLine();
			
			switch (choice) {
				case 1 -> {
					System.out.print("Enter title: ");
                    String title = scn.nextLine();
                    System.out.print("Enter author: ");
                    String author = scn.nextLine();
                    System.out.print("Enter year: ");
                    int year = scn.nextInt();
                    scn.nextLine();
                    Book book = new Book(title, author, year);
                    libraryController.addBook(book);
                    dbController.insertBook(book);
				}
				case 2 -> {
					System.out.print("Enter title to remove: ");
					String title = scn.nextLine();
					libraryController.removeBook(title);
					dbController.deleteBook(title);
				}
				case 3 -> {
					System.out.print("Enter title to lend: ");
					String title = scn.nextLine();
					libraryController.lendBook(title, user);
				}
				case 4 -> {
					System.out.print("Enter title to return: ");
					String title = scn.nextLine();
					libraryController.returnBook(title, user);
				}
				case 5 -> {
					System.out.print("Books in library: ");
					for (Book b : dbController.getAllBooks()) {
						System.out.println(b.toString());
					}
				}
				case 6 -> {
					libraryController.sortBooksByTitle();
				}
				case 7 -> {
					libraryController.sortBooksByYear();
				}
				case 8 -> {
					System.out.println("Enter a name of author: ");
					String author = scn.nextLine();
					libraryController.findBooksByAuthor(author);
				}
				case 9 -> {
					System.out.println("Enter a year: ");
					int year = scn.nextInt();
					libraryController.findBooksByYear(year);
				}
				case 10 -> {
					System.out.println("Borrowed books:");
					if (user.getBorrowedBooks().isEmpty()) {
						System.out.println("No borrowed books");
					} else {
						for (Book b : user.getBorrowedBooks()) {
							System.out.println(b.toString());
						}
					}
				}
				case 11 -> {
					System.out.println("Exiting...");
					Thread countdownThread = new Thread( () -> {
						try {
							for (int count = 5; count >= 1; count--) {
								System.out.println(String.format("Closing in %d ...", count));
								Thread.sleep(1000);
							}
							System.out.println("GoodBye!");
							scn.close();
							System.exit(0);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					});
					countdownThread.start();
				}
			}
		}
	}
}