import java.util.Scanner;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        int choice = -1;

        // main menu loop (do-while with switch statement)
        do {
            printMenu();
            choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    handleAddBook();
                    break;
                case 2:
                    handleAddMember();
                    break;
                case 3:
                    handleIssueBook();
                    break;
                case 4:
                    handleReturnBook();
                    break;
                case 5:
                    handleSearch();
                    break;
                case 6:
                    library.showAll();
                    break;
                case 7:
                    System.out.println("Exiting BookNest. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 7.");
            }
        } while (choice != 7);
    }

    private static void printMenu() {
        System.out.println("\n===== BookNest Library =====");
        System.out.println("1. Add Book    2. Add Member   3. Issue Book");
        System.out.println("4. Return Book 5. Search       6. Show All   7. Exit");
    }

    private static void handleAddBook() {
        String id = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Book Title: ");
        String author = getStringInput("Enter Author Name: ");
        int copies = getIntInput("Enter Number of Copies: ");

        if (copies <= 0) {
            System.out.println("Error: Number of copies must be positive.");
            return;
        }
        library.addBook(id, title, author, copies);
    }

    private static void handleAddMember() {
        String id = getStringInput("Enter Member ID: ");
        String name = getStringInput("Enter Member Name: ");

        if (name.trim().isEmpty()) {
            System.out.println("Error: Member name cannot be empty.");
            return;
        }
        library.addMember(id, name);
    }

    private static void handleIssueBook() {
        String memberId = getStringInput("Enter Member ID: ");
        String bookId = getStringInput("Enter Book ID : ");
        library.issueBook(memberId, bookId);
    }

    private static void handleReturnBook() {
        String memberId = getStringInput("Enter Member ID: ");
        String bookId = getStringInput("Enter Book ID : ");
        int days = getIntInput("Days kept: ");

        if (days < 0) {
            System.out.println("Error: Days kept cannot be negative.");
            return;
        }
        library.returnBook(memberId, bookId, days);
    }

    private static void handleSearch() {
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Title Keyword");
        int option = getIntInput("Choose search method: ");

        if (option == 1) {
            String id = getStringInput("Enter Book ID: ");
            library.searchBookById(id);
        } else if (option == 2) {
            String keyword = getStringInput("Enter Title Keyword: ");
            library.searchBookByTitle(keyword);
        } else {
            System.out.println("Invalid search choice.");
        }
    }

    // Helper method to safely read integers without crashing on text input
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Helper method to read strings
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}