package V1C6ND_1108.DOMParseV1C6ND;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMReadV1C6ND {
	public static void main(String[] args) {
        try {
            File inputFile = new File(".\\V1C6ND_1004\\v1c6nd_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("ora");
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nAktuális elem :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Id : " + eElement.getAttribute("id"));
                    System.out.println("Tipus : " + eElement.getAttribute("tipus"));
                    System.out.println("Kurzusnev : " + eElement.getElementsByTagName("kurzusnev").item(0).getTextContent());
                    Element idopont = (Element) eElement.getElementsByTagName("idopont").item(0);
                    System.out.println("Nap : " + idopont.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("Kezdes : " + idopont.getElementsByTagName("kezdes").item(0).getTextContent());
                    System.out.println("Befejezes : " + idopont.getElementsByTagName("befejezes").item(0).getTextContent());
                    System.out.println("Hely : " + eElement.getElementsByTagName("hely").item(0).getTextContent());
                    System.out.println("Oktato : " + eElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak : " + eElement.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}