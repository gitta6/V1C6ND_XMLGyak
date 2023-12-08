package XMLTaskV1C6ND.DOMParseV1C6ND.src.hu.domparse;

import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadV1C6ND {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        // XML fájl beolvasása és dokumentummá alakítása:
        Document inputDocument = documentBuilder.parse(new File("XMLTaskV1C6ND\\\\XMLV1C6ND.xml"));

        // A bemeneti fájl normalizálása:
        inputDocument.getDocumentElement().normalize();

        // Prolog kiíratása:
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        // Nyitó tag kiíratása:
        System.out.println(
                "<V1C6ND_okmanyiroda xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\" xs:noNamespaceSchemaLocation=\"XMLSchemaV1C6ND.xsd\">");

        // Elementek beolvasása sorban:
        readDolgozok(inputDocument);
        readMunkakorok(inputDocument);
        readSzerepek(inputDocument);
        readMunkakorIsm(inputDocument);
        readAblakok(inputDocument);
        readUgyfelek(inputDocument);
        readFelettesek(inputDocument);

        // Záró tag kiíratása:
        System.out.println("\n</V1C6ND_okmanyiroda>");
    }

    // XML dokumentum elementjeinek beolvasása, majd struktuált módon való kiíratása
    // konzolra:

    // Dolgozók beolvasása:
    private static void readDolgozok(Document document) {
        NodeList dolgozoList = document.getElementsByTagName("dolgozo");
        for (int i = 0; i < dolgozoList.getLength(); i++) {
            Node node = dolgozoList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String did = el.getAttribute("did");
                String szig = el.getElementsByTagName("szig").item(0).getTextContent();
                String nev = el.getElementsByTagName("nev").item(0).getTextContent();
                String szulido = el.getElementsByTagName("szulido").item(0).getTextContent();
                String irsz = el.getElementsByTagName("irsz").item(0).getTextContent();
                String varos = el.getElementsByTagName("varos").item(0).getTextContent();
                String utca = el.getElementsByTagName("utca").item(0).getTextContent();
                String hsz = el.getElementsByTagName("hsz").item(0).getTextContent();
                String fid = el.getAttribute("fid");

                // Kiíratás konzolra:
                System.out.println("    <dolgozo did=\"" + did + "\">");
                printElement("szig", szig);
                printElement("nev", nev);
                printElement("szulido", szulido);
                printElement("irsz", irsz);
                printElement("varos", varos);
                printElement("utca", utca);
                printElement("hsz", hsz);
                printElement("fid", fid);
                System.out.println("    </dolgozo>");
            }
        }
    }

    // Munkakörök beolvasása:
    private static void readMunkakorok(Document document) {
        NodeList munkakorokList = document.getElementsByTagName("munkakorok");
        for (int i = 0; i < munkakorokList.getLength(); i++) {
            Node node = munkakorokList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String mid = el.getAttribute("mid");
                String megnevezes = el.getElementsByTagName("megnevezes").item(0).getTextContent();

                // Kiíratás konzolra:
                System.out.println("    <munkakorok mid=\"" + mid + "\">");
                printElement("megnevezes", megnevezes);
                System.out.println("    </munkakorok>");
            }
        }
    }

    // Szerepek beolvasása:
    private static void readSzerepek(Document document) {
        NodeList szerepList = document.getElementsByTagName("szerep");
        for (int i = 0; i < szerepList.getLength(); i++) {
            Node node = szerepList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String did = el.getAttribute("did");
                String mid = el.getAttribute("mid");
                String helyettesit = el.getElementsByTagName("helyettesit-e").item(0).getTextContent();

                // Kiíratás konzolra:
                System.out.println("    <szerep did=\"" + did + "\" mid=\"" + mid + "\">");
                printElement("helyettesit-e", helyettesit);
                System.out.println("    </szerep>");
            }
        }
    }

    // Munkakör ismeretek beolvasása:
    private static void readMunkakorIsm(Document document) {
        NodeList munkakorIsmList = document.getElementsByTagName("munkakor_ism");
        for (int i = 0; i < munkakorIsmList.getLength(); i++) {
            Node node = munkakorIsmList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String mid = el.getAttribute("mid");
                String ismeretek = el.getElementsByTagName("ismeretek").item(0).getTextContent();

                // Kiíratás konzolra:
                System.out.println("    <munkakor_ism mid=\"" + mid + "\">");
                printElement("ismeretek", ismeretek);
                System.out.println("    </munkakor_ism>");
            }
        }
    }

    // Ablakok beolvasása:
    private static void readAblakok(Document document) {
        NodeList ablakList = document.getElementsByTagName("ablak");
        for (int i = 0; i < ablakList.getLength(); i++) {
            Node node = ablakList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String aid = el.getAttribute("aid");
                String ugykor = el.getElementsByTagName("ugykor").item(0).getTextContent();
                String did = el.getAttribute("did");

                // Kiíratás konzolra:
                System.out.println("    <ablak aid=\"" + aid + "\">");
                printElement("ugykor", ugykor);
                printElement("did", did);
                System.out.println("    </ablak>");
            }
        }
    }

    // Ügyfelek beolvasása:
    private static void readUgyfelek(Document document) {
        NodeList ugyfelList = document.getElementsByTagName("ugyfel");
        for (int i = 0; i < ugyfelList.getLength(); i++) {
            Node node = ugyfelList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String uid = el.getAttribute("uid");
                String szig = el.getElementsByTagName("szig").item(0).getTextContent();
                String nev = el.getElementsByTagName("nev").item(0).getTextContent();
                String szulido = el.getElementsByTagName("szulido").item(0).getTextContent();
                String irsz = el.getElementsByTagName("irsz").item(0).getTextContent();
                String varos = el.getElementsByTagName("varos").item(0).getTextContent();
                String utca = el.getElementsByTagName("utca").item(0).getTextContent();
                String hsz = el.getElementsByTagName("hsz").item(0).getTextContent();
                String aid = el.getAttribute("aid");
                String belepes = el.getElementsByTagName("belepes").item(0).getTextContent();
                String kilepes = el.getElementsByTagName("kilepes").item(0).getTextContent();

                // Kiíratás konzolra:
                System.out.println("    <ugyfel uid=\"" + uid + "\">");
                printElement("szig", szig);
                printElement("nev", nev);
                printElement("szulido", szulido);
                printElement("irsz", irsz);
                printElement("varos", varos);
                printElement("utca", utca);
                printElement("hsz", hsz);
                printElement("aid", aid);
                printElement("belepes", belepes);
                printElement("kilepes", kilepes);
                System.out.println("    </ugyfel>");
            }
        }
    }

    // Felettesek beolvasása:
    private static void readFelettesek(Document document) {
        NodeList felettesList = document.getElementsByTagName("felettes");
        for (int i = 0; i < felettesList.getLength(); i++) {
            Node node = felettesList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String fid = el.getAttribute("fid");
                String szig = el.getElementsByTagName("szig").item(0).getTextContent();
                String nev = el.getElementsByTagName("nev").item(0).getTextContent();

                // Kiíratás konzolra:
                System.out.println("    <felettes fid=\"" + fid + "\">");
                printElement("szig", szig);
                printElement("nev", nev);
                System.out.println("    </felettes>");
            }
        }
    }

    // Elem kiíratási metódus:
    private static void printElement(String name, String value) {
        System.out.println("        <" + name + ">" + value + "</" + name + ">");
    }
}
