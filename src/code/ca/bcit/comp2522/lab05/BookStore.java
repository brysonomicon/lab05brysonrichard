package ca.bcit.comp2522.lab05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;


/**
 * The {@code BookStore} class represents a store containing a collection of novels.
 * It provides methods to search, sort, and print information about the novels.
 *
 * @author Bryson Lindy
 * @author Richard Ho
 * @author Phyo Thu Kha
 *
 * @version 1.0
 */
public class BookStore
{
    private static final int ROUND_LOWER_BOUND    = 10;
    private static final int ROUND_UPPER_BOUND    = 9;
    private static final int PERCENTAGE_CONVERTOR = 100;
    private static final int COUNT_ZERO           = 0;

    private final String             storeName;
    private final List<Novel>        novels;
    private final Map<String, Novel> novelsMap;
    private final Set<String>        keySet;
    private final List<String>       keyList;

    /**
     * Constructs a {@code BookStore} with the given store name.
     * Initializes the list of novels using {@code Novel.createNovelList()}.
     *
     * @param storeName the name of the bookstore
     */
    public BookStore(final String storeName)
    {
        validateName(storeName);

        this.storeName = storeName;
        this.novels    = Novel.createNovelList();

        this.novelsMap = new HashMap<>();
        this.populateNovelsMap(novelsMap);

        this.keySet    = novelsMap.keySet();
        this.keyList   = new ArrayList<>(keySet);
        Collections.sort(keyList);
    }

    /**
     * Returns the name of the bookstore.
     *
     * @return the name of the bookstore
     */
    public String getStoreName()
    {
        return storeName;
    }

    /*
    Checks to make sure the store name is not null or empty.
     */
    private static void validateName(final String storeName)
    {
        final boolean storeNameIsEmpty;

        storeNameIsEmpty = storeName.trim().isEmpty();

        if(storeName == null || storeNameIsEmpty)
        {
            throw new IllegalArgumentException("Store name cannot be null or empty");
        }
    }

    /*
     * Helper method to populate hashmap with Novels
     */
    private void populateNovelsMap(final Map<String, Novel> novelMap)
    {
        final boolean novelMapIsEmpty;

        novelMapIsEmpty = novelMap.isEmpty();

        if(novelMap != null && novelMapIsEmpty)
        {
            for(final Novel novel : novels)
            {
                final String title;

                title = novel.getTitle();

                novelMap.put(title, novel);
            }
        }
    }

