package language.ENG.lib;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private Word[] wordsFull = getWords();
	private Map<String, Integer> wordStr = new HashMap<String, Integer>();
	
	private static Word[] getWords() {
		//TODO make jsoncreator
		return null;
	}
	
	private Map<String, Integer> getWordStr(){
		//TODO go through the list of wordsFull and get the actual word for each one
	}
	
	public static void rewriteWords() {
		//TODO make jsoncreator
	}
}
