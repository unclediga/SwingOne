package unclediga.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class SearchXPath {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document doc = parser.parse(new File("res/data/questions.xml"));
        Element el = doc.getDocumentElement();

        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath path = pathFactory.newXPath();

        String xpathStr = "//question[1]";
        System.out.println("eval xpath {" + xpathStr + "}");
        try {
            String val = path.evaluate(xpathStr, el);
            System.out.println("value: " + val);
        } catch (XPathExpressionException e) {
            System.err.println("Error on eval xpath: " + e.getMessage());
        }

        xpathStr = "//question[2]/@w";
        System.out.println("eval xpath {" + xpathStr + "}");
        try {
            Object val2 = path.evaluate(xpathStr, el, XPathConstants.NUMBER);
            System.out.println("value (type) : " + val2 + "("+val2.getClass().toString()+")");
        } catch (XPathExpressionException e) {
            System.err.println("Error on eval xpath: " + e.getMessage());
        }
    }
}

