package V1C6ND

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.parser.JSONParser;
import org.jcp.xml.dsig.internal.dom.DOMXMLObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class JSONwriteV1C6ND {
	public static void main(String[] args) {
		
		JSONObject root = new DOMXMLObject();
		
		root.put("_id", "V1C6ND");
		root.put("name", "orarend");
		
		JSONObject orarend = new JSONObject();
		JSONArray ora = new JSONArray();
		
		// 1.
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
		
		// 2.
		JSONObject ora2 = new JSONObject();
		ora1.put("id", "2");
        ora2.put("tipus", "gyakorlat");        
		ora2.put("kurzusnev", "Adatkezeles XML-ben");
		ora2.put("hely", "Inf101");
		
		JSONObject idopont2 = new JSONObject();
		idopont2.put("nap", "Szerda");
		idopont2.put("kezdes", "10");
		idopont2.put("befejezes", "12");
		
		ora2.put("idopont", idopont1);
		ora2.put("oktato", "Dr. Bednarik Laszlo");
		ora2.put("szak", "BGI");
		
		ora.add(ora2);
		
		// 3.
		JSONObject ora3 = new JSONObject();
        ora1.put("id", "3");
        ora2.put("tipus", "eloadas");
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
		
		// 4.
		JSONObject ora4 = new JSONObject();
		
		//...

		ora.add(ora4);
		
		// 5.
		JSONObject ora5 = new JSONObject();
		
        //...
												
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