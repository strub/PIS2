package graphics_tools;

import java.util.concurrent.locks.Condition;

import javax.swing.JOptionPane;

public class givesec implements Runnable {

	private javax.swing.JLabel Time;
	int m = 5 ;
	int t = 30 ; //m*60 ;
	private Condition notOver ;
	
	public givesec(javax.swing.JLabel Time, Condition notOver){
		this.Time = Time ;
		this.notOver = notOver ;
	}
	@Override
	public void run() {
        while(true){
        	try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	t-- ; 
        	Time.setText(toString());
        	if(t==0) {
        		notOver.signalAll(); 
        		JOptionPane.showMessageDialog(Time, "Over !");
        		break ;
        	}
        }
	}

	public int getSec(){
		return this.t ;
	}
	
	
	public String toString(){
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
