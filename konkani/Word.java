/* Created by James DSouza: Date June 27 2019*/
/* Change History
1. created initial file on June 27 2019 - jamesdsouza


*/

package konkani;

class Word
{
	private String sWord;
	public boolean isNoun;
	
	public Word(String sWord)
	{
		this.sWord = sWord;
	}
	
	public String value()
	{
		return sWord;
	}
	
	public void value(String sWord)
	{
		this.sWord = sWord;
	}
	
}