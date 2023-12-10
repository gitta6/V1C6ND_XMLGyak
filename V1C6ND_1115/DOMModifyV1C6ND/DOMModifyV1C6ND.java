package V1C6ND_1115.DOMModifyV1C6ND;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;

public class DOMModifyV1C6ND {
    public static void main(String[] args) {
        try {
            File inputFile = new File("V1C6ND_1115\\v1c6nd_kurzusfelvetel.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document inputDocument = dBuilder.parse(inputFile);
            inputDocument.getDocumentElement().normalize();
            NodeList kurzusNodeList = inputDocument.getElementsByTagName("kurzus");
            for (int i = 0; i < kurzusNodeList.getLength(); i++) {
                Element kurzusNode = (Element) kurzusNodeList.item(i);
                Element oktatoNode = (Element) kurzusNode.getElementsByTagName("oktato").item(0);
                String nyelvAttr = kurzusNode.getAttribute("nyelv");

                if (oktatoNode == null) {
                    oktatoNode = inputDocument.createElement("oktato");
                    oktatoNode.appendChild(inputDocument.createTextNode("Szucs Miklos"));
                    kurzusNode.appendChild(oktatoNode);
                }

                if (nyelvAttr.equals("magyar")) {
                    kurzusNode.setAttribute("nyelv", "angol");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(inputDocument);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            File outputFile = new File("V1C6ND_1115\\kurzusfelvetelV1C6NDModified.xml");
            System.out.println("A módosított XML fájl kurzusfelvetelV1C6NDModified.xml néven lett elmentve.");
            OutputStream outputStream = new FileOutputStream(outputFile);
            StreamResult fileResult = new StreamResult(outputStream);
            transformer.transform(source, fileResult);
            outputStream.close();

        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}