package V1C6ND_beadando.DOMParseV1C6ND.src.hu.domparse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

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

public class DOMReadV1C6ND {

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File inFile = new File("V1C6ND_beadando\\XMLV1C6ND.xml");
        File outFile = new File("V1C6ND_beadando\\XMLV1C6ND1.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document inputDocument = docBuilder.parse(inFile);
        Document outputDocument = docBuilder.newDocument();

        Element rootElement = outputDocument.createElementNS("XMLV1C6ND", "okmanyiroda");
        outputDocument.appendChild(rootElement);

        // Dolgozó elemek létrehozása és hozzáadása a gyökér elemhez:
        rootElement.appendChild(
                createDolgozo(outputDocument, "1", "14725AE", "Tóth Erzsébet", "1985-07-11", "3300", "Eger",
                        "Széchenyi út", "2", "1"));
        rootElement.appendChild(
                createDolgozo(outputDocument, "2", "65815FV", "Nagy Zsigmond", "1970-01-05", "3500", "Miskolc",
                        "Kossuth utca", "36A", "2"));
        rootElement.appendChild(
                createDolgozo(outputDocument, "3", "148575UK", "Kovács Levente", "1995-12-08", "3434", "Mályi",
                        "Petőfi út", "50", "2"));

        // Munkakör elemek:
        rootElement.appendChild(createMunkakorok(outputDocument, "1", "NAV ügyintéző"));
        rootElement.appendChild(createMunkakorok(outputDocument, "2", "gépjármű ügyintéző"));
        rootElement.appendChild(createMunkakorok(outputDocument, "3", "CSOK ügyintéző"));

        // Szerep elemek:
        rootElement.appendChild(createSzerep(outputDocument, "1", "2", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "1", "3", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "1", "8", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "2", "1", "1"));
        rootElement.appendChild(createSzerep(outputDocument, "3", "4", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "3", "5", "0"));

        // Munkakör ismeretek elemek:
        rootElement.appendChild(createMunkakorIsm(outputDocument, "1", "adóbevallás kezelése"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "1", "adózási ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "1", "MS Office ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "adásvételi szerződés kezelése"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "törzskönyv kezelése"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "forgalmi engedély kezelése"));

        // Ablak elemek:
        rootElement.appendChild(createAblak(outputDocument, "1", "adóbevallás", "2"));
        rootElement.appendChild(createAblak(outputDocument, "2", "személyig.", "4"));
        rootElement.appendChild(createAblak(outputDocument, "3", "személyes iratok", "3"));

        // Ügyfél elemek:
        rootElement
                .appendChild(createUgyfel(outputDocument, "1", "577403KE", "Hegyi Károly", "1969-09-15", "3432", "Emőd",
                        "Kossuth út", "15", "3", "2022-11-18T08:06:00", "2022-11-18T08:24:00"));
        rootElement.appendChild(
                createUgyfel(outputDocument, "2", "775491HB", "Kis Elek", "1979-04-05", "3300", "Eger", "Petőfi út",
                        "6B", "5", "2022-11-18T08:10:00", "2022-11-18T08:25:00"));
        rootElement.appendChild(
                createUgyfel(outputDocument, "3", "224546DF", "Tóth Mária", "1980-12-25", "3400", "Mezőkövesd",
                        "Mátyás király út", "189", "2", "2022-11-18T08:11:00", "2022-11-18T08:29:00"));

        // Felettes elemek:
        rootElement.appendChild(createFelettes(outputDocument, "1", "468534WW", "Piros Rózsa"));
        rootElement.appendChild(createFelettes(outputDocument, "2", "795341AC", "Szabó Tamás"));
        rootElement.appendChild(createFelettes(outputDocument, "3", "861888JB", "Lengyel László"));

        // XML transzformációhoz szükséges Transformer objektum inicializálása:
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Kimeneti szöveg formázása:
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Karakterkódolás beállítása
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Behúzás mértékének
                                                                                         // beállítása
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formázás engedélyezése

        // DOMSource létrehozása az outputDocument alapján:
        DOMSource source = new DOMSource(outputDocument);

        // Kiíratás konzolra:
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outFile);

