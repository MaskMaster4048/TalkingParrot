package language.ENG.lib;

abstract class Word {
	/*
	 * order: 
	 * 
	 */
	double[] emotion;
	String[] spellings;
	
	protected Word(double[] emotion, String[] spellings) {
		this.emotion = emotion;
		this.spellings = spellings;
	}
}
