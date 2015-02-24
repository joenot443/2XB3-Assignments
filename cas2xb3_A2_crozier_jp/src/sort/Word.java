package sort;

public class Word implements Comparable<Word>{
	private String word;
	private int score;
	
	
	public Word(String w, int s)
	{
		this.word = w;
		this.score = s;
	}
	
	//Return the score of the Word
	public int getScore()
	{
		return this.score;
	}
	
	//Set the score of the Word
	public void setScore(int s)
	{
		this.score = s;
	}
	
	//Return the word string
	public String getWord()
	{

		return this.word;
	}
	//Set the word string
	public void setWord(String w)
	{
		this.word = w;
	}
	
	@Override
	public int compareTo(Word w)
	{
		//Compare the passed Word to our current Word
		//The Word with a higher score is deemed greater
		if (this.getScore() > w.getScore()) return 1;
		
		if (this.getScore() == w.getScore()) return 0;
		
		//If the function has not returned already, then this.score < w.score so we return -1
		return -1; 
	}
	
	//Returns a string representation of the word
	public String toString()
	{
		
		return this.getWord()+" "+this.getScore();
	}

}
