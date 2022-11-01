package entity;

import validation.Validator;

import java.util.ArrayList;
import java.util.Locale;

public class Library {
    private String name;
    private String address;
    private int employeesNumber;
    private ArrayList<Book> books = new ArrayList<>();

    // Constructor
    public Library(String name, String address, int employeesNumber) {
        this.name = name;
        this.address = address;
        this.employeesNumber = employeesNumber;
    }

    // Methods
    public void addBook(Book book){
        this.books.add(book);
        System.out.println("Book added successfully!");
    }

    public void removeBook(Book book){
        int indexToDelete=0;
        for(int i=0; i<this.books.size(); i++){
            if (this.books.get(i).equals(book)){
                indexToDelete=i;
                break;
            }
        }

        this.books.remove(indexToDelete);
    }

    public ArrayList<Book> findBook(String criteria, String value){
        ArrayList<Book> found = new ArrayList<>();
        for(int i=0; i<this.books.size(); i++){
            if(this.books.get(i).getProperty(criteria).toLowerCase().contains(value.toLowerCase())){
                found.add(this.books.get(i));
            }
        }

        return found;
    }

    public boolean updateBook(Book book, String property, String value){
        Validator val=new Validator();

        switch (property) {
            case "available":
                book.setAvailable(value.equals("true"));
                break;
            case "takeDate":
                book.setTakeDate(value);
                break;
            case "returnDate":
                book.setReturnDate(value);
                break;
            case "takingPeriod":
                book.setTakingPeriod(Integer.parseInt(value));
                break;
            case "takingsCount":
                if (val.validateBookTakingsCount(Integer.parseInt(value))) {
                    book.setTakingsCount(Integer.parseInt(value));
                } else {
                    return false;
                }
                break;
        }

        return true;
    }

    public void printBooks(){
        if (this.books.size()>0) {
            System.out.println("Title           |Genre           |Authors");
            System.out.println("-------------------------------------------------------------------------");
            for (Book book : this.books) {
                System.out.printf("%-15s |", book.getTitle());
                System.out.printf("%-15s |", book.getGenre());
                System.out.printf("%-15s ", book.getAuthors());

                System.out.println();
                System.out.println("-------------------------------------------------------------------------");
            }
        }else {
            System.out.println("There are no books in the library yet.");
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

}
