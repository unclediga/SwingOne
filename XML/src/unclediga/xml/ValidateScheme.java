package unclediga.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidateScheme {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document doc = parser.parse(new File("res/data/questions.xml"));

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("res/data/questions.xsd"));
        Validator validator = schema.newValidator();

        try {
            validator.validate(new DOMSource(doc));
            System.out.println("Проверка прошла успешно");
        } catch (SAXException e) {
            System.err.println("Ошибка проверки:"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


