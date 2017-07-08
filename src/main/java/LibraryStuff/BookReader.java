package LibraryStuff;

import lombok.Data;

@Data
class BookReader implements LibraryItem {
    
    private Integer id;
    private String name;
    private String surname;
    
    public BookReader(String name, String surname) {
        if (name != null & surname != null) {
            this.name = name;
            this.surname = surname;
        }
        else{
            throw new IllegalArgumentException("Incorrect input in 2 arg constructor");
        }
    }
    public BookReader(String name, String surname, Integer id) {
        if (name != null & surname != null && id !=null && !id.equals(0)) {
            this.name = name;
            this.surname = surname;
            this.id = id;
        }
        else{
            throw new IllegalArgumentException("Incorrect input in 3 arg constructor");
        }
    }
}
