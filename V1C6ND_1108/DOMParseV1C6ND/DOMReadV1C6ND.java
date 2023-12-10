package V1C6ND_1108.DOMParseV1C6ND;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class DOMReadV1C6ND {
    public static void main(String[] args) {

        try {
        	File xml = new File("V1C6ND_1108\\v1c6nd_kurzusfelvetel.xml");
            
            try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
                String ln;
                while ((ln = br.readLine()) != null) {
                    System.out.println(ln);
                }
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}