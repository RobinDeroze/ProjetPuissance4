import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * 
 */

/**
 * @author imerir
 *
 */
public class IHM extends JFrame implements ActionListener{

	IHMEcriture recept;
	
	Client c=null;
	String s ;
	NetIn th;
	boolean isYourTurn;
	int monTableauColonnePleine [];
	
	private static final long serialVersionUID = 1L;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Partie");
	JMenu menu2 = new JMenu("?");
	JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
	JMenuItem quitterPartie = new JMenuItem("Quitter Partie");
	JMenuItem aide = new JMenuItem("Aide");
	
	private JTextField tf=new JTextField();
	private JTextField tfHost=new JTextField("localhost");
	private JTextField tfNickName=new JTextField(".NickName.");
	private JTextField tfPort=new JTextField("30000");
	private JTextArea textarea=new JTextArea("Unconnected..");
	Grille maGrille = new Grille();
	private JScrollPane scroll = new JScrollPane(textarea);

	private JButton send=new JButton("Send");
	private JButton colonne1 = new JButton("Here");
	private JButton colonne2 = new JButton("Here");
	private JButton colonne3 = new JButton("Here");
	private JButton colonne4 = new JButton("Here");
	private JButton colonne5 = new JButton("Here");
	private JButton colonne6 = new JButton("Here");
	private JButton colonne7 = new JButton("Here");
	private JButton connect = new JButton("Connect");
	private JLabel HostLabel = new JLabel("Host:");
	private JLabel PortLabel = new JLabel("Port:");
	private JLabel NickLabel = new JLabel("NickName:");
	private JOptionPane Alert = new JOptionPane("Morning Sir!!");
	private JMenuBar MenuBar = new JMenuBar();
	
//------------------------------------------------------------------------------------------------	
		public IHM() {
			
			monTableauColonnePleine = new int[7];
			for(int i=0;i<7;i++) monTableauColonnePleine[i]=0;
			
			setTitle("Puissance 4");
			setJMenuBar(menuBar);
			menuBar.add(menu1);	
			menuBar.add(menu2);
			menu1.add(nouvellePartie);
			menu1.add(quitterPartie);
			menu2.add(aide);	
			
			
			textarea.setPreferredSize(new Dimension(10,140));
			textarea.setEditable(false);
		
	this.setLayout(new BorderLayout());
	
	JPanel center = new JPanel();
	JPanel east=new JPanel();
	JPanel south=new JPanel();
	JPanel north= new JPanel();
	this.add("Center",center);
	this.add("East",east);
	this.add("South",south);
	this.add("North",north);


	north.setLayout(new FlowLayout(FlowLayout.LEFT,0,2));
	
	//north.setPreferredSize(new Dimension(80,80));
	colonne1.setPreferredSize(new Dimension(80,20));
	colonne2.setPreferredSize(new Dimension(80,20));
	colonne3.setPreferredSize(new Dimension(80,20));
	colonne4.setPreferredSize(new Dimension(80,20));
	colonne5.setPreferredSize(new Dimension(80,20));
	colonne6.setPreferredSize(new Dimension(80,20));
	colonne7.setPreferredSize(new Dimension(80,20));

		
	
	north.add("West",colonne1);

	north.add("East",colonne2);
	north.add("East",colonne3);
	north.add("East",colonne4);
	north.add("East",colonne5);
	north.add("East",colonne6);
	north.add("East",colonne7);

	
	center.setLayout(new BorderLayout());
	center.setPreferredSize(new Dimension(2*80, 6*80));
	center.setBorder(new LineBorder(Color.BLACK));
	center.add("Center",maGrille);
	//
	
	east.setBorder(new LineBorder(Color.BLACK));
	east.setLayout(new GridLayout(0,1));
	east.add("North",connect);
	east.add("North",HostLabel);
	tfHost.setEditable(false);
	east.add("North",tfHost);
	
	east.add("North",PortLabel);
	tfPort.setEditable(false);
	east.add("North",tfPort);
	east.add("North",NickLabel);
	east.add("North",tfNickName);
	east.add("North",send);
	
	south.setLayout(new BorderLayout());
	south.add("Center",tf);
	south.add("East",send);
	scroll.setPreferredSize(new Dimension(140, 120));
	south.add("North",scroll);
	
	send.addActionListener(this);
	connect.addActionListener(this);
	quitterPartie.addActionListener(this);
	aide.addActionListener(this);
	tfNickName.addActionListener(this);
	
	colonne1.addActionListener(this);
	colonne2.addActionListener(this);
	colonne3.addActionListener(this);
	colonne4.addActionListener(this);
	colonne5.addActionListener(this);
	colonne6.addActionListener(this);
	colonne7.addActionListener(this);
	

	}

//-----------------------------------------------------------------------------------------------
	public void GriserColonnePleine(){
		
		if(monTableauColonnePleine[0]==1) colonne1.setEnabled(false);
		if(monTableauColonnePleine[1]==1) colonne2.setEnabled(false);
		if(monTableauColonnePleine[2]==1) colonne3.setEnabled(false);
		if(monTableauColonnePleine[3]==1) colonne4.setEnabled(false);
		if(monTableauColonnePleine[4]==1) colonne5.setEnabled(false);
		if(monTableauColonnePleine[5]==1) colonne6.setEnabled(false);
		if(monTableauColonnePleine[6]==1) colonne7.setEnabled(false);
		
	}
	
//-----------------------------------------------------------------------------------------------
	public void MasquerBouton(boolean b){
		colonne1.setEnabled(b);
		colonne2.setEnabled(b);
		colonne3.setEnabled(b);
		colonne4.setEnabled(b);
		colonne5.setEnabled(b);
		colonne6.setEnabled(b);
		colonne7.setEnabled(b);
		
		GriserColonnePleine();
	}
		
//-----------------------------------------------------------------------------------------------	
	public void CreerClient(){
				
					
			recept = new IHMEcriture(){
	
			public void ecrire(String message) {
					String FirstValue = ""+message.charAt(0);		
					switch(Integer.parseInt(FirstValue)){
					
					case 1:	String PlayerValue = ""+message.charAt(2);
							maGrille.Joueur = Integer.parseInt(PlayerValue)+1;
							if(maGrille.Joueur==1){
								isYourTurn = true;
								MasquerBouton(isYourTurn);
								textarea.append("C'est à vous de commencer !\n");
							}
							else {
								isYourTurn = false;
								MasquerBouton(isYourTurn);
								textarea.append("C'est à votre adversaire de commencer !\n");
							}
							textarea.setCaretPosition(textarea.getDocument().getLength());
							break;
						
					case 2:	textarea.append("\n"+message);
							textarea.setCaretPosition(textarea.getDocument().getLength());
							break;
					
					case 3: String ColumnValue = ""+message.charAt(2);
							String ColorValue = ""+message.charAt(4);
							monTableauColonnePleine[Integer.parseInt(ColumnValue)]=maGrille.AddPion(Integer.parseInt(ColumnValue), Integer.parseInt(ColorValue));
							isYourTurn = true;
							MasquerBouton(isYourTurn);
							break;
						
					case 4:	
					
					default:break;	
					}
					
					}
						
			};
				
			try {
					c = new Client(tfHost.getText(),Integer.parseInt(tfPort.getText()),tfNickName.getText());
					th=new NetIn(c.getCanalLecture(),recept);
				    th.start();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	}

	

//---------------------------GESTION EVENT------------------------------------------------	
	public void actionPerformed(ActionEvent e) {
		
	
		
		if(e.getSource()==quitterPartie){
			 int Ret;
				Ret = JOptionPane.showConfirmDialog(Alert, "Sure?", "Quit", JOptionPane.YES_NO_OPTION);
				if(Ret==0){
				
					System.exit(0);
				
				} else {}
		}
		
		if(e.getSource()==aide){

            JOptionPane.showMessageDialog(null, "Qui ne sait pas jouer au Puissance 4?!!");
		}

		
	//---------------------------------connect---------------------------------	
	if(e.getSource()==connect){
	
		
		textarea.setText("Vous etes desormais connecte sur le chan !\n");
		CreerClient();

	
	}
	
	//---------------------------------envoi-------------------------------------
	if (e.getSource()==send) {
		
		if(tf.getText().equals("quit")){
			c.getCanalEcriture().println("quit");
		}else{
		c.getCanalEcriture().println("2_"+tfNickName.getText() + " dit : " + tf.getText());
		
		tf.setText(null);
		}
		
	}
	
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne1){
		
		monTableauColonnePleine[0]=maGrille.AddPion(0, maGrille.Joueur);
		c.getCanalEcriture().println("3_0_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);
	}
	
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne2){
			
		monTableauColonnePleine[1]=maGrille.AddPion(1, maGrille.Joueur);
		c.getCanalEcriture().println("3_1_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);	
	}	
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne3){
			
		monTableauColonnePleine[2]=maGrille.AddPion(2, maGrille.Joueur);
		c.getCanalEcriture().println("3_2_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);	
		}
		
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne4){
			
		monTableauColonnePleine[3]=maGrille.AddPion(3, maGrille.Joueur);
		c.getCanalEcriture().println("3_3_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);	
		}
		
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne5){
			
		monTableauColonnePleine[4]=maGrille.AddPion(4, maGrille.Joueur);
		c.getCanalEcriture().println("3_4_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);
		}		
	
//-------------------------------------------------------------------------------------------
	if(e.getSource()==colonne6){
			
		monTableauColonnePleine[5]=maGrille.AddPion(5, maGrille.Joueur);
		c.getCanalEcriture().println("3_5_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);	
		}
	
//-------------------------------------------------------------------------------------------
if(e.getSource()==colonne7){
			
		monTableauColonnePleine[6]=maGrille.AddPion(6, maGrille.Joueur);
		c.getCanalEcriture().println("3_6_"+maGrille.Joueur);
		isYourTurn = false;
		MasquerBouton(isYourTurn);	
		}
	}

}

