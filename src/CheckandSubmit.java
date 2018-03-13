

import java.util.HashSet;
import java.util.LinkedList;

import data_treatment.ConcurrentQueue;
import data_treatment.Dictionary;

public class CheckandSubmit implements Runnable {
	private final Dictionary dictionary ;
	private final ConcurrentQueue<String> pile ;
	private Integer points ;
	private HashSet<String> words_used ;
	private graphics g ;
	
	public CheckandSubmit(String filename, ConcurrentQueue<String> pile, int points, LinkedList<String> incorrect_words, graphics g ){
		this.dictionary = new Dictionary(filename) ;
		this.pile = pile ;
		this.points = points ;
		this.words_used = new HashSet<String>() ;
		this.g= g ;
		
	}
	
	
	@Override
	public void run() {
		while(true){
			try {
				String word = pile.take() ;
				int bonus = word.length() ;
				if(words_used.contains(word)){
					points -= bonus ;
					g.incorrectNOW.setText(g.incorrectNOW.getText() + word + " (used twice) | "); 
				}
				else{
					if(dictionary.contains(word)){
						points += bonus ;
						words_used.add(word) ;
						g.textNOW.append(word + " | ");
						
					}
					else{
						points -= bonus ;
						g.incorrectNOW.setText( g.incorrectNOW.getText() + word + " | " );
						
					}
				}
				g.scoreNOW.setText(""+points);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
