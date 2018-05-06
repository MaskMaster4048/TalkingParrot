package external.jsoncreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	}
	
	/**
	 * adds a JsonObject into the "uncompiled" list of items
	 * @param j The JsonObject to be added
	 */
	public void add(JsonObject j) {
		objects.add(j);
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
	 * Gets a JsonObject list from a string in Json file format
	 * @param s The string in Json file format
	 * @return A JsonObject list
	 * @throws NotAJsonFileException Thrown if the string is not in Json file format
	 */
	public static JsonObject[] getListFromString(String s) throws NotAJsonFileException {
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
			if(line.charAt(center+3)=='\"') {
				value = line.substring(center+4, line.indexOf("\"", center+5)); //last ""
				line = input.next();
			} else {
				value = "{\n";
				line = input.next();
				while (line.indexOf("\"") != WSPC) {
					value = value+line+"\n";
					line = input.next();
				}
			}
			array.add(new JsonObject(title, value));
		}
		input.close();
		JsonObject[] j = new JsonObject[array.size()];
		return array.toArray(j);
	}
	
	/**
	 * Writes to the given file
	 * @throws FileNotFoundException if the file given is not found
	 */
	public void WriteToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		JsonObject[] list = new JsonObject[objects.size()];
		list = objects.toArray(list);
		out.print(getFileFromList(list));
		out.close();
	}
}
