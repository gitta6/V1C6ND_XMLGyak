package V1C6ND_1025.SAXV1C6ND;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XsdV1C6ND {
    public static void main(String[] args) throws SAXException, IOException {
        File xml = new File("SaxV1C6ND\\v1c6nd_kurzusfelvetel.xml");
        File xsd = new File("SaxV1C6ND\\v1c6nd_kurzusfelvetel.xsd");

        SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sFactory.newSchema(xsd);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
        System.out.println("The XSD Validation was successful!");
    }
}