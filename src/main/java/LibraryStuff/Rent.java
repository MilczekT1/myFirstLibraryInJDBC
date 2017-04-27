package LibraryStuff;

import lombok.Data;

@Data
public class Rent {
    
    private Integer rentID;
    private Integer bookID;
    private Integer readerID;
    
    public Rent(Integer bookID, Integer readerID) {
        this.bookID = bookID;
        this.readerID = readerID;
    }
    
    public Rent(Integer rentID, Integer bookID, Integer readerID) {
        this.rentID = rentID;
        this.bookID = bookID;
        this.readerID = readerID;
    }
}
