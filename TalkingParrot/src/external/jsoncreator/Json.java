package external.jsoncreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
				list[list.length-1].getValue():"\""+list[list.length-1].getValue()+"\"")+"\n";
		return out;
	}
	
	public void WriteToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		JsonObject[] list = new JsonObject[objects.size()];
		list = objects.toArray(list);
		out.print(getFileFromList(list));
		out.close();
	}
}
