import java.util.ArrayList;
import java.util.List;

public class BookStore
{
    private final String     storeName;
    private final List<Book> books;

    public BookStore(final String storeName)
    {
        validateName(storeName);
        this.storeName = storeName;
        this.books = Book.createBookList();
    }

    private static void validateName(final String storeName)
    {
        if(storeName == null || storeName.isEmpty())
        {
            throw new IllegalArgumentException("Store name cannot be null or empty");
        }
    }

    /**
     * Iterates through the BookStore List and prints all of the titles in UPPERCASE.
     */
    public void printAllTitles()
    {
        for(Book book : books)
        {
            System.out.println(book.getTitle().toUpperCase());
        }
    }

    /**
     * Iterates through the BookStore List and prints all titles that contain the
     * parameter passed as a String.
     *
     * @param title what we check to see if each title in the book store list contains
     */
    public void printBookTitle(final String title)
    {
        String titleLowerCase;

        titleLowerCase = title.toLowerCase();

        for(Book book : books)
        {
            if(book.getTitle().toLowerCase().contains(titleLowerCase))
            {
                System.out.println(book.getTitle());
            }
        }
    }

    // TODO prints all titles in alphabetical order
    public void printTitlesInAlphaOrder()
    {

    }

    // TODO prints all books of a given decade
    // ie 2000 would print 2000-2009
    public void printGroupByDecade(final int decade)
    {

    }

    // TODO return the longest string in the bookstore
    public String getLongest()
    {
        return "";
    }

    // TODO return true if a book was written in the given year, false if not
    public boolean isThereABookWrittenIn(final int year)
    {
        return false;
    }

    // TODO return an int of how many books contain the work passed as parameter
    public int howManyBooksContain(final String word)
    {
        return 0;
    }

    // TODO return percentage of total books written between ranges as a double
    public double whichPercentWrittenBetween(final int first, final int last)
    {
        return 0;
    }

    // TODO return the oldest book in the bookstore
    public Book getOldestBook()
    {
        return null;
    }

    // TODO add all books of given length to a List and return the List
    public List<Book> getBooksThisLength(final int length)
    {
        return null;
    }

    public static void main(String[] args)
    {
        BookStore store;

        store = new BookStore("Books and Books and Books");

//        System.out.println("Print all of the book titles in UPPERCASE");
//        store.printAllTitles();

        System.out.println("\nBook Titles containing \"The\"");
        store.printBookTitle("the");
    }
}
