package LibraryStuff;

import lombok.Data;

@Data
class Rent implements LibraryItem {
    
    private Integer rentID;
    private Integer bookID;
    private Integer readerID;
    
    public Rent(Integer bookID, Integer readerID) {
        this.bookID = bookID;
        this.readerID = readerID;
    }
    
    public Rent(Integer bookID, Integer readerID, Integer rentID) {
        this.rentID = rentID;
        this.bookID = bookID;
        this.readerID = readerID;
    }
}
