package unclediga.xml;

import com.sun.org.apache.xerces.internal.impl.Version;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXVersion {

    public static String getVersionString() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Version xerces = new Version();

        StringBuilder s = new StringBuilder();
        s.append("SAXParserFactory prop: " + System.getProperty("javax.xml.parsers.SAXParserFactory"));
        s.append("\n");
        s.append("SAXParserFactory class : " + saxParserFactory.getClass().getName());
        s.append("\n");
        s.append("version : " + xerces.getVersion());

        if (saxParser != null) {
            s.append("SAXParser class : " + saxParser.getClass().getName());
        } else {
            s.append("No SAX Parser from factory");
        }
        return s.toString();
    }
}
