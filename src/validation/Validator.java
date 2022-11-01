package validation;

public class Validator {

    // Library
    public boolean validateLibraryName(String name) {
        return !name.equals("") && name.length() <= 100;
    }

    public boolean validateLibraryAddress(String address) {
        return !address.equals("") && address.length() <= 500;
    }

    public boolean validateLibraryEmployeesCount(int count) {
        return count > 0 && count < 50;
    }

    // Book
    public boolean validateBookTitle(String title) {
        return !title.equals("") && title.length() <= 100;
    }

    public boolean validateBookAuthors(String authors) {
        String[] parts = authors.split(";");
        for (String author : parts) {
            if (author.equals("") || author.length() > 50) {
                return false;
            }
        }
        return true;
    }

    public boolean validateBookPublisher(String publisher) {
        return !publisher.equals("") && publisher.length() <= 50;
    }

    public boolean validateBookPublishYear(int year) {
        return year > 0 && year <= 2022;
    }

    public boolean validateBookISBNNumber(String isbnNumber) {
        return !isbnNumber.equals("") && isbnNumber.length() <= 10;
    }

    public boolean validateBookPageCount(int pageCount) {
        return pageCount > 0;
    }

    public boolean validateBookTakingsCount(int takingsCount) {
        return takingsCount >= 0;
    }


}