        // Az XML dokumentum átalakítása és kiírása a konzolra
        transformer.transform(source, console);

        // Az XML dokumentum átalakítása és kiírása egy fájlba
        transformer.transform(source, file);

        // Megnyitott xml file kezelése
        inputDocument.getDocumentElement().normalize();

        // Gyökér elem kiíratása:
        System.out.println("\nRoot element: " + inputDocument.getDocumentElement().getNodeName());

        // Gyermek elem listába rendezés:
        NodeList dolgozoList = inputDocument.getElementsByTagName("dolgozo");
        NodeList munkakorokList = inputDocument.getElementsByTagName("munkakorok");
        NodeList szerepList = inputDocument.getElementsByTagName("szerep");
        NodeList munkakorIsmList = inputDocument.getElementsByTagName("munkakor_ism");
        NodeList ablakList = inputDocument.getElementsByTagName("ablak");
        NodeList ugyfelList = inputDocument.getElementsByTagName("ugyfel");
        NodeList felettesList = inputDocument.getElementsByTagName("felettes");

        // Fájlba írás:
        StringWriter stringwriter = new StringWriter();
        transformer.transform(source, new StreamResult(stringwriter));
        FileWriter filewriter = new FileWriter(outFile);
        filewriter.write(stringwriter.toString());
        filewriter.close();

        // Lista feltöltés:

        // DOLGOZÓK NodeList
        for (int i = 0; i < dolgozoList.getLength(); i++) {

            // Az aktuális dolgozó Node lekérése:
            Node dolgozoNode = dolgozoList.item(i);
            System.out.println("\nAktuális elem: " + dolgozoNode.getNodeName());

            // Feltétel, hogy az elem típusa ELEMENT_NODE:
            if (dolgozoNode.getNodeType() == Node.ELEMENT_NODE) {

                // Az aktuális Node átalakítása Element típussá:
                Element elem = (Element) dolgozoNode;

                // Attribútum:
                String did = elem.getAttribute("did");

                // Az elem gyerek elemeinek lekérése és kiírása:
                Node node1 = elem.getElementsByTagName("szig").item(0);
                String szig = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("nev").item(0);
                String nev = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("szulido").item(0);
                String szulido = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("irsz").item(0);
                String irsz = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("varos").item(0);
                String varos = node5.getTextContent();

                Node node6 = elem.getElementsByTagName("utca").item(0);
                String utca = node6.getTextContent();

                Node node7 = elem.getElementsByTagName("hsz").item(0);
                String hsz = node7.getTextContent();

                Node node8 = elem.getElementsByTagName("fid").item(0);
                String fid = node8.getTextContent();

                // Az adatok kiírása:
                System.out.println("Did: " + did);
                System.out.println("Szig: " + szig);
                System.out.println("Nev: " + nev);
                System.out.println("Szulido: " + szulido);
                System.out.println("Irsz: " + irsz);
                System.out.println("Varos: " + varos);
                System.out.println("Utca: " + utca);
                System.out.println("Hsz: " + hsz);
                System.out.println("Fid: " + fid);
            }
        }