    /**
     * Iterates through the BookStore List and prints all the titles in UPPERCASE.
     */
    public void printAllTitles()
    {
        for(final Novel novel : novels)
        {
            final String titleUpperCase;

            titleUpperCase = novel.getTitle().toUpperCase();

            System.out.println(titleUpperCase);
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
        final String titleLowerCase;

        titleLowerCase = title.toLowerCase();

        for(final Novel novel : novels)
        {
            final String novelTitleLowerCase;

            novelTitleLowerCase = novel.getTitle().toLowerCase();

            if(novelTitleLowerCase.contains(titleLowerCase))
            {
                final String novelTitle;

                novelTitle = novel.getTitle();

                System.out.println(novelTitle);
            }
        }
    }

    /**
     * Creates a copy of the instance List of Books. Sorts the copy and then
     * prints all of them in alphabetical order.
     */
    public void printTitlesInAlphaOrder()
    {
        final List<Novel> sortedBooks;

        sortedBooks = new ArrayList<>(novels);
        Collections.sort(sortedBooks);

        for(final Novel novel : sortedBooks)
        {
            System.out.println(novel.getTitle());
        }
    }

    /**
     * Prints all the novels published in a decade determined by the passed int parameter.
     * Takes a given decade parameter and rounds it down to the nearest {@value ROUND_LOWER_BOUND} to determine
     * the lower bound of the decade. Then adds {@value ROUND_UPPER_BOUND}
     *
     * @param decade a year that falls in the desired decade
     */
    public void printGroupByDecade(final int decade)
    {
        final int startOfDecade;
        final int endOfDecade;
        int       publicationYear;

        startOfDecade = (decade / ROUND_LOWER_BOUND) * ROUND_LOWER_BOUND;
        endOfDecade   = startOfDecade + ROUND_UPPER_BOUND;

        for(final Novel novel : novels)
        {
            publicationYear = novel.getYearPublished();

            if(publicationYear >= startOfDecade && publicationYear <= endOfDecade)
            {
                final String title;

                title = novel.getTitle();

                System.out.println(title);
            }
        }
    }

    /**
     * Initializes the longestTitle variable to the first book in the book list.
     * Iterates through all novels to find the longest title and prints the result.
     */
    public void printLongest()
    {
        Novel longestNovel;

        longestNovel = novels.getFirst();

        for(final Novel novel : novels)
        {
            final int novelTitleLength;
            final int longestNovelTitleLength;

            novelTitleLength        = novel.getTitle().length();
            longestNovelTitleLength = longestNovel.getTitle().length();

            if(novelTitleLength > longestNovelTitleLength)
            {
                longestNovel = novel;
            }
        }

        System.out.println(longestNovel);
    }

    /**
     * Iterates through the book list to see if any were published in the year passed
     * as a parameter. Returns true if one is found, false if not.
     *
     * @param year int of the year to check if the book was written in
     * @return boolean
     */
    public boolean isThereABookWrittenIn(final int year)
    {
        for(final Novel novel : novels)
        {
            final int novelYearPublished;

            novelYearPublished = novel.getYearPublished();

            if(novelYearPublished == year)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Increments a counter for each title in the book list that contains the
     * word passed as a parameter.
     *
     * @param word String to check if contained in each book title
     * @return number
     */
    public int howManyBooksContain(final String word)
    {
        int          counter;
        final String wordLowerCase;

        counter       = COUNT_ZERO;
        wordLowerCase = word.toLowerCase();

        for(final Novel novel : novels)
        {
            final String novelTitle;

            novelTitle = novel.getTitle().toLowerCase();

            if(novelTitle.contains(wordLowerCase))
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
     * @param lowerBound int that indicates the lower limit of years
     * @param upperBound int that indicates the upper limit of years
     * @return double representing the percentage of novels between the lower and upper bounds
     */
    public double whichPercentWrittenBetween(final int lowerBound,
                                             final int upperBound)
    {
        int          counter;
        final double novelsSize;

        counter    = COUNT_ZERO;
        novelsSize = novels.size();

        for(final Novel novel : novels)
        {
            final int novelYearPublished;

            novelYearPublished = novel.getYearPublished();

            if(novelYearPublished <= upperBound && novelYearPublished >= lowerBound)
            {
                ++counter;
            }
        }

        return (counter / novelsSize) * PERCENTAGE_CONVERTOR;
    }

    /**
     * Returns the oldest book from the book list.
     * @return Book object that is oldest
     */
    public Novel getOldestBook()
    {
        Novel oldestNovel;

        oldestNovel = novels.getFirst();

        for(final Novel novel : novels)
        {
            final int novelYearPublished;
            final int oldestNovelYearPublished;

            novelYearPublished       = novel.getYearPublished();
            oldestNovelYearPublished = oldestNovel.getYearPublished();

            if (novelYearPublished < oldestNovelYearPublished)
            {
                oldestNovel = novel;
            }
        }

        return oldestNovel;
    }

    /**
     * Returns a list of novels with titles of the specified length.
     *
     * @param length the desired title length
     * @return a list of novels with titles of the given length
     */
    public List<Novel> getBooksThisLength(final int length)
    {
        final List<Novel> novelsWithLength;

        novelsWithLength = new ArrayList<>();

        for(final Novel novel : novels)
        {
            final int novelTitleLength;

            novelTitleLength = novel.getTitle().length();

            if(novelTitleLength == length)
            {
                novelsWithLength.add(novel);
            }
        }

        return novelsWithLength;
    }

    /**
     * Main method to test the functionality of the {@code BookStore} class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        final BookStore   store;
        final String      storeName;
        final Novel       oldest;
        final List<Novel> fifteenCharTitles;

        store     = new BookStore("Books and Books and Books");
        storeName = store.getStoreName();
        System.out.printf("Welcome to %s\n", storeName);

        System.out.println("Print all of the book titles in UPPERCASE");
        store.printAllTitles();

        System.out.println("\nBook Titles containing \"The\"");
        store.printBookTitle("The");

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
        System.out.println(oldest);

        System.out.println("\nNovels with 15 characters");
        fifteenCharTitles = store.getBooksThisLength(15);
        {
            final Iterator<Novel> it;

            it = fifteenCharTitles.iterator();

            while(it.hasNext())
            {
                final Novel novel;

                novel = it.next();

                System.out.println(novel);
            }
        }
//        fifteenCharTitles.forEach(novels -> System.out.println(novels.getTitle()));

        System.out.println("\nLab Part 2: BookShop");
        System.out.println("Iterator and HashMap.\nPrint All Titles");
        {
            final Iterator<String> it;

            it = store.keyList.iterator();

            while(it.hasNext())
            {
                final String key;
                final String valueTitle;

                key        = it.next();
                valueTitle = store.novelsMap.get(key).getTitle();

                System.out.println(valueTitle);
            }
        }

        System.out.println("\nAll titles containing \"The\" filtered out:");
        {
            final Iterator<String> it;

            it = store.keyList.iterator();

            while(it.hasNext())
            {
                final String key;
                final String keyLowerCase;

                key          = it.next();
                keyLowerCase = key.toLowerCase();

                if(keyLowerCase.contains("the"))
                {
                    it.remove();
                    store.novelsMap.remove(key);
                }
            }

            for(final String key : store.keyList)
            {
                final Novel value;

                value = store.novelsMap.get(key);

                System.out.println(value);
            }
        }

    }
}