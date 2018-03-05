import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

import graphics_tools.givesec;

public class Running implements Runnable {
	private javax.swing.JLabel Time;
	private final Lock lock ;
	private final Condition notOver ;
	private graphics g ;
	int m = 5 ;
	int t = 60 ; //m*60 ;
	
	Running(javax.swing.JLabel Time, graphics g ){
        lock = new ReentrantLock() ;
        notOver= lock.newCondition() ;
        this.Time = Time ;
    	this.g = g ;
    	
	}
	
	@Override
	public void run() {
		int decpt = t ;
        while(true){
        	try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	decpt-- ; 
        	Time.setText(toString(decpt));
        	if(decpt==0) {
        		JOptionPane.showMessageDialog(Time, "Over !");
        		break ;
        	}
        }
		g.stop(); 
	}
	
	public String toString(int t){
		int m = t/60 ;
		int s = t - m*60 ;
		
		if(s < 10){
			if(m<10){
				return "0"+m+":0"+s ;
			}
			else return m+":0"+s ;
		}
		else{
			if(m<10){
				return "0"+m+":"+s ;
			}
			else return m + ":" + s ;
		}
		
	}
	
}