        // MUNKAKÖRÖK NodeList
        for (int i = 0; i < munkakorokList.getLength(); i++) {
            Node munkakorNode = munkakorokList.item(i);
            System.out.println("\nAktuális elem: " + munkakorNode.getNodeName());

            if (munkakorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) munkakorNode;
                String mid = elem.getAttribute("mid");

                Node node1 = elem.getElementsByTagName("megnevezes").item(0);
                String megnevezes = node1.getTextContent();

                System.out.println("Mid: " + mid);
                System.out.println("Megnevezes: " + megnevezes);
            }
        }

        // SZEREP NodeList
        for (int i = 0; i < szerepList.getLength(); i++) {
            Node szerepNode = szerepList.item(i);
            System.out.println("\nAktuális elem: " + szerepNode.getNodeName());

            if (szerepNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) szerepNode;
                String did = elem.getAttribute("did");

                Element elem2 = (Element) szerepNode;
                String mid = elem2.getAttribute("mid");

                Node node1 = elem.getElementsByTagName("helyettesit-e").item(0);
                String helyettesitE = node1.getTextContent();

                System.out.println("Did: " + did);
                System.out.println("Mid: " + mid);
                System.out.println("Helyettesit-e: " + helyettesitE);
            }
        }

        // MUNKAKÖR ISMERETEK NodeList
        for (int i = 0; i < munkakorIsmList.getLength(); i++) {
            Node munkakorIsmNode = munkakorIsmList.item(i);
            System.out.println("\nAktuális elem: " + munkakorIsmNode.getNodeName());

            if (munkakorIsmNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) munkakorIsmNode;
                String mid = elem.getAttribute("mid");

                Node node1 = elem.getElementsByTagName("ismeretek").item(0);
                String ismeretek = node1.getTextContent();

                System.out.println("Mid: " + mid);
                System.out.println("Ismeretek: " + ismeretek);
            }
        }

        // ABLAK NodeList
        for (int i = 0; i < ablakList.getLength(); i++) {
            Node ablakNode = ablakList.item(i);
            System.out.println("\nAktuális elem: " + ablakNode.getNodeName());

            if (ablakNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) ablakNode;
                String aid = elem.getAttribute("aid");

                Node node1 = elem.getElementsByTagName("ugykor").item(0);
                String ugykor = node1.getTextContent();

                Element elem2 = (Element) ablakNode;
                String did = elem2.getAttribute("did");

                System.out.println("Aid: " + aid);
                System.out.println("Ugykor: " + ugykor);
                System.out.println("Did: " + did);
            }
        }

        // ÜGYFÉL NodeList
        for (int i = 0; i < ugyfelList.getLength(); i++) {
            Node ugyfelNode = ugyfelList.item(i);
            System.out.println("\nAktuális elem: " + ugyfelNode.getNodeName());

            if (ugyfelNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) ugyfelNode;
                String uid = elem.getAttribute("uid");

                Node node1 = elem.getElementsByTagName("szig").item(0);
                String szig = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("nev").item(0);
                String nev = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("szulido").item(0);
                String szulido = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("irsz").item(0);
                String irsz = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("varos").item(0);
                String varos = node5.getTextContent();

                Node node6 = elem.getElementsByTagName("utca").item(0);
                String utca = node6.getTextContent();

                Node node7 = elem.getElementsByTagName("hsz").item(0);
                String hsz = node7.getTextContent();

                Element elem2 = (Element) ugyfelNode;
                String aid = elem2.getAttribute("aid");

                Node node8 = elem.getElementsByTagName("belepes").item(0);
                String belepes = node8.getTextContent();

                Node node9 = elem.getElementsByTagName("kilepes").item(0);
                String kilepes = node9.getTextContent();

                System.out.println("Uid: " + uid);
                System.out.println("Szig: " + szig);
                System.out.println("Nev: " + nev);
                System.out.println("Szulido: " + szulido);
                System.out.println("Irsz: " + irsz);
                System.out.println("Varos: " + varos);
                System.out.println("Utca: " + utca);
                System.out.println("Hsz: " + hsz);
                System.out.println("Aid: " + aid);
                System.out.println("Belepes: " + belepes);
                System.out.println("Kilepes: " + kilepes);
            }
        }

        // FELETTES NodeList
        for (int i = 0; i < felettesList.getLength(); i++) {
            Node felettesNode = felettesList.item(i);
            System.out.println("\nAktuális elem: " + felettesNode.getNodeName());

            if (felettesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) felettesNode;
                String fid = elem.getAttribute("fid");

                Node node1 = elem.getElementsByTagName("szig").item(0);
                String szig = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("nev").item(0);
                String nev = node2.getTextContent();

                System.out.println("Fid: " + fid);
                System.out.println("Szig: " + szig);
                System.out.println("Nev: " + nev);
            }
        }

        System.out.println("\nSikeres fájlba írás.");
    }

    // "dolgozo" elem létrehozása a hozzátartozó tulajdonságokkal:
    private static Node createDolgozo(Document outputDocument, String did, String szig, String nev, String szulido,
            String irsz, String varos, String utca, String hsz, String fid) {
        Element dolgozo = outputDocument.createElement("dolgozo");

        dolgozo.setAttribute("did", did);
        dolgozo.appendChild(createDolgozoElement(outputDocument, "szig", szig));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "nev", nev));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "szulido", szulido));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "irsz", irsz));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "varos", varos));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "utca", utca));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "hsz", hsz));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "fid", fid));

        return dolgozo;
    }

    // Különböző adatokat tartalmazó gyermek elemek létrehozása a "dolgozo" elemhez:
    private static Node createDolgozoElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    // "munkakorok" elem létrehozása a hozzátartozó tulajdonságokkal:
    private static Node createMunkakorok(Document outputDocument, String mid, String megnevezes) {
        Element munkakorok = outputDocument.createElement("munkakorok");

        munkakorok.setAttribute("mid", mid);
        munkakorok.appendChild(createMunkakorokElement(outputDocument, "megnevezes", megnevezes));

        return munkakorok;
    }

    // Különböző adatokat tartalmazó gyermek elemek létrehozása a "munkakorok" elemhez:
    private static Node createMunkakorokElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    private static Node createSzerep(Document outputDocument, String did, String mid, String helyettesit) {
        Element szerep = outputDocument.createElement("szerep");

        szerep.setAttribute("did", did);
        szerep.setAttribute("mid", mid);
        szerep.appendChild(createSzerepElement(outputDocument, "helyettesit-e", helyettesit));

        return szerep;
    }

    private static Node createSzerepElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    private static Node createMunkakorIsm(Document outputDocument, String mid, String ismeretek) {
        Element munkakorIsm = outputDocument.createElement("munkakor_ism");

        munkakorIsm.setAttribute("mid", mid);
        munkakorIsm.appendChild(createMunkakorIsmElement(outputDocument, "ismeretek", ismeretek));

        return munkakorIsm;
    }

    private static Node createMunkakorIsmElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    private static Node createAblak(Document outputDocument, String aid, String ugykor, String did) {
        Element ablak = outputDocument.createElement("ablak");

        ablak.setAttribute("aid", aid);
        ablak.appendChild(createAblakElement(outputDocument, "ugykor", ugykor));
        ablak.setAttribute("did", did);

        return ablak;
    }

    private static Node createAblakElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    private static Node createUgyfel(Document outputDocument, String uid, String szig, String nev, String szulido,
            String irsz, String varos, String utca, String hsz, String aid, String belepes, String kilepes) {
        Element ugyfel = outputDocument.createElement("ugyfel");

        ugyfel.setAttribute("uid", uid);
        ugyfel.appendChild(createUgyfelElement(outputDocument, "szig", szig));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "nev", nev));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "szulido", szulido));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "irsz", irsz));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "varos", varos));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "utca", utca));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "hsz", hsz));
        ugyfel.setAttribute("aid", aid);
        ugyfel.appendChild(createUgyfelElement(outputDocument, "belepes", belepes));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "kilepes", kilepes));

        return ugyfel;
    }

    private static Node createUgyfelElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }

    private static Node createFelettes(Document outputDocument, String fid, String szig, String nev) {
        Element felettes = outputDocument.createElement("felettes");

        felettes.setAttribute("fid", fid);
        felettes.appendChild(createFelettesElement(outputDocument, "szig", szig));
        felettes.appendChild(createFelettesElement(outputDocument, "nev", nev));

        return felettes;
    }

    private static Node createFelettesElement(Document outputDocument, String name, String value) {
        Element node = outputDocument.createElement(name);
        node.appendChild(outputDocument.createTextNode(value));

        return node;
    }
}
