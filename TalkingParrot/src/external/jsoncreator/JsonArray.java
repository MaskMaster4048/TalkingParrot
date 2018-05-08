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
	public JsonArray(String t, JsonObject[] l) {
		super(t, Json.getFileFromList(l));
		list = l;
	}
	
	/**
	 * creates a JsonArray
	 * @param t The title of the JsonArray
	 * @param v The value of the JsonArray
	 */
	protected JsonArray(String t, String v) {
		super(t,v);
		try {
			list = Json.getListFromString(v);
		} catch (NotAJsonFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the list
	 */
	public JsonObject[] getChildren() {
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
	@Deprecated
	public void addToList(JsonObject j) {
		JsonObject[] oldList = list;
		list = new JsonObject[oldList.length+1];
		System.arraycopy(oldList, 0, list, 0, oldList.length);
		list[oldList.length] = j;
		value = Json.getFileFromList(list);
	}
}
