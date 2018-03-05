package data_treatment;

import java.util.LinkedList;

public class CheckandSubmit implements Runnable {
	private final Dictionary dictionary ;
	private final ConcurrentQueue<String> pile ;
	private Integer points ;
	private LinkedList<String> incorrect_words ;
	private javax.swing.JLabel scoreNOW;
	
	public CheckandSubmit(String filename, ConcurrentQueue<String> pile, int points, LinkedList<String> incorrect_words, javax.swing.JLabel score_Text ){
		this.dictionary = new Dictionary(filename) ;
		this.pile = pile ;
		this.points = points ;
		this.incorrect_words = incorrect_words;
		this.scoreNOW = score_Text ;
		
	}
	
	
	@Override
	public void run() {
		while(true){
			try {
				String word = pile.take() ;
				int bonus = word.length() ;
				if(dictionary.contains(word)){
					points += bonus ;
				}
				else{
					points -= bonus ;
					this.incorrect_words.add(word) ;
				}
				scoreNOW.setText(""+points);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
