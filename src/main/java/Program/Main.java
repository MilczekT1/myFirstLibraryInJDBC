package Program;

import LibraryStuff.Library;

/*
Project should simulate simple library with:
    + Add book to lib
    + Add user to lib
    + Add rent to lib
    + Show all books
    + Show all users
    + Show all rents
    + Remove user
    + Remove book
    + remove rent
    + get object from table
*/
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        
        // examples
        library.addBookReader("James", "Bond");
        library.addBook("LOTR", 4676);
        library.addRent(1,1);
        library.showAll("readers");
        library.showAll("books");
        library.showAll("rents");
        
        System.out.println("Amen");
    }
}
