package entity;

public class Book {
    private String title;
    private String genre;
    private String[] authors;
    private String publisher;
    private String language;
    private String takeDate;
    private String returnDate;
    private String isbnNumber;
    private int publishYear;
    private int pagesCount;
    private int takingPeriod;
    private int takingsCount;
    private boolean available;

    // Constructor
    public Book(String title, String genre, String authors, String publisher, String language, String takeDate,
                String returnDate, int publishYear, String isbnNumber, int pagesCount, int takingPeriod,
                int takingsCount, boolean available) {
        this.title = title;
        this.genre = genre;
        this.authors = authors.split(";");
        this.publisher = publisher;
        this.language = language;
        this.takeDate = takeDate;
        this.returnDate = returnDate;
        this.publishYear = publishYear;
        this.isbnNumber = isbnNumber;
        this.pagesCount = pagesCount;
        this.takingPeriod = takingPeriod;
        this.takingsCount = takingsCount;
        this.available = available;
    }

    // Methods
    public String getProperty(String property){
        return switch (property) {
            case "title" -> title;
            case "author" -> String.join(";", authors);
            case "year" -> Integer.toString(publishYear);
            case "isbn" -> isbnNumber;
            default -> "";
        };
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public StringBuilder getAuthors(){
        StringBuilder auth= new StringBuilder();
        for(String author: this.authors){
            auth.append(author);
            auth.append(", ");
        }

        return new StringBuilder(auth.substring(0, auth.length() - 2));
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getTakingPeriod() {
        return takingPeriod;
    }

    public void setTakingPeriod(int takingPeriod) {
        this.takingPeriod = takingPeriod;
    }

    public int getTakingsCount() {
        return takingsCount;
    }

    public void setTakingsCount(int takingsCount) {
        this.takingsCount = takingsCount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
