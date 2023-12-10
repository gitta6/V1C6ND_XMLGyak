package xpathModifyV1C6ND;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;
import java.io.*;

public class xpathModifyV1C6ND {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException, TransformerException {
        File inFile = new File("V1C6ND_1122\\v1c6nd_kurzusfelvetel.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document inputDocument = dBuilder.parse(inFile);
        inputDocument.getDocumentElement().normalize();
        XPath xPath = XPathFactory.newInstance().newXPath();
        addNewCourse(inputDocument, xPath);
        deleteCourse(inputDocument, xPath);
        modifyCourse(inputDocument, xPath);
        printToConsole(inputDocument);
        saveToFile(inputDocument, "V1C6ND_1122\\kurzusfelvetelV1C6NDModified.xml");
    }

    // 1. Új kurzus hozzáadása:
    private static void addNewCourse(Document document, XPath xPath) throws XPathExpressionException {
        Element kurzusokElement = (Element) xPath.compile("/V1C6ND_kurzusfelvetel/kurzusok").evaluate(document,
                XPathConstants.NODE);
        Element newCourse = document.createElement("kurzus");

        newCourse.setAttribute("id", "ujKurzus1");
        Element kurzusnev = document.createElement("kurzusnev");
        kurzusnev.appendChild(document.createTextNode("Új kurzus"));
        newCourse.appendChild(kurzusnev);
        Element kredit = document.createElement("kredit");
        kredit.appendChild(document.createTextNode("5"));
        newCourse.appendChild(kredit);
        Element hely = document.createElement("hely");
        hely.appendChild(document.createTextNode("XXXIII. előadó"));
        newCourse.appendChild(hely);
        Element idopont = document.createElement("idopont");
        idopont.appendChild(document.createTextNode("Péntek 12:00-14:00"));
        newCourse.appendChild(idopont);
        Element oktato = document.createElement("oktato");
        oktato.appendChild(document.createTextNode("Új Előadó"));
        newCourse.appendChild(oktato);
        kurzusokElement.appendChild(newCourse);
    }

    // 2. Kurzus törlése:
    private static void deleteCourse(Document doc, XPath xPath) throws XPathExpressionException {
        Node courseToDelete = (Node) xPath.compile("/V1C6ND_kurzusfelvetel/kurzusok/kurzus[@id='GEIAK130-B']")
                .evaluate(doc, XPathConstants.NODE);
        courseToDelete.getParentNode().removeChild(courseToDelete);
    }

    // 3. Kurzus adatainak módosítása:
    private static void modifyCourse(Document doc, XPath xPath) throws XPathExpressionException {
        Node courseToModify = (Node) xPath.compile("/V1C6ND_kurzusfelvetel/kurzusok/kurzus[@id='GTÜSZ601B-B']")
                .evaluate(doc, XPathConstants.NODE);
        Element courseElement = (Element) courseToModify;
        courseElement.getElementsByTagName("kredit").item(0).setTextContent("5");
        courseElement.getElementsByTagName("hely").item(0).setTextContent("X. előadó");
        courseElement.getElementsByTagName("idopont").item(0).setTextContent("Szerda 08:00-10:00");
    }

    // Konzolra íratás:
    private static void printToConsole(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 2);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult result = new StreamResult(System.out);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        System.out.println("\nMódosított verzió: ");
    }

    // Eredmények kiírása fájlba
    private static void saveToFile(Document outputDocument, String fileName) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 2);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult result = new StreamResult(new File(fileName));
        DOMSource source = new DOMSource(outputDocument);
        transformer.transform(source, result);
        System.out.println("A módosított verzió a " + fileName + " nevű fájlban található.");
    }

}