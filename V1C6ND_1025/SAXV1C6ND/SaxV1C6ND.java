package V1C6ND_1025.SAXV1C6ND;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxV1C6ND
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
			File inFile = new File("V1C6ND_1018\\v1c6nd_kurzusfelvetel.xml");
	        SAXParserFactory spFactory = SAXParserFactory.newInstance();
	        SAXParser sp = spFactory.newSAXParser();
	        NewHandler h = new NewHandler();
	        sp.parse(inFile, h);
	}   
}

class NewHandler extends DefaultHandler {
    static int indent = 0;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
        for (int i = 0; i < indent; i++)
            System.out.print("    ");
        System.out.print("<" + qName);
        for (int i = 0; i < attributes.getLength(); i++) 
            System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
        System.out.println(">");
        indent++;
    }
    @Override
    public void endElement(String uri, String localName, String name) throws SAXException
	{
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print("    ");

        System.out.println("</" + name + ">");
    }

    @Override
    public void characters(char c[], int start, int length) throws SAXException
	{
        String token = new String(c, start, length).trim();
        if (token.isEmpty() == false)
		{
            for (int i = 0; i < indent; i++)
                System.out.print("    ");
            System.out.println(token);
        }
    }
}