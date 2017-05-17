package LibraryStuff;

import lombok.Data;

@Data
class Book implements LibraryItem {
    
    private String title;
    private int pages;
    private int id;
    
    public Book(){
    }
    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
    public Book(String title, int pages, int id) {
        this.title = title;
        this.pages = pages;
        this.id = id;
    }
}
