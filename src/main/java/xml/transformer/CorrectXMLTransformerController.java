package xml.transformer;

import javax.xml.XMLConstants;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class CorrectXMLTransformerController {
    public void loadEmployees() {
        File inputFile = new File("res/employees.xml");

        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

            StringWriter writer = new StringWriter();
            StreamResult streamResult = new StreamResult(writer);
            InputStream fileStream = new FileInputStream(inputFile);
            Source input = new StreamSource(fileStream);

            Transformer transformer = tf.newTransformer();
            transformer.transform(input, streamResult);

            System.out.println(writer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
