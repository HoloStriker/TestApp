package xml.sax;

import model.Book;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String PRICE = "Price";

    private String elementValue;

    private List<Book> bookList = new ArrayList<Book>();

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("Book".equals(qName)) {
            Book book = new Book();

            if (attributes != null && attributes.getLength() == 1) {
                book.setId(Integer.parseInt(attributes.getValue(0)));
            }
            bookList.add(book);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case TITLE -> lastBook().setTitle(elementValue);
            case AUTHOR -> lastBook().setAuthor(elementValue);
            case PRICE -> lastBook().setPrice(Float.parseFloat(elementValue));
        }
    }
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length).trim();
    }

    private Book lastBook() {
        return bookList.get(bookList.size()-1);
    }

    public List<Book> getBookList(){
        return bookList;
    }
}
