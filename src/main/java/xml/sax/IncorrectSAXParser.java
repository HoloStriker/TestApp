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

public class IncorrectSAXParser {
    public static void main(String[] args) {
        File inputFile = new File("res/input.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = spf.newSAXParser();
            XMLReader saxReader = saxParser.getXMLReader();
            SAXHandler handler = new SAXHandler();
            saxReader.setContentHandler(handler);

            InputStream fileStream = new FileInputStream(inputFile);
            InputSource input = new InputSource(fileStream);

            saxReader.parse(input);
            List<Book> students = handler.getBookList();
            System.out.println(students);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
