package testing;

import java.io.File;
import java.io.FileNotFoundException;

import external.jsoncreator.*;

public class TestingControl {

	public static void main(String[] args) {
		File f = new File("testFile.json");
		JsonObject d = new JsonObject("int", 56);
		
		JsonObject a = new JsonObject("int", 42);
		JsonObject b = new JsonObject("char", 'G');
		JsonObject c = new JsonObject("String", "Dogwich");
		JsonObject[] array = {a,b,c};
		JsonArray ar = new JsonArray("Array", array);
		
		JsonObject e = new JsonObject("long", 1L);
		
		Json file = new Json(f);
		file.add(d);
		file.add(ar);
		file.add(e);
		try {
			file.WriteToFile();
		} catch (FileNotFoundException e1) {
			System.out.println("file not found");
			e1.printStackTrace();
		}
		System.out.println("It worked so far..");
		}

}
