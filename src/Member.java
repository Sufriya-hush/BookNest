import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private List<String> borrowedBookIds;
    private static final int MAX_BORROW_LIMIT = 3;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getBorrowedBookIds() { return borrowedBookIds; }

    public boolean canBorrow() {
        return borrowedBookIds.size() < MAX_BORROW_LIMIT;
    }

    public boolean addBorrowedBook(String bookId) {
        if (canBorrow()) {
            borrowedBookIds.add(bookId);
            return true;
        }
        return false;
    }

    public boolean removeBorrowedBook(String bookId) {
        return borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("Member ID: %-6s | Name: %-15s | Borrowed: %d/%d books", 
                id, name, borrowedBookIds.size(), MAX_BORROW_LIMIT);
    }
}