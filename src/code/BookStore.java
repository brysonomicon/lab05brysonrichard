import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore
{
    private static final int DECADE_LOWER_BOUND_ROUNDER = 10;
    private static final int DECADE_UPPER_BOUND_ROUNDER = 9;

    private final String     storeName;
    private final List<Book> books;

    public BookStore(final String storeName)
    {
        validateName(storeName);
        this.storeName = storeName;
        this.books = Book.createBookList();
    }

    /*
    Checks to make sure the store name is not null or empty.
     */
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

    /**
     * Creates a copy of the instance List of Books. Sorts the copy and then
     * prints all of them in alphabetical order.
     */
    public void printTitlesInAlphaOrder()
    {
        List<Book> sortedBooks;

        sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks);

        for(Book book : sortedBooks)
        {
            System.out.println(book.getTitle());
        }
    }

    /**
     * Prints all the books published in a decade determined by the passed int parameter.
     * Takes a given decade parameter and rounds it down to the nearest {@value DECADE_LOWER_BOUND_ROUNDER} to determine
     * the lower bound of the decade. Then adds {@value DECADE_UPPER_BOUND_ROUNDER}
     *
     * @param decade a year that falls in the desired decade
     */
    public void printGroupByDecade(final int decade)
    {
        int startOfDecade;
        int endOfDecade;
        int publicationYear;

        startOfDecade = (decade/DECADE_LOWER_BOUND_ROUNDER) * DECADE_LOWER_BOUND_ROUNDER;
        endOfDecade = startOfDecade + DECADE_UPPER_BOUND_ROUNDER;

        for(Book book : books)
        {
            publicationYear = book.getYearPublished();

            if(publicationYear >= startOfDecade && publicationYear <= endOfDecade)
            {
                System.out.println(book.getTitle());
            }
        }
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

        System.out.println("Print all of the book titles in UPPERCASE");
        store.printAllTitles();

        System.out.println("\nBook Titles containing \"The\"");
        store.printBookTitle("the");

        System.out.println("\nAll Titles in Alphabetical Order");
        store.printTitlesInAlphaOrder();

        System.out.println("\nBooks from the 2000s");
        store.printGroupByDecade(2000);
    }
}
