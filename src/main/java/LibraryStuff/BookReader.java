package LibraryStuff;

import lombok.Data;

@Data
public class BookReader implements LibraryItem {
    private Integer id;
    private String name;
    private String surname;
    
    public BookReader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    public BookReader(String name, String surname, Integer id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
