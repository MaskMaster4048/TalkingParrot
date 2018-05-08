package external.jsoncreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Perhaps what you could call "the control panel" of the Json library. This is where the file is written, where text is
 * produced, et cetera.
 * @author Logan Plumlee
 * @since 2018-5-6
 */
public class Json{
	private File file;
	private ArrayList<JsonObject> objects = new ArrayList<JsonObject>();
	private final static String spacing = "    ";
	
	/**
	 * creating the Json file
	 * @param f The file to be written/read
	 */
	public Json(File f) {
		file = f;
		try {
			JsonObject[] listObj = getObjects();
			for (JsonObject j : listObj) objects.add(j);
		} catch (NotAJsonFileException e) {
			clearFile();
			e.printStackTrace();
		}
	}
	
	/**
	 * creating the Json file
	 * @param f The name of the file to be written/read
	 */
	public Json(String f) {
		file = new File(f);
		try {
			JsonObject[] listObj = getObjects();
			for (JsonObject j : listObj) objects.add(j);
		} catch (NotAJsonFileException e) {
			clearFile();
			e.printStackTrace();
		}
	}
	
	/**
	 * adds a JsonObject into the "uncompiled" list of items
	 * @param j The JsonObject to be added
	 */
	public void add(JsonObject j) {
		objects.add(j);
		try {
			writeToFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets a JsonObject[] and turns it into a string representing how it would be laid out like in a Json file
	 * used before writing the file
	 * @param list the list of JsonObjects
	 * @return A string representing a Json file
	 */
	protected static String getFileFromList(JsonObject[] list) {
		String out = "{\n";
		for (int i=0; i<list.length-1; i++) {//saves the last one, because the format is different (no comma)
			out = out+spacing+"\""+list[i].getTitle()+"\": "+((list[i].getValue().substring(0, 1).equals("{"))?
					addSpacing(list[i].getValue()):"\""+list[i].getValue()+"\"")+",\n";
		}
		out = out+spacing+"\""+list[list.length-1].getTitle()+"\": "+((list[list.length-1].getValue().substring(0, 1)
				.equals("{"))? addSpacing(list[list.length-1].getValue()):"\""+list[list.length-1].getValue()+"\"")+"\n}";
		return out;
	}
	
	/**
	 * "tabs" out a multi-line string
	 * @param value the String to be spaced
	 * @return the spaced String
	 */
	private static String addSpacing(String value) {
		return value.replace("\n", "\n"+spacing);
	}
	
	/**
	 * "untabs" out a multi-line string
	 * @param value the String to be unspaced
	 * @return the unspaced String
	 */
	private static String removeSpacing(String value) {
		return value.replace("\n"+spacing, "\n");
	}
	
	/**
	 * gets the linked file and creates objects out of it
	 * @return the list of all the objects
	 * @throws NotAJsonFileException Thrown if the file is not in Json file format
	 */
	public JsonObject[] getObjects() throws NotAJsonFileException {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(file.toPath());
		} catch (IOException e) { e.printStackTrace(); }
		return getListFromString(new String(encoded));
	}
	
	/**
	 * Gets a JsonObject list from a string in Json file format
	 * @param s The string in Json file format
	 * @return A JsonObject list
	 * @throws NotAJsonFileException Thrown if the string is not in Json file format
	 */
	protected static JsonObject[] getListFromString(String s) throws NotAJsonFileException {
		ArrayList<JsonObject> array = new ArrayList<JsonObject>();
		short WSPC = (short) spacing.length(); //the amount of white
		/*setting up to read and making sure that it's a Json File before continuing*/
		Scanner input = new Scanner(s);
		input.useDelimiter("\n"); //sets the scanner to output a line at a time
		String line = input.next(); //get's the first line (should be "{" if it's a Json file)
		if(!line.equals("{")) {
			input.close();
			throw new NotAJsonFileException(); //makes sure it's a Json File
		}
		line = input.next();
		/*end set up*/
		while (input.hasNext()) {
			//"line = input.next();" is at the end of the loop
			if(line.indexOf("\"") != WSPC) {
				input.close();
				throw new NotAJsonFileException();
			}
			int center = line.indexOf("\": "); //I used it 3+ times so i thought i might as well make a var..
			String title = line.substring(line.indexOf("\"")+1, center); //everything within the first ""
			String value = "";
			JsonObject currObj = null;
			if(line.charAt(center+3)=='\"') {
				value = line.substring(center+4, line.indexOf("\"", center+5)); //last ""
				line = input.next();
				currObj = new JsonObject(title, value);
			} else {
				value = "{\n";
				line = input.next();
				while (line.indexOf("\"") != WSPC) {
					value = value+line+"\n";
					line = input.next();
				}
				if(value.charAt(value.length()-2)==',') value = value.substring(0, value.length()-2);
				value = removeSpacing(value);
				currObj = (JsonObject) new JsonArray(title, value);
			}
			array.add(currObj);
		}
		input.close();
		JsonObject[] j = new JsonObject[array.size()];
		return array.toArray(j);
	}
	
	/**
	 * Writes to the given file
	 * @throws FileNotFoundException if the file given is not found
	 */
	private void writeToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		JsonObject[] list = new JsonObject[objects.size()];
		list = objects.toArray(list);
		out.print(getFileFromList(list));
		out.close();
	}
	
	private void clearFile() {
		if(file.exists()) {
			try {
				PrintWriter out = new PrintWriter(file);
				out.print("");
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
