package unclediga.xml;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidateScheme2 {

    public static void main(String[] args) throws Exception {

        // ... by cause of static context
        final ErrorHandler errorHandlerParse = new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.err.println("WARNING on parsing!\nMessage: " + exception.getMessage());

            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println("ERROR on parsing!\nMessage: " + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.err.println("FATAL ERROR: on parsing!\nMessage: " + exception.getMessage());
            }
        };

        final ErrorHandler errorHandlerValidator = new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.err.println("WARNING when validate!\nMessage: " + exception.getMessage());

            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println("ERROR when validate!\nMessage: " + exception.getMessage());
                throw new SAXException();
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.err.println("FATAL ERROR: when validate!\nMessage: " + exception.getMessage());
                throw new SAXException();
            }
        };

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        parser.setErrorHandler(errorHandlerParse);
        Document doc = parser.parse(new File("res/data/questions.xml"));

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("res/data/questions.xsd"));
        Validator validator = schema.newValidator();
        validator.setErrorHandler(errorHandlerValidator);

        try {
            validator.validate(new DOMSource(doc));
            System.out.println("Проверка прошла успешно");
        } catch (SAXException e) {
            System.out.println("Проверка завершилась неудачей");
        }

    }


}


