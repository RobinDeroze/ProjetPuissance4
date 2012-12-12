import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Grille extends JPanel {
		
		int Xx=0;
		int Xy=0;
		int Yx=80;
		int Yy=80;
		int monTableau[][];
		int Joueur;
		
	public Grille() {
		monTableau = new int[7][7];
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++) monTableau[i][j]=0;
		}
	}
		
	public int IsTherePion(int X,int Y){
		
		
		System.out.println(""+X+"  "+Y);
		return monTableau[X][Y];
		
		
	}
	
	public int AddPion(int colonne, int nJoueur){
		
		int colonnePleine = 0;
		for(int i=5;i>=0;i--){
			if(monTableau[colonne][i]==0){
				monTableau[colonne][i]=nJoueur;
				if(i==0) colonnePleine = 1;
				break;
			}
		}
		
		repaint();
		return colonnePleine;
		
	}
	

	public void paint(Graphics g) {
		Xx=0;
		Xy=0;
		Color c = g.getColor();
		for(int j=0;j<6;j++){
		for(int i=0;i<7;i++){
		g.setColor(Color.blue);
		g.fillRect(Xx,Xy,Yx,Yy);
		switch(IsTherePion(Xx/80, Xy/80)){
		case 0:g.setColor(Color.gray);
			break;	
		case 1:g.setColor(Color.yellow);
			break;
		case 2:g.setColor(Color.red);
			break;
		default:break;	
		}
		
		g.fillOval(Xx,Xy,Yx,Yy);
		Xx+=80;
		
		}
		Xx=0;
		Xy+=80;
		}
		g.setColor(c);
		g.dispose();
	}
	
	

}