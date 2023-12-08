package XMLTaskV1C6ND.DOMParseV1C6ND.src.hu.domparse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyV1C6ND {

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {

        // Bemeneti fájl megnyitása:
        File inputFile = new File("XMLTaskV1C6ND\\XMLV1C6ND.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder(); 
        Document inputDocument = documentBuilder.parse(inputFile);

        // Módosítandó elementek beolvasása:
        Node dolgozo = inputDocument.getElementsByTagName("dolgozo").item(0);
        Node munkakorok = inputDocument.getElementsByTagName("munkakorok").item(0);
        Node munkakor_ism = inputDocument.getElementsByTagName("munkakor_ism").item(0);
        Node ablak = inputDocument.getElementsByTagName("ablak").item(0);
        Node ugyfel = inputDocument.getElementsByTagName("ugyfel").item(0);
        Node felettes = inputDocument.getElementsByTagName("felettes").item(0);

        NodeList dolgozoList = dolgozo.getChildNodes();
        NodeList munkakorokList = munkakorok.getChildNodes();
        NodeList munkakor_ismList = munkakor_ism.getChildNodes();
        NodeList ablakList = ablak.getChildNodes();
        NodeList ugyfelList = ugyfel.getChildNodes();
        NodeList felettesList = felettes.getChildNodes();

        // Dolgozo element módosítása:
        for (int i = 0; i < dolgozoList.getLength(); i++) {
            Node node = dolgozoList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Tóth Erzsébet nevének megváltoztatása, mert férjhez ment:
                if ("nev".equals(element.getNodeName())) {
                    if ("Tóth Erzsébet".equals(element.getTextContent())) {
                        element.setTextContent("Kiss Erzsébet");
                    }
                }
            }
        }

        // Munkakorok element módosítása:
        for (int i = 0; i < munkakorokList.getLength(); i++) {
            Node node = munkakorokList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // A gépjármű ügyintéző munkakör megnevezésének változtatása (pontosítás):
                if ("megnevezes".equals(element.getNodeName())) {
                    if ("gépjármű ügyintéző".equals(element.getTextContent())) {
                        element.setTextContent("személygépjármű ügyintéző");
                    }
                }
            }
        }

        // Munkakor_ism element módosítása:
        for (int i = 0; i < munkakor_ismList.getLength(); i++) {
            Node node = munkakor_ismList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Az MS Office ismeretek módosítása MS 365 ismeretekre (növekvő elvárások a
                // munkakörben):
                if ("ismeretek".equals(element.getNodeName())) {
                    if ("MS Office ismeretek".equals(element.getTextContent())) {
                        element.setTextContent("MS 365 ismeretek");
                    }
                }
            }
        }

        // Ablak element módosítása:
        for (int i = 0; i < ablakList.getLength(); i++) {
            Node node = ablakList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // A személyigazolvány ügykör változtatása, mert összevonásra került egy másik
                // ügykörrel:
                if ("ugykor".equals(element.getNodeName())) {
                    if ("személyig.".equals(element.getTextContent())) {
                        element.setTextContent("személyig., lakcímkártya");
                    }
                }
            }
        }

        // Ugyfel element módosítása:
        for (int i = 0; i < ugyfelList.getLength(); i++) {
            Node node = ugyfelList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // A szig. módosítása egy ügyfélnél, akinek megváltozott a személyigazolvány
                // száma:
                if ("szig".equals(element.getNodeName())) {
                    if ("577403KE".equals(element.getTextContent())) {
                        element.setTextContent("254338KK");
                    }
                }
            }
        }

        // Felettes element módosítása:
        for (int i = 0; i < felettesList.getLength(); i++) {
            Node node = felettesList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Piros Rózsa nevének megváltoztatása, mert férjhez ment:
                if ("nev".equals(element.getNodeName())) {
                    if ("Piros Rózsa".equals(element.getTextContent())) {
                        element.setTextContent("Fehérné Piros Rózsa");
                    }
                }
            }
        }

        // Módosítások kilistázása:

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Kimeneti szöveg formázása:
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Karakterkódolás beállítása
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5"); // Behúzás mértékének
                                                                                         // beállítása
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formázás engedélyezése
        DOMSource source = new DOMSource(inputDocument);
        System.out.println("Módosított verzió: ");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }

}