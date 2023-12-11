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

public class DOMWriteV1C6ND {

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File outFile = new File("XMLTaskV1C6ND\\XMLV1C6ND1.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document outputDocument = docBuilder.newDocument();

        Element rootElement = outputDocument.createElementNS("XMLV1C6ND", "V1C6ND_okmanyiroda");
        outputDocument.appendChild(rootElement);
        outputDocument.setXmlVersion("1.0");

        // Dolgozó elemek létrehozása és hozzáadása a gyökér elemhez:
        rootElement.appendChild(
                createDolgozo(outputDocument, "1", "14725AE", "Toth Erzsebet", "1985-07-11", "3300", "Eger",
                        "Szechenyi ut", "2", "1"));
        rootElement.appendChild(
                createDolgozo(outputDocument, "2", "65815FV", "Nagy Zsigmond", "1970-01-05", "3500", "Miskolc",
                        "Kossuth utca", "36A", "2"));
        rootElement.appendChild(
                createDolgozo(outputDocument, "3", "148575UK", "Kovacs Levente", "1995-12-08", "3434", "Malyi",
                        "Petofi ut", "50", "2"));

        // Munkakor elemek:
        rootElement.appendChild(createMunkakorok(outputDocument, "1", "NAV ugyintezo"));
        rootElement.appendChild(createMunkakorok(outputDocument, "2", "gepjarmu ugyintezo"));
        rootElement.appendChild(createMunkakorok(outputDocument, "3", "CSOK ugyintezo"));
        rootElement.appendChild(createMunkakorok(outputDocument, "3", "palyazati ugyintezo"));

        // Szerep elemek:
        rootElement.appendChild(createSzerep(outputDocument, "1", "2", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "1", "3", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "1", "4", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "2", "1", "1"));
        rootElement.appendChild(createSzerep(outputDocument, "3", "4", "0"));
        rootElement.appendChild(createSzerep(outputDocument, "3", "2", "0"));

        // Munkakör ismeretek elemek:
        rootElement.appendChild(createMunkakorIsm(outputDocument, "1", "adobevallas kezelese"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "1", "adozasi ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "MS Office ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "forgalmi engedely kezelese"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "adasveteli szerzodes kezelese"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "2", "torzskonyv kezelese"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "3", "MS Office ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "3", "jogi ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "3", "szamviteli ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "4", "MS Office ismeretek"));
        rootElement.appendChild(createMunkakorIsm(outputDocument, "4", "jogi ismeretek"));

        // Ablak elemek:
        rootElement.appendChild(createAblak(outputDocument, "1", "adobevallas", "2"));
        rootElement.appendChild(createAblak(outputDocument, "2", "személyig.", "4"));
        rootElement.appendChild(createAblak(outputDocument, "3", "szemelyes iratok", "3"));

        // Ügyfél elemek:
        rootElement
                .appendChild(createUgyfel(outputDocument, "1", "577403KE", "Hegyi Karoly", "1969-09-15", "3432", "Emod",
                        "Kossuth ut", "15", "3", "2022-11-18T08:06:00", "2022-11-18T08:24:00"));
        rootElement.appendChild(
                createUgyfel(outputDocument, "2", "775491HB", "Kis Elek", "1979-04-05", "3300", "Eger", "Petofi ut",
                        "6B", "5", "2022-11-18T08:10:00", "2022-11-18T08:25:00"));
        rootElement.appendChild(
                createUgyfel(outputDocument, "3", "224546DF", "Toth Maria", "1980-12-25", "3400", "Mezokovesd",
                        "Matyas kiraly ut", "189", "2", "2022-11-18T08:11:00", "2022-11-18T08:29:00"));

        // Felettes elemek:
        rootElement.appendChild(createFelettes(outputDocument, "1", "468534WW", "Piros Rozsa"));
        rootElement.appendChild(createFelettes(outputDocument, "2", "795341AC", "Szabo Tamas"));
        rootElement.appendChild(createFelettes(outputDocument, "3", "861888JB", "Lengyel Laszlo"));

        // XML transzformációhoz szükséges Transformer objektum inicializálása:
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Kimeneti szöveg formázása:
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Karakterkódolás beállítása
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5"); // Behúzás mértékének
                                                                                         // beállítása
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formázás engedélyezése

        // DOMSource létrehozása az outputDocument alapján:
        DOMSource source = new DOMSource(outputDocument);

        // Kiíratás konzolra és fájlba:
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(outFile);
        transformer.transform(source, console);
        transformer.transform(source, file);
    }

    // "dolgozo" elem létrehozása a hozzátartozó tulajdonságokkal:
    private static Node createDolgozo(Document outputDocument, String did, String szig, String nev, String szulido,
            String irsz, String varos, String utca, String hsz, String fid) {
        Element dolgozo = outputDocument.createElement("dolgozo");

        dolgozo.setAttribute("did", did);
        dolgozo.setAttribute("fid", fid);
        dolgozo.appendChild(createDolgozoElement(outputDocument, "szig", szig));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "nev", nev));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "szulido", szulido));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "irsz", irsz));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "varos", varos));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "utca", utca));
        dolgozo.appendChild(createDolgozoElement(outputDocument, "hsz", hsz));

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

    // Különböző adatokat tartalmazó gyermek elemek létrehozása a "munkakorok"
    // elemhez:
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
        // Az tulajdonság többértékűsége miatt van szükség erre a metódusra:
        Element munkakorIsm = findMunkakorIsmElement(outputDocument, mid);

        // Ellenőrizzük, hogy létezik-e már "munkakor_ism" elem a megadott "mid"
        // azonosítóval:
        if (munkakorIsm == null) {
            munkakorIsm = outputDocument.createElement("munkakor_ism");
            munkakorIsm.setAttribute("mid", mid);

            // Az új "munkakor_ism" elem hozzáadásra kerül:
            outputDocument.getDocumentElement().appendChild(munkakorIsm);
        }

        // Létrehozunk egy új "ismeretek" elemet és beállítjuk annak tartalmát:
        Element ismeretekElement = outputDocument.createElement("ismeretek");
        ismeretekElement.setTextContent(ismeretek);

        // Hozzáadjuk a "munkakor_ism" elemhez:
        munkakorIsm.appendChild(ismeretekElement);

        return munkakorIsm;
    }

    private static Node createAblak(Document outputDocument, String aid, String ugykor, String did) {
        Element ablak = outputDocument.createElement("ablak");

        ablak.setAttribute("aid", aid);
        ablak.setAttribute("did", did);
        ablak.appendChild(createAblakElement(outputDocument, "ugykor", ugykor));

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
        ugyfel.setAttribute("aid", aid);
        ugyfel.appendChild(createUgyfelElement(outputDocument, "szig", szig));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "nev", nev));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "szulido", szulido));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "irsz", irsz));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "varos", varos));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "utca", utca));
        ugyfel.appendChild(createUgyfelElement(outputDocument, "hsz", hsz));
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

    private static Element findMunkakorIsmElement(Document outputDocument, String mid) {
        // Az összes "munkakor_ism" elem megkeresése a dokumentumból:
        NodeList munkakorIsmList = outputDocument.getElementsByTagName("munkakor_ism");

        for (int i = 0; i < munkakorIsmList.getLength(); i++) {
            Element munkakorIsmElement = (Element) munkakorIsmList.item(i);

            // Ellenőrzés: az elem "mid" attribútuma megegyezik a keresett "mid"-del?
            if (munkakorIsmElement.getAttribute("mid").equals(mid)) {
                // Ha igen, visszaadjuk az elemet:
                return munkakorIsmElement;
            }
        }
        return null;
    }
}
