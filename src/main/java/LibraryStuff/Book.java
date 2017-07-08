package LibraryStuff;

import lombok.Data;

@Data
class Book implements LibraryItem {
    
    private String title;
    private int pages;
    private int id;
    
    public Book(){
    }
    public Book(String title, int pages) throws IllegalArgumentException {
        if (title != null && pages >0) {
            this.title = title;
            this.pages = pages;
        }
        else {
            throw new IllegalArgumentException("Incorrect input in 2 arg constructor");
        }
    }
    public Book(String title, int pages, int id) throws IllegalArgumentException {
        if (title != null && pages > 0 && id > 0){
            this.title = title;
            this.pages = pages;
            this.id = id;
        }
        else{
            throw new IllegalArgumentException("Incorrect input in 3 arg constructor");
        }
    }
}
