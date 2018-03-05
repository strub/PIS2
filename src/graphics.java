import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data_treatment.CheckandSubmit;
import data_treatment.ConcurrentQueue;
import graphics_tools.dataReader;
import graphics_tools.givesec;
import tc.TC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class graphics extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public graphics() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void start(){
    	
    	
    	Name.setEnabled(true);
    	Summit.setEnabled(true);
    	typer.setEnabled(false);
    	Runnable runnable3 = new Running(Time, this) ;
    	running = new Thread(runnable3);
    	Runnable runnable2 = new CheckandSubmit("dictionary/dictionary.txt", pile, points, incorrect_words, scoreNOW) ;
        checker = new Thread(runnable2) ;
    	
    	Summit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String finalName = Name.getText() ;
                Name.setEnabled(false);
                Summit.setEnabled(false);
                // recherche des donn�es utilisateurs 
                if(userData.contains(finalName)){
                	BestScore.setText(userData.scoreToString(finalName));
                }
                else{
                	userData.add(finalName) ;
                }
                
                
                
                running.start(); //s'arr�tera � la fin du d�compte de temps 
                checker.start(); //module de v�rification des mots
                typer.setEnabled(true); //l'utilisateur commence � jouer
                
                
            }
        });
    	
    	typer.setText("Type your words here and press Enter");
        typer.addMouseListener(new MouseAdapter(){
        	public void mousePressed(MouseEvent e){
        		if(first){
        			typer.setText("");
        			textNOW.setText("");
        			first = false ;
        		}
        		else{
        			typer.setText("");
        		}
        		
        	}
        });
        
        
        typer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String wordtoprocess = typer.getText() ;
                System.out.println(wordtoprocess);
                textNOW.append(wordtoprocess + " | ");
                if(TC.motsDeChaine(wordtoprocess).length > 1){
                	System.out.println("ERREUR");
                	typer.setText("Typing Error, one word please");
                }
                else{
                	try {
						pile.put(wordtoprocess) ;
						System.out.println("Success");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println(pile);
                    typer.setText("");
                }
                
            }
        });

    }
    
    
    public void stop(){
    	start() ;
    }
    
    private void initComponents() {
    	
    	
    	//Elements graphiques
        speedTyper_Panel = new javax.swing.JPanel();
        best_Panel = new javax.swing.JPanel();
        BestScore_Text = new javax.swing.JLabel();
        BestScore = new javax.swing.JLabel();
        name_Panel = new javax.swing.JPanel();
        Summit = new javax.swing.JButton();
        Name = new javax.swing.JTextField();
        score_Pannel = new javax.swing.JPanel();
        score_Text = new javax.swing.JLabel();
        scoreNOW = new javax.swing.JLabel();
        time_Pannel = new javax.swing.JPanel();
        onmarks_Text = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        typing_Panel = new javax.swing.JPanel();
        Typingarea_Text = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textNOW = new javax.swing.JTextArea();
        incorrect_Panel = new javax.swing.JPanel();
        incorrect_Text = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        incorrectNOW = new javax.swing.JTextPane();
        typer = new javax.swing.JTextField();
        typer.setEnabled(false);
        
        //Variables utilisateur
    	userData = new dataReader("data/User_Data.txt") ; //pr�paration donn�es utilisateurs
        pile = new ConcurrentQueue<String>() ; //les mots trait�s
    	points = 0 ;
    	incorrect_words = new LinkedList<String> () ;
        Runnable runnable3 = new Running(Time, this) ;
        running = new Thread(runnable3) ; //Thread g�rant l'affichage de l'�coulement du temps et qui g�re l'arr�t
        Runnable runnable2 = new CheckandSubmit("dictionary/dictionary.txt", pile, points, incorrect_words, scoreNOW) ;
        checker = new Thread(runnable2) ; //Thread g�rant le traitement des mots (pile) en regard d'un dictionnaire, et qui renvoit les points (donc le score) et les mots incorrects
        this.first = true ; //boolean indiquant si une partie a d�j� �t� jou�e
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        speedTyper_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("speedTyper"));
        
        /**
         * I. Cr�ation du premier Paneau contenant le meilleur score de l'utilisateur
         */
        
        best_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BestScore_Text.setText("Best score is : ");
        BestScore.setText(userData.scoreToString("Default"));

        javax.swing.GroupLayout best_PanelLayout = new javax.swing.GroupLayout(best_Panel);
        best_Panel.setLayout(best_PanelLayout);
        best_PanelLayout.setHorizontalGroup(
            best_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(best_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BestScore_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(BestScore, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        best_PanelLayout.setVerticalGroup(
            best_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, best_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BestScore_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BestScore, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        
        /**
         * II. Cr�ation du Paneau contenant le nom de l'utilisateur et du bouton Summit
         */
        
        name_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Summit.setText("Submit & Start");
        Name.setText("Your Name");
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout name_PanelLayout = new javax.swing.GroupLayout(name_Panel);
        name_Panel.setLayout(name_PanelLayout);
        name_PanelLayout.setHorizontalGroup(
            name_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(name_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Summit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        name_PanelLayout.setVerticalGroup(
            name_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(name_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Summit)
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        
        /**
         * III. Cr�ation du Paneau contenant le score de l'utilisateur 
         */
        
        score_Pannel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        score_Text.setText("Your score is : ");
        scoreNOW.setText("0");

        javax.swing.GroupLayout score_PannelLayout = new javax.swing.GroupLayout(score_Pannel);
        score_Pannel.setLayout(score_PannelLayout);
        score_PannelLayout.setHorizontalGroup(
            score_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(score_PannelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(score_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scoreNOW)
                .addGap(69, 69, 69))
        );
        score_PannelLayout.setVerticalGroup(
            score_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(score_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(score_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scoreNOW, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        
        
        /**
         * IV. Cr�ation du Paneau contenant le temps restant
         */
        
        time_Pannel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        onmarks_Text.setText("On your marks, get set, go : ");
        Time.setText("Click on Submit");

        javax.swing.GroupLayout time_PannelLayout = new javax.swing.GroupLayout(time_Pannel);
        time_Pannel.setLayout(time_PannelLayout);
        time_PannelLayout.setHorizontalGroup(
            time_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(time_PannelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(onmarks_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        time_PannelLayout.setVerticalGroup(
            time_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(time_PannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(onmarks_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addComponent(Time))
        );

        
        
        /**
         * V. Cr�ation du Paneau contenant la zone de texte
         */
        
        typing_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Typingarea_Text.setText("Typing area : ");

        textNOW.setColumns(20);
        textNOW.setLineWrap(true);
        textNOW.setRows(5);
        textNOW.setText("Please start by summitting your name, you will then have X seconds to perform at your best. ");
        jScrollPane2.setViewportView(textNOW);


        javax.swing.GroupLayout typing_PanelLayout = new javax.swing.GroupLayout(typing_Panel);
        typing_Panel.setLayout(typing_PanelLayout);
        typing_PanelLayout.setHorizontalGroup(
            typing_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typing_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(typing_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(typing_PanelLayout.createSequentialGroup()
                        .addComponent(Typingarea_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(typer))
                .addContainerGap())
        );
        typing_PanelLayout.setVerticalGroup(
            typing_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typing_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Typingarea_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(typer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        
        /**
         * VI. Cr�ation du Paneau contenant la zone des mots incorrects
         */
        
        incorrect_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        incorrect_Text.setText("Incorrect words : ");
        jScrollPane3.setViewportView(incorrectNOW);

        javax.swing.GroupLayout incorrect_PanelLayout = new javax.swing.GroupLayout(incorrect_Panel);
        incorrect_Panel.setLayout(incorrect_PanelLayout);
        incorrect_PanelLayout.setHorizontalGroup(
            incorrect_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incorrect_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(incorrect_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(incorrect_PanelLayout.createSequentialGroup()
                        .addComponent(incorrect_Text)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        incorrect_PanelLayout.setVerticalGroup(
            incorrect_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incorrect_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(incorrect_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        
        /**
         * VII. Cr�ation du GroupLayout contenant toutes les Zones
         */
        
        javax.swing.GroupLayout speedTyper_PanelLayout = new javax.swing.GroupLayout(speedTyper_Panel);
        speedTyper_Panel.setLayout(speedTyper_PanelLayout);
        speedTyper_PanelLayout.setHorizontalGroup(
            speedTyper_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speedTyper_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(speedTyper_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typing_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(best_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time_Pannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(score_Pannel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(incorrect_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        speedTyper_PanelLayout.setVerticalGroup(
            speedTyper_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speedTyper_PanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(best_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time_Pannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(score_Pannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typing_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(incorrect_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(speedTyper_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(speedTyper_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SummitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SummitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SummitActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	graphics g = new graphics() ;
                g.setVisible(true);
                g.start() ;

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BestScore;
    private javax.swing.JLabel BestScore_Text;
    private javax.swing.JTextField Name;
    private javax.swing.JButton Summit;
    private javax.swing.JLabel Time;
    private javax.swing.JLabel Typingarea_Text;
    private javax.swing.JPanel best_Panel;
    private javax.swing.JTextPane incorrectNOW;
    private javax.swing.JPanel incorrect_Panel;
    private javax.swing.JLabel incorrect_Text;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel name_Panel;
    private javax.swing.JLabel onmarks_Text;
    private javax.swing.JLabel scoreNOW;
    private javax.swing.JPanel score_Pannel;
    private javax.swing.JLabel score_Text;
    private javax.swing.JPanel speedTyper_Panel;
    private JTextArea textNOW;
    private javax.swing.JPanel time_Pannel;
    private javax.swing.JPanel typing_Panel;
    private javax.swing.JTextField typer;
    private boolean first ;
    private ConcurrentQueue<String> pile ;
	private Integer points ;
	private LinkedList<String> incorrect_words ;
	private Thread running ;
	private dataReader userData ;
	private Thread checker ;
    // End of variables declaration//GEN-END:variables
}
