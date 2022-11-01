import entity.Book;
import entity.Library;
import validation.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);
    static Validator validator = new Validator();

    public static void printBooks(ArrayList<Book> books) {
        System.out.println("Title          |Genre          |Publisher      |Language       |Take date      |Return date    |Publish year   |ISBN number    |Pages          |Taking Period  |Takings count  |Available      |Authors");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Book book : books) {
            System.out.printf("%-14s |", book.getTitle());
            System.out.printf("%-14s |", book.getGenre());
            System.out.printf("%-14s |", book.getPublisher());
            System.out.printf("%-14s |", book.getLanguage());
            System.out.printf("%-14s |", book.getTakeDate());
            System.out.printf("%-14s |", book.getReturnDate());
            System.out.printf("%-14s |", book.getPublishYear());
            System.out.printf("%-14s |", book.getIsbnNumber());
            System.out.printf("%-14s |", book.getPagesCount());
            System.out.printf("%-14s |", book.getTakingPeriod());
            System.out.printf("%-14s |", book.getTakingsCount());
            System.out.printf("%-14s |", (book.isAvailable()) ? "yes" : "no");
            System.out.printf("%-15s ", book.getAuthors());

            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static Library createLibrary() {
        System.out.println("Hello, please enter library info!");

        System.out.println("Library name:");
        String libraryName = scanner.nextLine();
        while (!validator.validateLibraryName(libraryName)) {
            System.out.println("Invalid name! Try again:");
            libraryName = scanner.nextLine();
        }

        System.out.println("Library address:");
        String libraryAddress = scanner.nextLine();
        while (!validator.validateLibraryAddress(libraryAddress)) {
            System.out.println("Invalid address! Try again:");
            libraryAddress = scanner.nextLine();
        }

        System.out.println("Employees count:");
        int libraryEmployeesCount = scanner.nextInt();
        scanner.nextLine();

        while (!validator.validateLibraryEmployeesCount(libraryEmployeesCount)) {
            System.out.println("Invalid employees count! Try again:");
            libraryEmployeesCount = scanner.nextInt();
            scanner.nextLine();
        }

        return new Library(libraryName, libraryAddress, libraryEmployeesCount);
    }

    public static void addBook(Library library) {
        System.out.println("Book title:");
        String title = scanner.nextLine();
        while (!validator.validateBookTitle(title)) {
            System.out.println("Invalid title! Try again:");
            title = scanner.nextLine();
        }

        System.out.println("Book genre:");
        String genre = scanner.nextLine();

        System.out.println("Book authors separated by ';' :");
        String authors = scanner.nextLine();
        while (!validator.validateBookAuthors(authors)) {
            System.out.println("Invalid authors data! Try again:");
            authors = scanner.nextLine();
        }

        System.out.println("Book publisher:");
        String publisher = scanner.nextLine();
        while (!validator.validateBookPublisher(publisher)) {
            System.out.println("Invalid publisher! Try again:");
            publisher = scanner.nextLine();
        }

        System.out.println("Book language:");
        String language = scanner.nextLine();

        System.out.println("Book take date:");
        String takeDate = scanner.nextLine();

        System.out.println("Book return date:");
        String returnDate = scanner.nextLine();

        System.out.println("Book ISBN number:");
        String isbnNumber = scanner.nextLine();
        while (!validator.validateBookISBNNumber(isbnNumber)) {
            System.out.println("Invalid ISBN number! Try again:");
            isbnNumber = scanner.nextLine();
        }

        System.out.println("Book publish year:");
        int publishYear = scanner.nextInt();
        while (!validator.validateBookPublishYear(publishYear)) {
            System.out.println("Invalid publish year! Try again:");
            publishYear = scanner.nextInt();
        }

        System.out.println("Book pages count:");
        int pagesCount = scanner.nextInt();
        while (!validator.validateBookPageCount(pagesCount)) {
            System.out.println("Invalid pages count! Try again:");
            pagesCount = scanner.nextInt();
        }

        System.out.println("Book taking period (days):");
        int takingPeriod = scanner.nextInt();

        System.out.println("Book takings count:");
        int takingsCount = scanner.nextInt();
        scanner.nextLine();
        while (!validator.validateBookTakingsCount(takingsCount)) {
            System.out.println("Invalid takings count! Try again:");
            takingsCount = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Book availability(true/false):");
        String availableString = scanner.nextLine();

        boolean available = availableString.equals("true");

        Book book = new Book(title, genre, authors, publisher, language,
                takeDate, returnDate, publishYear, isbnNumber, pagesCount, takingPeriod,
                takingsCount, available);

        library.addBook(book);
    }

    public static void removeBook(Library library) {
        System.out.println("Choose criteria to remove book by:");
        System.out.println("1) Title");
        System.out.println("2) ISBN number");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Book book = null;

        System.out.println("Enter value:");
        String value = scanner.nextLine();

        if (choice == 1) {
            book = library.findBook("title", value).get(0);
        } else if (choice == 2) {
            book = library.findBook("isbn", value).get(0);
        }

        if (book == null) {
            System.out.println("This book is not in the library!");
        } else {
            library.removeBook(book);
            System.out.println("You successfully removed " + book.getTitle());
        }
    }

    public static void findBooks(Library library) {
        System.out.println("Choose criteria to search for book by:");
        System.out.println("1) Title");
        System.out.println("2) ISBN number");
        System.out.println("3) Author");
        System.out.println("4) Publish year");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter value:");
        String value = scanner.nextLine();

        String criteria = switch (choice) {
            case 1 -> "title";
            case 2 -> "isbn";
            case 3 -> "author";
            case 4 -> "year";
            default -> "";
        };

        ArrayList<Book> books = library.findBook(criteria, value);
        if (books.size() == 0) {
            System.out.println("No books found!");
        } else {
            printBooks(books);
        }
    }

    public static void updateBook(Library library) {
        System.out.println("Enter ISBN number of the book you want to edit:");
        String isbn = scanner.nextLine();

        ArrayList<Book> books = library.findBook("isbn", isbn);
        Book book;
        if (books.size() == 0) {
            System.out.println("There isn't a book with ISBN number " + isbn + " in the library!");
        } else {
            book = books.get(0);
            System.out.println("What do you want to change?");
            System.out.println("1) Availability");
            System.out.println("2) Take date");
            System.out.println("3) Return date");
            System.out.println("4) Taking period");
            System.out.println("5) Takings count");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter new value:");
            String value = scanner.nextLine();

            String property = switch (choice) {
                case 1 -> "available";
                case 2 -> "takeDate";
                case 3 -> "returnDate";
                case 4 -> "takingPeriod";
                case 5 -> "takingsCount";
                default -> "";
            };

            while (!library.updateBook(book, property, value)) {
                System.out.println("Invalid value! Try again:");
                value = scanner.nextLine();
            }

            System.out.println("Book successfully updated!");
        }

    }

    public static void main(String[] args) {
        Library library = createLibrary();

        int choice;
        do {
            System.out.println("---------------------------");
            System.out.println("Choose action:");
            System.out.println("1) Add book");
            System.out.println("2) Remove book");
            System.out.println("3) Find book");
            System.out.println("4) Update book");
            System.out.println("5) Print all books");
            System.out.println("6) Quit");
            System.out.println("---------------------------");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook(library);
                case 2 -> removeBook(library);
                case 3 -> findBooks(library);
                case 4 -> updateBook(library);
                case 5 -> library.printBooks();
            }


        } while (choice != 6);

        System.out.println("Goodbye! Have a nice day!");
    }
}
