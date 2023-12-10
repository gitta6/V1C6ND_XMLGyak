package XMLTaskV1C6ND.DOMParseV1C6ND.src.hu.domparse;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryV1C6ND {

    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException, ParseException {

        try {

            // Bemeneti XML fájl beolvasása:
            File inFile = new File("XMLTaskV1C6ND\\XMLV1C6ND.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document inputDocument = dBuilder.parse(inFile);
            inputDocument.getDocumentElement().normalize();

            // Gyökérelem konzolra íratása:
            System.out.println("\n");
            System.out.print("Root element: ");
            System.out.println(inputDocument.getDocumentElement().getNodeName());
            System.out.println("\n");

            // 1. Lekérdezés:
            // Az ügyfelek ID-jának, szig. számának, nevének, születési idejüknek, valamint
            // városuknak kiíratása.
            NodeList nodeList1 = inputDocument.getElementsByTagName("ugyfel");
            System.out.println("1. Ügyfelek néhány személyes adatának kilistázása:");
            System.out.println("__________________________________________________");

            // Végig iterálunk az elemeken és adott elemek kiíratjuk a konzolra:
            for (int i = 0; i < nodeList1.getLength(); i++) {
                Node n = nodeList1.item(i);
                System.out.println("\n/" + (i + 1) + "/");

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    System.out.print("Ügyfél azonosító: ");
                    System.out.println(element.getAttribute("uid"));
                    NodeList ugyfelSzigList = element.getElementsByTagName("szig");
                    NodeList ugyfelNevList = element.getElementsByTagName("nev");
                    NodeList ugyfelSzulidoList = element.getElementsByTagName("szulido");
                    NodeList ugyfelVarosList = element.getElementsByTagName("varos");

                    for (int j = 0; j < ugyfelSzigList.getLength(); j++) {
                        Node n1 = ugyfelSzigList.item(j);
                        Node n2 = ugyfelNevList.item(j);
                        Node n3 = ugyfelSzulidoList.item(j);
                        Node n4 = ugyfelVarosList.item(j);

                        Element ugyfelSzig = (Element) n1;
                        System.out.print("Ügyfél szig. száma: ");
                        System.out.println(ugyfelSzig.getTextContent());

                        Element ugyfelNev = (Element) n2;
                        System.out.print("Ügyfél neve: ");
                        System.out.println(ugyfelNev.getTextContent());

                        Element ugyfelSzulido = (Element) n3;
                        System.out.print("Ügyfél születési ideje: ");
                        System.out.println(ugyfelSzulido.getTextContent());

                        Element ugyfelVaros = (Element) n4;
                        System.out.print("Ügyfél lakhelye: ");
                        System.out.println(ugyfelVaros.getTextContent());
                    }
                }
            }
            System.out.println("\n");

            // 2. Lekérdezés:
            // Dolgozók nevének, születési idejének kiíratása, akik 1980 után születtek, és
            // koruk kiszámolása.
            NodeList nodeList2 = inputDocument.getElementsByTagName("dolgozo");
            System.out.println("2. Dolgozók, akik 1980 után születtek:");
            System.out.println("_______________________________________");
            int szuletett1980utan = 0;

            for (int i = 0; i < nodeList2.getLength(); i++) {
                Node n = nodeList2.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    NodeList dolgozokNevList = element.getElementsByTagName("nev");
                    NodeList dolgozoSzulidoList = element.getElementsByTagName("szulido");

                    for (int j = 0; j < dolgozokNevList.getLength(); j++) {
                        Node node1 = dolgozokNevList.item(j);
                        Node node2 = dolgozoSzulidoList.item(j);

                        Element dolgozoNev = (Element) node1;
                        Element dolgozoSzulido = (Element) node2;

                        // Ellenőrizzük, hogy az illető 1980 után született-e:
                        String szulidoString = dolgozoSzulido.getTextContent();
                        LocalDate szuletesiDatum = LocalDate.parse(szulidoString, DateTimeFormatter.ISO_DATE);
                        int szuletesiEv = szuletesiDatum.getYear();

                        if (szuletesiEv > 1980) {
                            szuletett1980utan++;
                            System.out.println("\n/" + (szuletett1980utan) + "/");

                            // Kiszámoljuk az életkorát:
                            int kor = LocalDate.now().getYear() - szuletesiEv;

                            System.out.println("Név: " + dolgozoNev.getTextContent());
                            System.out.println("Születési idő: " + szulidoString);
                            System.out.println("Kor: " + kor + " év");
                        }

                    }
                }
            }
            System.out.println("\n");

            // 3. Lekérdezés:
            // A dolgozók, ügyfelek és felettesek neveinek ABC sorrendbe rendezése.
            NodeList dolgozoList = inputDocument.getElementsByTagName("dolgozo");
            NodeList felettesList = inputDocument.getElementsByTagName("felettes");
            NodeList ugyfelList = inputDocument.getElementsByTagName("ugyfel");
            System.out.println("3. Dolgozók, ügyfelek, felettesek ABC sorrendben:");
            System.out.println("_________________________________________________");

            // A kért elementek listájának létrehozása, feltöltése:
            List<Element> dolgozoElements = getNodeListAsList(dolgozoList);
            List<Element> felettesElements = getNodeListAsList(felettesList);
            List<Element> ugyfelElements = getNodeListAsList(ugyfelList);

            // Összesítés:
            List<Element> osszesitettLista = new ArrayList<>();
            osszesitettLista.addAll(dolgozoElements);
            osszesitettLista.addAll(felettesElements);
            osszesitettLista.addAll(ugyfelElements);

            // ABC sorrendbe rendezés:
            Collections.sort(osszesitettLista, new ElementComparator());

            for (int i = 0; i < osszesitettLista.size(); i++) {
                Element element = osszesitettLista.get(i);
                System.out.println("\n/" + (i + 1) + "/ ");
                System.out.println(element.getElementsByTagName("nev").item(0).getTextContent());
            }
            System.out.println("\n");

            // 4. lekérdezés:
            // A munkakör ismeretek elemzése, hogy melyik hány db munkakör betöltéséhez
            // szükséges.
            NodeList munkakorIsmNodes = inputDocument.getElementsByTagName("munkakor_ism");
            System.out.println("4. Munkakör ismeretek statisztikája:");
            System.out.println("____________________________________");

            // Lista az egyedi ismeretek tárolásához:
            List<String> egyediIsmeretek = new ArrayList<>();

            // Lista az ismeretek előfordulásainak számolásához:
            List<Integer> ismeretSzamlaloLista = new ArrayList<>();

            // Munkakor_ism elemek feldolgozása:
            for (int i = 0; i < munkakorIsmNodes.getLength(); i++) {
                Element munkakorIsmElement = (Element) munkakorIsmNodes.item(i);
                NodeList ismeretNodes = munkakorIsmElement.getElementsByTagName("ismeretek");

                // Ismeretek számlálása:
                for (int j = 0; j < ismeretNodes.getLength(); j++) {
                    Element ismeretElement = (Element) ismeretNodes.item(j);
                    String ismeret = ismeretElement.getTextContent();

                    // Ha az ismeretet először találjuk meg, adjuk hozzá az egyediIsmeretek
                    // listához:
                    if (!egyediIsmeretek.contains(ismeret)) {
                        egyediIsmeretek.add(ismeret);
                        ismeretSzamlaloLista.add(1); // A számolás kezdeti értékét beállítjuk 1-re.
                    } else {
                        // Ha az ismeret már korábban előfordult, növeljük az előfordulások számát:
                        int k = egyediIsmeretek.indexOf(ismeret);
                        int ismeretSzamlalo = ismeretSzamlaloLista.get(k);
                        ismeretSzamlaloLista.set(k, ismeretSzamlalo + 1);
                    }
                }
            }
            System.out.println("\n");

            // Eredmények kiíratása a konzolra:
            for (int i = 0; i < egyediIsmeretek.size(); i++) {
                System.out.println("/" + (i + 1) + "/ ");
                String ismeret = egyediIsmeretek.get(i);
                int count = ismeretSzamlaloLista.get(i);
                System.out.println(ismeret + ": " + count + " db munkakör betöltéséhez szükséges.\n");
            }
            System.out.println("\n");

            // 5. lekérdezés:
            // Az ügyfelek látogatásának hosszának, valamint az ügyfelek neveinek kiíratása.
            NodeList ugyfelNodes = inputDocument.getElementsByTagName("ugyfel");
            System.out.println("5. Ügyfelek látogatásának hossza: ");
            System.out.println("__________________________________");

            // Dátum formázó:
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            for (int i = 0; i < ugyfelNodes.getLength(); i++) {
                Element ugyfelElement = (Element) ugyfelNodes.item(i);

                // Ügyfelek neveinek lekérése:
                String ugyfelNev = ugyfelElement.getElementsByTagName("nev").item(0).getTextContent();

                // Belepes és kilepes időpontok lekérése az XMLfájlból:
                String belepesString = ugyfelElement.getElementsByTagName("belepes").item(0).getTextContent();
                String kilepesString = ugyfelElement.getElementsByTagName("kilepes").item(0).getTextContent();

                // Időpontok átalakítása Date objektummá:
                Date belepesDate = dateFormat.parse(belepesString);
                Date kilepesDate = dateFormat.parse(kilepesString);

                // Okményirodában töltött idő kiszámítása percekben:
                long eltoltottIdoMillis = kilepesDate.getTime() - belepesDate.getTime();
                long eltoltottIdoPercek = eltoltottIdoMillis / (60 * 1000);

                // Eredmény kiíratása konzolra:
                System.out.println("\n/" + (i + 1) + "/");
                System.out.println(ugyfelNev + " látogatása során " + eltoltottIdoPercek
                        + " percet töltött az okmányirodában.\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    // Elementeket tartalmazó NodeList konvertálása List-té:
    private static List<Element> getNodeListAsList(NodeList nodeList) {
        // Üres lista létrehozása, amely tartalmazza az Elementeket:
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Castolja az adott Node-ot Element típusra, majd hozzáadja a listához:
                elements.add((Element) node);
            }
        }
        return elements;
    }

    // Comparator, ami abc sorrendbe helyezi az elemeket:
    private static class ElementComparator implements Comparator<Element> {
        @Override
        public int compare(Element e1, Element e2) {
            String name1 = e1.getElementsByTagName("nev").item(0).getTextContent();
            String name2 = e2.getElementsByTagName("nev").item(0).getTextContent();
            return name1.compareTo(name2); // compareTo --> Compares two strings lexicographically.
        }
    }

}
