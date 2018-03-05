package data_treatment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import tc.TC;

public class Dictionary {

	private final HashSet<String> correct_words ;
	private int number_words ;
	
	public Dictionary(String filename){
		this.number_words = 0 ;
		this.correct_words = new HashSet<String>() ;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			reader.readLine() ; 
			String ligne ;
			while((ligne = reader.readLine()) != null) { 
				correct_words.add(ligne) ;
				number_words++ ;
			}}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(String word){
		this.correct_words.add(word) ;
	}
	
	public boolean contains(String word){
		return this.correct_words.contains(word) ;
	}
	
    public static void main(String args[]) {
    	Dictionary essai = new Dictionary("dictionary/dictionary.txt") ;
    	System.out.println(essai.number_words);
    }
	
}


