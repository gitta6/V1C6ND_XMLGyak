package V1C6ND_1206.JSONParseV1C6ND.src.V1C6ND;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.parser.JSONParser;
import org.jcp.xml.dsig.internal.dom.DOMXMLObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 

public class JSONWriteV1C6ND {
	public static void main(String[] args) {
		
		JSONObject root = new DOMXMLObject();
		
		root.put("_id", "V1C6ND");
		root.put("name", "orarend");
		
		JSONObject orarend = new JSONObject();
		JSONArray ora = new JSONArray();
		
		// 1. óra:
		JSONObject ora1 = new JSONObject();
        ora1.put("id", "1");
        ora1.put("tipus", "eloadas");        
		ora1.put("kurzusnev", "Adatkezeles XML-ben");
		ora1.put("hely", "XXXII. eloado");
		JSONObject idopont1 = new JSONObject();
		idopont1.put("nap", "Kedd");
		idopont1.put("kezdes", "12");
		idopont1.put("befejezes", "14");
		ora1.put("idopont", idopont1);
		ora1.put("oktato", "Dr. Bednarik Laszlo");
		ora1.put("szak", "BGI");
		ora.add(ora1);
		
		// 2. óra:
		JSONObject ora2 = new JSONObject();
		ora2.put("id", "2");
        ora2.put("tipus", "gyakorlat");        
		ora2.put("kurzusnev", "Adatkezeles XML-ben");
		ora2.put("hely", "Inf101");
		JSONObject idopont2 = new JSONObject();
		idopont2.put("nap", "Szerda");
		idopont2.put("kezdes", "10");
		idopont2.put("befejezes", "12");
		ora2.put("idopont", idopont2);
		ora2.put("oktato", "Dr. Bednarik Laszlo");
		ora2.put("szak", "BGI");
		ora.add(ora2);
		
		// 3. óra:
		JSONObject ora3 = new JSONObject();
        ora3.put("id", "3");
        ora3.put("tipus", "eloadas");
		ora3.put("kurzusnev", "Mesterseges Intelligencia alapjai");
		ora3.put("hely", "XXXII. eloado");
		JSONObject idopont3 = new JSONObject();
		idopont3.put("nap", "Kedd");
		idopont3.put("kezdes", "10");
		idopont3.put("befejezes", "12");
		ora3.put("idopont", idopont3);
		ora3.put("oktato", "Kunne dr. Tamas Judit");
		ora3.put("szak", "BGI");
		ora.add(ora3);
		
		// 4. óra:
		JSONObject ora4 = new JSONObject();
        ora4.put("id", "4");
        ora4.put("tipus", "gyakorlat");
		ora4.put("kurzusnev", "Mesterseges Intelligencia alapjai");
		ora4.put("hely", "XXXII. eloado");
		JSONObject idopont4 = new JSONObject();
		idopont4.put("nap", "Csutortok");
		idopont4.put("kezdes", "10");
		idopont4.put("befejezes", "12");
		ora4.put("idopont", idopont4);
		ora4.put("oktato", "Fazekas Levente");
		ora4.put("szak", "BGI");
		ora.add(ora4);
		
		// 5. óra:
       	JSONObject ora5 = new JSONObject();
        ora3.put("id", "5");
        ora3.put("tipus", "eloadas");
		ora3.put("kurzusnev", "Penzugytan");
		ora3.put("hely", "XXXVII. eloado");
		JSONObject idopont5 = new JSONObject();
		idopont5.put("nap", "Hetfo");
		idopont5.put("kezdes", "10");
		idopont5.put("befejezes", "12");
		ora5.put("idopont", idopont5);
		ora5.put("oktato", "Dr. Bozsik Sandor");
		ora5.put("szak", "BGI");
		ora.add(ora5);
		
		orarend.put("ora", ora);
		root.put("orarend", orarend);
		
		String jsonText = root.toString();
        System.out.println(jsonText);
		try {
	        FileWriter file = new FileWriter("orarendV1C6ND1.json");
	        file.write(jsonText);
	        file.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}