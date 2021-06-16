package xml.sax;

import model.Book;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

// https://howtodoinjava.com/java/xml/sax-parser-read-xml-example/
// https://www.baeldung.com/java-sax-parser

public class SAXHandler extends DefaultHandler {
    private static final String TITLE = "Title";
    private static final String AUTHOR = "Author";
    private static final String PRICE = "Price";

    private String elementValue;

    //This is the list which shall be populated while parsing the XML.
    private List<Book> bookList = new ArrayList<Book>();

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        //If this is start of 'user' element then prepare a new User instance and push it in object stack
        if ("Book".equals(qName)) {
            //New User instance
            Book student = new Book();

            //Set all required attributes in any XML element here itself
            if (attributes != null && attributes.getLength() == 1) {
                student.setId(Integer.parseInt(attributes.getValue(0)));
            }
            bookList.add(student);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        //User instance has been constructed so pop it from object stack and push in userList
        switch (qName) {
            case TITLE -> lastStudent().setTitle(elementValue);
            case AUTHOR -> lastStudent().setAuthor(elementValue);
            case PRICE -> lastStudent().setPrice(Float.parseFloat(elementValue));
        }
    }

    /**
     * This will be called everytime parser encounter a value node
     */
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length).trim();
    }

    //Accessor for userList object
    private Book lastStudent() {
        return bookList.get(bookList.size()-1);
    }

    public List<Book> getBookList(){
        return bookList;
    }
}
