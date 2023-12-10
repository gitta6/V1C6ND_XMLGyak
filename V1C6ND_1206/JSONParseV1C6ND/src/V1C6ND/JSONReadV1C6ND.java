package V1C6ND_1206.JSONParseV1C6ND.src.V1C6ND;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import javax.security.auth.login.AccountException;

public class JSONReadV1C6ND {

	public static void main(String[] args) {
		try (FileReader reader = new FileReader("orarendV1C6ND.json")) {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONObject root = (JSONObject) jsonObject.get("V1C6ND_orarend");
			JSONArray lessons = (JSONArray) root.get("ora");

			// Óra adatok kiírása a konzolra:
			System.out.println("Órarend:\n");
			for (int i = 0; i < lessons.size(); i++) {
				JSONObject lesson = (JSONObject) lessons.get(i);
				JSONObject time = (JSONObject) lesson.get("idopont");
				System.out.println("ID: " + lesson.get("id"));
				System.out.println("Tárgy: " + lesson.get("targy"));
				System.out.println(
						"Időpont: " + time.get("nap") + " " + time.get("kezdes") + "-" + time.get("befejezes"));
				System.out.println("Helyszín: " + lesson.get("helyszin"));
				System.out.println("Oktató: " + lesson.get("oktato"));
				System.out.println("Szak: " + lesson.get("szak") + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}