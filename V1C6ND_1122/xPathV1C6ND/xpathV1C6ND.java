package xPathV1C6ND;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class xpathV1C6ND {
      public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
                  File inputFile = new File("V1C6ND_1122\\studentV1C6ND.xml");

                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                  DocumentBuilder builder = factory.newDocumentBuilder();
                  Document doc = builder.parse(inputFile);
                  doc.getDocumentElement().normalize();

                  // xPath példány készítése:
                  XPath xPath = XPathFactory.newInstance().newXPath();

                  // 1. lekérdezés: 
                  System.out.println("1. Összes sutdent element, amelyek a class gyermekei: ");
                  String V1C6ND = "class/student";
                  NodeList nodeList = (NodeList) xPath.compile(V1C6ND).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList);

                  // 2. lekérdezés:
                  System.out.println("\n2. '2' id-jű student: ");
                  String V1C6ND_2 = "class/student[@id='2']";
                  NodeList nodeList2 = (NodeList) xPath.compile(V1C6ND_2).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList2);

                  //3. lekérdezés:
                  System.out.println("\n3. Összes student elem attól függetlenül, hogy hol vannak a dokumentumban: ");
                  String V1C6ND_3 = "//student";
                  NodeList nodeList3 = (NodeList) xPath.compile(V1C6ND_3).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList3);

                  //4. lekérdezés:
                  System.out.println("\n4. Második student element, ami a class root element gyermeke: ");
                  String V1C6ND_4 = "/class/student[2]";
                  NodeList nodeList4 = (NodeList) xPath.compile(V1C6ND_4).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList4);

                  // 5. lekérdezés:
                  System.out.println("\n5. Utolsó student element, ami a class root element gyermeke: ");
                  String V1C6ND_5 = "/class/student[last()]";
                  NodeList nodeList5 = (NodeList) xPath.compile(V1C6ND_5).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList5);

                  // 6. lekérdezés:
                  System.out.println("\n6. Utolsó előtti student element, ami a class root element gyermeke: ");
                  String V1C6ND_6 = "/class/student[last()-1]";
                  NodeList nodeList6 = (NodeList) xPath.compile(V1C6ND_6).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList6);

                  // 7. lekérdezés:
                  System.out.println("\n7. Első 2 student element, ami a class root element gyermeke: ");
                  String V1C6ND_7 = "class/student[position() <= 2]";
                  NodeList nodeList7 = (NodeList) xPath.compile(V1C6ND_7).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList7);
      
                  // 8. lekérdezés:
                  System.out.println("\n8. class root element összes gyermeke: ");
                  String V1C6ND_8 = "/class/*";
                  NodeList nodeList8 = (NodeList) xPath.compile(V1C6ND_8).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList8);

                  // 9. lekérdezés:
                  System.out.println("\n9. Összes student element, amely rendelkezik legalább egy bármilyen attribútummal: ");
                  String V1C6ND_9 = "/class/student[@*]";
                  NodeList nodeList9 = (NodeList) xPath.compile(V1C6ND_9).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList9);

                  // 10. lekérdezés:
                  System.out.println("\n10. A dokumentum összes eleme: ");
                  String V1C6ND_10 = "/*/*";
                  NodeList nodeList10 = (NodeList) xPath.compile(V1C6ND_10).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList10);

                  // 11. lekérdezés:
                  System.out.println("\n11. class root összes student eleme, amelynél a kor >20: ");
                  String V1C6ND_11 = "/class/student[kor > 20]";
                  NodeList nodeList11 = (NodeList) xPath.compile(V1C6ND_11).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList11);

                  // 12. lekérdezés:
                  System.out.println("\n12. Az összes student elem összes keresztnev or vezeteknev csomópontja: ");
                  String V1C6ND_12 = "//student/keresztnev | //student/vezeteknev";
                  NodeList nodeList12 = (NodeList) xPath.compile(V1C6ND_12).evaluate(doc, XPathConstants.NODESET);
                  writeData(nodeList12);
      }

      public static void writeData(NodeList nodeList) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                  Node node = nodeList.item(i);
                  System.out.println("\n");
                  if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                        Element element = (Element) node;

                        System.out.println("Hallgató ID: " + element.getAttribute("id"));
                        System.out
                                    .println("Vezetéknév: "
                                                + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                        System.out
                                    .println("Keresztnév: "
                                                + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                        System.out.println(
                                    "Becenév : " + element.getElementsByTagName("becenev").item(0).getTextContent());
                        System.out.println("Kor : " + element.getElementsByTagName("kor").item(0).getTextContent());

                  }
            }
      }
}