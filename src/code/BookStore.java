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

    /**
     * Initializes the longestTitle variable to the first book in the book list.
     * Iterates through all books to find the longest title and prints the result.
     */
    public void printLongest()
    {
        Book longestTitle = books.getFirst();

        for(Book book : books)
        {
            if(book.getTitle().length() > longestTitle.getTitle().length())
            {
                longestTitle = book;
            }
        }

        System.out.println(longestTitle.getTitle());
    }

    /**
     * Iterates through the book list to see if any were published in the year passed
     * as a parameter. Returns true if one is found, false if not.
     *
     * @param year to check if
     * @return
     */
    public boolean isThereABookWrittenIn(final int year)
    {
        for(Book book : books)
        {
            if(book.getYearPublished() == year)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Increments a counter for each title in the book list that containts the
     * word passed as a parameter.
     *
     * @param word String to check if contained in each book title
     * @return number
     */
    public int howManyBooksContain(final String word)
    {
        int counter;

        counter = 0;

        for(Book book : books)
        {
            if(book.getTitle().toLowerCase().contains(word.toLowerCase()))
            {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Returns a double value of the ratio of books between the ranges passed as
     * parameters and the total number of books.
     *
     * @param lowerBound
     * @param upperBound
     * @return double representing the percentage of books between the lower and upper bounds
     */
    public double whichPercentWrittenBetween(final int lowerBound,
                                             final int upperBound)
    {
        int counter;

        counter = 0;

        for(Book book : books)
        {
            if(book.getYearPublished() < upperBound && book.getYearPublished() > lowerBound)
            {
                ++counter;
            }
        }

        return (double)counter / (double)books.size();
    }

    /**
     * Returns the oldest book from the book list.
     * @return Book object that is oldest
     */
    public Book getOldestBook()
    {
        Book oldestBook;

        oldestBook = books.getFirst();

        for(Book book : books)
        {
            if(book.getYearPublished() > oldestBook.getYearPublished())
            {
                oldestBook = book;
            }
        }
        return oldestBook;
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

        System.out.println("\nLongest Book Title");
        store.printLongest();

        System.out.println("\nOldest Book Title");
        store.getOldestBook();
    }
}
