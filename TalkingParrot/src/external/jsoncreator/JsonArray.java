package external.jsoncreator;

/**
 * A JsonObject that holds a list of other JsonObjects
 * @author Logan Plumlee
 * @since 2018-5-3
 *
 */
public class JsonArray extends JsonObject{
	private JsonObject[] list;
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, String v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, int v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, double v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, float v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, long v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, short v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, char v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, byte v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * Creates a JsonArray
	 * @param t Title of the object
	 * @param v Value of the object
	 * @param l List of children in the object
	 */
	public JsonArray(String t, boolean v, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}


	/**
	 * @return the list
	 */
	public JsonObject[] get() {
		return list;
	}


	/**
	 * @param list The list to set
	 */
	public void setList(JsonObject[] l) {
		value = Json.getFileFromList(l);
		list = l;
	}
	
	/**
	 * Adds an object to the end of the array in emergency cases
	 * @param j the JsonObject to be added
	 */
	public void addToList(JsonObject j) {
		JsonObject[] oldList = list;
		list = new JsonObject[oldList.length+1];
		System.arraycopy(oldList, 0, list, 0, oldList.length);
		list[oldList.length] = j;
		value = Json.getFileFromList(list);
	}
}
