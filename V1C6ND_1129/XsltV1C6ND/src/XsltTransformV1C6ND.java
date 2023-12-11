package V1C6ND_1129.XsltV1C6ND.src;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XsltTransformV1C6ND {
    public static void main(String[] args) throws TransformerException {
            String xmlInputFile = "V1C6ND_1129\\XsltV1C6ND\\src\\hallgatoV1C6ND.xml";
            String xsltInputHTMLFile = "V1C6ND_1129\\XsltV1C6ND\\src\\hallgatoV1C6ND.xsl";
            String xsltInputXMLFile = "V1C6ND_1129\\XsltV1C6ND\\src\\hallgatoV1C6NDxml.xsl";
            String output = "V1C6ND_1129\\XsltV1C6ND\\hallgatoV1C6ND.html";
            String XMLOutput = "V1C6ND_1129\\XsltV1C6ND\\hallgatoV1C6NDresult.xml";

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltInputHTMLFile));
            transformer.transform(new StreamSource(xmlInputFile), new StreamResult(output));
            transformer = factory.newTransformer(new StreamSource(xsltInputXMLFile));
            transformer.transform(new StreamSource(xmlInputFile), new StreamResult(XMLOutput));

            xmlInputFile = "V1C6ND_1129\\XsltV1C6ND\\src\\orarendV1C6ND.xml";
            xsltInputHTMLFile = "V1C6ND_1129\\XsltV1C6ND\\src\\orarendV1C6ND.xsl";
            xsltInputXMLFile = "V1C6ND_1129\\XsltV1C6ND\\src\\orarendV1C6NDxml.xsl";
            output = "V1C6ND_1129\\XsltV1C6ND\\orarendV1C6ND.html";
            XMLOutput = "V1C6ND_1129\\XsltV1C6ND\\orarendV1C6NDresult.xml";

            transformer = factory.newTransformer(new StreamSource(xsltInputHTMLFile));
            transformer.transform(new StreamSource(xmlInputFile), new StreamResult(output));
            transformer = factory.newTransformer(new StreamSource(xsltInputXMLFile));
            transformer.transform(new StreamSource(xmlInputFile), new StreamResult(XMLOutput));

            System.out.println("Sikeres XSLT transzformáció!");
    }
}