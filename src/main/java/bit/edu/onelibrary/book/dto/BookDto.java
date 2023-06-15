package bit.edu.onelibrary.book.dto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookDto {
    private long bookNo;
    private String title;
    private String content;
    private boolean rentalCheck;
    private String author;
    private boolean displayCheck;
    private String categoryName;

    public BookDto(long bookNo, String title, String content, boolean rentalCheck, String author,
                   boolean displayCheck, String categoryName) {
        this.bookNo = bookNo;
        this.title = title;
        this.content = content;
        this.rentalCheck = rentalCheck;
        this.author = author;
        this.displayCheck = displayCheck;
        this.categoryName = categoryName;
    }

    public long getBookNo() {
        return bookNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isRentalCheck() {
        return rentalCheck;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isDisplayCheck() {
        return displayCheck;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "{" +
            "\"bookNo\":" + bookNo +
            ", \"title\":\"" + title + '\"' +
            ", \"content\":\"" + content + '\"' +
            ", \"rentalCheck\":" + rentalCheck +
            ", \"author\":\"" + author + '\"' +
            ", \"displayCheck\":" + displayCheck +
            ", \"categoryName\":\"" + categoryName + '\"' +
            '}';
    }
}
