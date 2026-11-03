import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private static int totalBooksIssued = 0; // Static counter for library stats

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public static int getTotalBooksIssued() {
        return totalBooksIssued;
    }

    // Validation helpers
    public Book findBookById(String id) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    public Member findMemberById(String id) {
        for (Member m : members) {
            if (m.getId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    // Add Book
    public boolean addBook(String id, String title, String author, int copies) {
        if (findBookById(id) != null) {
            System.out.println("Error: Book ID already exists.");
            return false;
        }
        books.add(new Book(id, title, author, copies));
        return true;
    }

    // Add Member
    public boolean addMember(String id, String name) {
        if (findMemberById(id) != null) {
            System.out.println("Error: Member ID already exists.");
            return false;
        }
        members.add(new Member(id, name));
        return true;
    }

    // Issue Book
    public boolean issueBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Error: Member not found.");
            return false;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found.");
            return false;
        }

        if (!member.canBorrow()) {
            System.out.println("Error: Member has reached the maximum borrowing limit of 3 books.");
            return false;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("Error: No copies of this book are available.");
            return false;
        }

        book.issueCopy();
        member.addBorrowedBook(bookId);
        totalBooksIssued++;
        System.out.println("Book \"" + book.getTitle() + "\" issued to " + member.getName() + 
                           ". Copies left: " + book.getAvailableCopies());
        return true;
    }

    // Return Book & Fine Calculation
    public boolean returnBook(String memberId, String bookId, int daysKept) {
        Member member = findMemberById(memberId);
        if (member == null || !member.getBorrowedBookIds().contains(bookId)) {
            System.out.println("Error: Book not recorded as borrowed by this member.");
            return false;
        }

        Book book = findBookById(bookId);
        if (book == null) return false;

        // Calculate fine (Rs. 2 per day after 7 days)
        int fine = 0;
        if (daysKept > 7) {
            int lateDays = daysKept - 7;
            fine = lateDays * 2;
            System.out.println("Late by " + lateDays + " days. Fine = Rs. " + fine);
        } else {
            System.out.println("Returned on time. No fine.");
        }

        book.returnCopy();
        member.removeBorrowedBook(bookId);
        System.out.println("Book returned successfully.");
        return true;
    }

    // Overloaded Search 1: Search by ID
    public void searchBookById(String id) {
        Book b = findBookById(id);
        if (b != null) {
            System.out.println("Found: " + b);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    // Overloaded Search 2: Search by Title Keyword
    public void searchBookByTitle(String keyword) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books matching keyword: " + keyword);
        }
    }

    public void showAll() {
        System.out.println("\n--- All Books ---");
        if (books.isEmpty()) System.out.println("No books in catalogue.");
        for (Book b : books) System.out.println(b);

        System.out.println("\n--- All Members ---");
        if (members.isEmpty()) System.out.println("No registered members.");
        for (Member m : members) System.out.println(m);
        
        System.out.println("\nTotal overall books issued: " + totalBooksIssued);
    }
}