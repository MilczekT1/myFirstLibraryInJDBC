package LibraryStuff;

import lombok.Data;

@Data
class Rent implements LibraryItem {
    
    private Integer rentID;
    private Integer bookID;
    private Integer readerID;
    
    public Rent(Integer bookID, Integer readerID) throws IllegalArgumentException {
        if (bookID != null && readerID != null && bookID > 0 && readerID > 0){
            this.bookID = bookID;
            this.readerID = readerID;
        } else {
            throw new IllegalArgumentException("Incorrect input in 2 arg constructor");
        }
    }
    
    public Rent(Integer bookID, Integer readerID, Integer rentID) throws IllegalArgumentException {
        if (bookID != null &&  bookID > 0 &&
            readerID != null && readerID > 0 &&
            rentID != null && rentID > 0){
            this.rentID = rentID;
            this.bookID = bookID;
            this.readerID = readerID;
        } else {
            throw new IllegalArgumentException("Incorrect input in 3 arg constructor");
        }
    }
}
