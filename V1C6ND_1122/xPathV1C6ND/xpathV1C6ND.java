package xpathV1C6ND
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

public class XPathV1C6ND {

	public static void main(String[] args) {

        File xmlFile = new File ("studentV1C6ND.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("V1C6ND_1122/studentV1C6ND.xml");
		document.getDocumentElement().normalize();
		XPath xPath = XPathFactory.newInstance().newXPath();
			//1
		String expression = "class/student";
			//2
		String expression = "class/student[@id=2]";
            //3
		String expression = "//student";
            //4
		String expression = "class/student[position()=2]";
            //5 
		String expression = "class/student[last()]";
            //6
		String expression = "class/student[last()-1]";
            //7
		String expression = "class/student[position()<3]";
            //8
		String expression = "class/*";
            //9 
		String expression = "class/student[*]";
            //10
		String expression = "*";
            //11
		String expression = "class/student[kor > 20]";
            //12
        String expression = "class/student/keresztnev | class/student/vezeteknev";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);

            System.out.println("\nAktualis elem : " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                System.out.println("Student id: " + eElement.getAttribute("id"));

                System.out.println("Keresztnev: " + eElement.getElementsByTagName("keresztnev").item(0).getTextContent());

                System.out.println("Vezeteknev: " + eElement.getElementsByTagName("vezeteknev").item(0).getTextContent());

                System.out.println("Becenev: " + eElement.getElementsByTagName("becenev").item(0).getTextContent());

                System.out.println("Kor: " + eElement.getElementsByTagName("kor").item(0).getTextContent());
            }
        }
    }
}