package LibraryStuff;

import lombok.Data;

@Data
public class BookReader {
    private String name;
    private String surname;
    
    public BookReader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
