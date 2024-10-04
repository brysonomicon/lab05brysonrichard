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

    // TODO prints all titles in UPPERCASE
    public void printAllTitles()
    {

    }

    // TODO prints all Book titles that contain the pass parameter
    public void printBookTitle(final String title)
    {

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

    }
}
