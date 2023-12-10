package V1C6ND_1108.DOMParseV1C6ND;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DOMWriteV1C6ND {
    public static void main(String[] args) {
    	
        try {
        	File inputFile = new File("V1C6ND_1108\\v1c6nd_kurzusfelvetel.xml");
            
            try (BufferedReader bReader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            writeXmlContentToFile(inputFile);
                
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void writeXmlContentToFile(File inputFile) {
        File outputFile = new File("V1C6ND_1108\\v1c6nd_kurzusfelvetelResult.xml");
        try (BufferedReader bReader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            String ln;
            while ((ln = bReader.readLine()) != null) {
                writer.write(ln + System.lineSeparator());
            }
            System.out.println("Az eredmény a v1c6nd_kurzusfelvetelResult.xml fájlba ki lett íratva.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
