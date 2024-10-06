import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore
{
    private static final int DECADE_LOWER_BOUND_ROUNDER = 10;
    private static final int DECADE_UPPER_BOUND_ROUNDER = 9;
    private static final int PERCENTAGE_CONVERTOR       = 100;

    private final String      storeName;
    private final List<Novel> novels;

    public BookStore(final String storeName)
    {
        validateName(storeName);

        this.storeName = storeName;
        this.novels    = Novel.createNovelList();
    }

    public String getStoreName()
    {
        return storeName;
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
        for(Novel novel : novels)
        {
            System.out.println(novel.getTitle().toUpperCase());
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

        for(Novel novel : novels)
        {
            if(novel.getTitle().toLowerCase().contains(titleLowerCase))
            {
                System.out.println(novel.getTitle());
            }
        }
    }

    /**
     * Creates a copy of the instance List of Books. Sorts the copy and then
     * prints all of them in alphabetical order.
     */
    public void printTitlesInAlphaOrder()
    {
        List<Novel> sortedBooks;

        sortedBooks = new ArrayList<>(novels);
        Collections.sort(sortedBooks);

        for(Novel novel : sortedBooks)
        {
            System.out.println(novel.getTitle());
        }
    }

    /**
     * Prints all the novels published in a decade determined by the passed int parameter.
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

        for(Novel novel : novels)
        {
            publicationYear = novel.getYearPublished();

            if(publicationYear >= startOfDecade && publicationYear <= endOfDecade)
            {
                System.out.println(novel.getTitle());
            }
        }
    }

    /**
     * Initializes the longestTitle variable to the first book in the book list.
     * Iterates through all novels to find the longest title and prints the result.
     */
    public void printLongest()
    {
        Novel longestTitle = novels.getFirst();

        for(Novel novel : novels)
        {
            if(novel.getTitle().length() > longestTitle.getTitle().length())
            {
                longestTitle = novel;
            }
        }

        System.out.println(longestTitle.getTitle());
    }

    /**
     * Iterates through the book list to see if any were published in the year passed
     * as a parameter. Returns true if one is found, false if not.
     *
     * @param year to check if
     * @return boolean
     */
    public boolean isThereABookWrittenIn(final int year)
    {
        for(Novel novel : novels)
        {
            if(novel.getYearPublished() == year)
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

        for(Novel novel : novels)
        {
            if(novel.getTitle().toLowerCase().contains(word.toLowerCase()))
            {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Returns a double value of the ratio of novels between the ranges passed as
     * parameters and the total number of novels.
     *
     * @param lowerBound
     * @param upperBound
     * @return double representing the percentage of novels between the lower and upper bounds
     */
    public double whichPercentWrittenBetween(final int lowerBound,
                                             final int upperBound)
    {
        int counter;

        counter = 0;

        for(Novel novel : novels)
        {
            if(novel.getYearPublished() <= upperBound && novel.getYearPublished() >= lowerBound)
            {
                ++counter;
            }
        }

        return ((double)counter / (double) novels.size()) * PERCENTAGE_CONVERTOR;
    }

    /**
     * Returns the oldest book from the book list.
     * @return Book object that is oldest
     */
    public Novel getOldestBook()
    {
        Novel oldestBook;

        oldestBook = novels.getFirst();

        for(Novel novel : novels)
        {
            if(novel.getYearPublished() < oldestBook.getYearPublished())
            {
                oldestBook = novel;
            }
        }
        return oldestBook;
    }

    // TODO add all novels of given length to a List and return the List
    public List<Novel> getBooksThisLength(final int length)
    {
        final List<Novel> novelsWithLength;
        novelsWithLength = new ArrayList<>();

        for(Novel novel : novels)
        {
            if(novel.getTitle().length() == length)
            {
                novelsWithLength.add(novel);
            }

        }
        return novelsWithLength;
    }

    public static void main(String[] args)
    {
        BookStore store;
        final Novel oldest;
        final List<Novel>fifteenCharTitles;

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

        System.out.println("\nReturns if there is a book written in 1950?");
        System.out.println(store.isThereABookWrittenIn(1950));

        System.out.println("\nHow many books contain heart?");
        System.out.println(store.howManyBooksContain("heart"));

        System.out.println("\nPercentage of books written between 1940 and 1950:");
        System.out.println(store.whichPercentWrittenBetween(1940, 1950) + "%");

        System.out.println("\nOldest Book Title");

        oldest = store.getOldestBook();
        System.out.println(oldest.getTitle() + " by " + oldest.getAuthor() + ", " +
                oldest.getYearPublished());

        System.out.println("\nNovels with 15 characters");
        fifteenCharTitles = store.getBooksThisLength(15);
        fifteenCharTitles.forEach(novels -> System.out.println(novels.getTitle()));

    }

}
