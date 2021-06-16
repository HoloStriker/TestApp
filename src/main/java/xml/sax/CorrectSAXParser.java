package xml.sax;

import model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CorrectSAXParser {
    public List<Book> getBooks() {
        File inputFile = new File("res/books.xml");
        String FEATURE = null;

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader saxReader = saxParser.getXMLReader();
            SAXHandler handler = new SAXHandler();
            saxReader.setContentHandler(handler);

            FEATURE = "http://xml.org/sax/features/external-general-entities";
            spf.setFeature(FEATURE, false);
            saxReader.setFeature(FEATURE, false);
            FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            spf.setFeature(FEATURE, true);

            InputStream fileStream = new FileInputStream(inputFile);
            InputSource input = new InputSource(fileStream);
            saxReader.parse(input);
            List<Book> books = handler.getBookList();
            return books;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
