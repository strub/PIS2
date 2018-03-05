package graphics_tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import tc.TC;

public class dataReader {
	private HashMap<String, Integer> Users ;
	private String filename ;
	
	public dataReader(String filename){
		this.filename = filename ;
		this.Users = new HashMap<String, Integer>() ;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			reader.readLine() ; reader.readLine() ;
	    	String ligne ;
	    	String[] tabFrom ;

	    	while((ligne = reader.readLine()) != null) { 
	    		tabFrom=TC.motsDeChaine(ligne);
	    		int score = Integer.parseInt(tabFrom[tabFrom.length - 1]) ;
	    		String name = tabFrom[0] ;
	    		for(int i = 1 ; i < tabFrom.length - 1 ; i++){
	    			name += " " + tabFrom[i] ;
	    		}
	    		Users.put(name, score) ;
	    	}
//	    	for(String name : Users.keySet()){
//	    		System.out.println("le joueur "+ name + " a fait " + Users.get(name));
//	    	}

	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean contains(String name){
		return Users.containsKey(name) ;
	}
	
	public String scoreToString(String name){
		return ""+ Users.get(name) ;
	}
	
	public int getScore(String name){
		return Users.get(name);
	}
	
	public void add(String name){
		Users.put(name, 0) ;
		if(contains(name)){
			return ;
		}
		try {
			FileWriter fw = new FileWriter(filename, true) ;
			BufferedWriter output = new BufferedWriter(fw) ;
			output.write(name + " " + 0);
			output.newLine();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
