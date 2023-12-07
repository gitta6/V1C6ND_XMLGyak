package V1C6ND;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import javax.security.auth.login.AccountException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class JSONreadV1C6ND {
	
	public static void main(String[] args) {
		ConditionObject object = new ConditionObject(); 	
		JSONParser parser = new JSONParser();		 
		try (Reader reader = new FileReader("orarendV1C6ND.json")) {
	        ConditionObject jsonObject = (ConditionObject) parser.parse(reader);    // Parse-olja a JSON fájlt és elmenti egy ConditionObject-be	       
	        object.put("root", jsonObject.get("root"));   // A ConditionObject-et beállítja a "root" kulccsal a jsonObject-ban
	        String jsonText = object.toString();  // Szöveggé alakítás
	        System.out.println(jsonText);   // Kiíratás konzolra
	    } catch (IOException |  AccountException e) {
	        e.printStackTrace();
	    }
	}
}