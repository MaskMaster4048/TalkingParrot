package external.jsoncreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Json{
	private File file;
	private ArrayList<JsonObject> objects = new ArrayList<JsonObject>();
	private final static String spacing = "    ";
	
	public Json(File f) {
		file = f;
	}
	
	public void add(JsonObject j) {
		objects.add(j);
	}
	
	public static String getFileFromList(JsonObject[] list) {
		String out = "{\n";
		for (int i=0; i<list.length-1; i++) {//saves the last one, because the format is different (no comma)
			out = out+spacing+"\""+list[i].getTitle()+"\": "+((list[i].getValue().substring(0, 1)=="{")?
					list[i].getValue():"\""+list[i].getValue()+"\"")+",\n";
		}
		out = out+spacing+"\""+list[list.length-1].getTitle()+"\": "+((list[list.length-1].getValue().substring(0, 1)=="{")?
				list[list.length-1].getValue():"\""+list[list.length-1].getValue()+"\"")+"\n}";
		return out;
	}
	
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
	
	public void WriteToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		JsonObject[] list = new JsonObject[objects.size()];
		list = objects.toArray(list);
		out.print(getFileFromList(list));
		out.close();
	}
}
