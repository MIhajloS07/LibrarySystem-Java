package view;

/**
 * Provides the console-based user interface for the library system.
 * Displays the main menu with available options for interacting with the library.
 */
public class MainUI {
	/**
     * Prints the library menu to the console.
     * The menu includes options for adding, removing, lending, returning,
     * showing, sorting, finding, and exiting the library system.
     */
	public static void menuUI() {
        System.out.println("____ Library Menu ____");
        System.out.println("| 1. Add book        |");
        System.out.println("| 2. Remove book     |");
        System.out.println("| 3. Lend book       |");
        System.out.println("| 4. Return book     |");
        System.out.println("| 5. Show all books  |");
        System.out.println("| 6. Sort by title   |");
        System.out.println("| 7. Sort by year    |");
        System.out.println("| 8. Find by author  |");
        System.out.println("| 9. Find by year    |");
        System.out.println("| 10. Borrowed books |");
        System.out.println("| 11. Exit           |");
        System.out.println("|____________________|");
	}
}
