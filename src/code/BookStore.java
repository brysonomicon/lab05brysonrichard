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

    public static void main(String[] args)
    {

    }


}
