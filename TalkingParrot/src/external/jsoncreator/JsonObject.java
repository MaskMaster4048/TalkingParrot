package external.jsoncreator;

/**A very simple "line" in a Json file.
 * <b>Note:</b> this in not an "object" object, this in closer to a variable.
 * If you are looking for something to hold these objects, refer to JsonArray.
 * @author Logan Plumlee
 * @since 2018-5-3
 */
public class JsonObject {
	protected String title;
	protected String value;
	
	/**
	 * Creates a JsonObject
	 * @param t Title of the object
	 * @param v Value of the object
	 */
	 public JsonObject(String t, String v) {
		 title = t;
		 value = v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, int v) {
		 title = t;
		 value = ""+v;
	 }

	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, double v) {
		 title = t;
		 value = ""+v;
	 }

	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, float v) {
		 title = t;
		 value = ""+v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, long v) {
		 title = t;
		 value = ""+v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, short v) {
		 title = t;
		 value = ""+v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, char v) {
		 title = t;
		 value = ""+v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, byte v) {
		 title = t;
		 value = ""+v;
	 }
	 
	 /**
	  * Creates a JsonObject
	  * @param t Title of the object
	  * @param v Value of the object
	  */
	 public JsonObject(String t, boolean v) {
		 title = t;
		 value = ""+v;
	 }

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * gets children in the object, separates the Objects from the Arrays
	 * @return children in the object, if applicable
	 */
	public JsonObject[] getChildren() {
		return null;
	}
}
