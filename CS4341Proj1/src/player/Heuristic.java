package player;

import java.util.ArrayList;
import java.util.Collections;

import game.Board;
import game.Piece;

public class Heuristic {
	
	public int Evaluate(Board board) {
		
		if (board==null)
			return 0;
		
		ArrayList<Threat> threats = new ArrayList<Threat>();
		Piece[][] pieces = board.Pieces;
		boolean sign=true;
		int streak=0;
		int potentialStreak=0;
		boolean previousEmpty=false;
		
		int total=0; //final score to return
		
		
		int emptyX=-1;
		int emptyY=-1;
		
		//Check vertical threats
		for (int x=0;x<board.Width;x++) {
			
			if (pieces[x][0]==null) //empty column
				continue;
			streak=1;
			potentialStreak=0;
			sign=pieces[x][0].Mine;
			previousEmpty=false;
			for (int y=1;y<board.Height;y++)
			{
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
				} else {
					if (sign!=pieces[x][y].Mine) //broke the streak
					{
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
				}
			}
			
			if (previousEmpty || streak==1)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		
		//copy-paste, i know. it hurts.
		//Check horizontal threats
		boolean noPieceYet=true;
		for (int y=0;y<board.Height;y++) {
			streak=0;
			potentialStreak=0;
			noPieceYet=true;
			for (int x=0;x<board.Width;x++)
			{
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
				} else {
					if (noPieceYet) {
						sign=pieces[x][y].Mine;
						streak++;
					} else if (sign!=pieces[x][y].Mine) { //broke the streak
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
					noPieceYet=false;
				}
			}
			
			if ((previousEmpty || streak==1) && !noPieceYet)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		
		
		//Check diagonal threats on bottom half of board
		int y=0;
		for (int i=0;i<board.Width;i++) {
			
			
			streak=0;
			potentialStreak=0;
			noPieceYet=true;
			y=0;
			
			for (int x=i;x<board.Width;x++)  {
				if (y>=board.Height) //hit the end of this diagonal
					break;
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
					
				} else {
					if (noPieceYet) {
						sign=pieces[x][y].Mine;
						streak++;
					} else if (sign!=pieces[x][y].Mine) { //broke the streak
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
					noPieceYet=false;
				}
				
				y++; //move up too
			}
			
			if ((previousEmpty || streak==1) && !noPieceYet)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		
		//Check diagonal threats on top half of board
		int x=0;
		for (int i=0;i<board.Height;i++) {
			
			streak=0;
			potentialStreak=0;
			noPieceYet=true;
			x=0;
			
			for (y=i;y<board.Height;y++)  {
				if (x>=board.Width) //hit the end of this diagonal
					break;
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
				} else {
					if (noPieceYet) {
						sign=pieces[x][y].Mine;
						streak++;
					} else if (sign!=pieces[x][y].Mine) { //broke the streak
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
					noPieceYet=false;
				}
				
				x++; //move across, too
			}
			
			if ((previousEmpty || streak==1) && !noPieceYet)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		
		//downard diagonals
		//Check diagonal threats on bottom half of board
		for (int i=board.Width-1;i>=0;i--) {
			streak=0;
			potentialStreak=0;
			noPieceYet=true;
			y=0;
			
			for (x=i;x>=0;x--)  {
				if (y>=board.Height) //hit the end of this diagonal
					break;
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
				} else {
					if (noPieceYet) {
						sign=pieces[x][y].Mine;
						streak++;
					} else if (sign!=pieces[x][y].Mine) { //broke the streak
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
					noPieceYet=false;
				}
				
				y++; //move up too
			}
			
			if ((previousEmpty || streak==1) && !noPieceYet)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		//downard diagonals
		//Check diagonal threats on bottom half
		for (int i=0;i<board.Height;i++) {
			
			streak=0;
			potentialStreak=0;
			noPieceYet=true;
			x=board.Width-1;
			
			for (y=i;y<board.Height;y++)  {  
				if (x<0) //hit the end of this diagonal
					break;
				if (pieces[x][y]==null) { //empty space
					if (!previousEmpty) {
						emptyX=x;
						emptyY=y;
					}
					potentialStreak++;
					previousEmpty=true;
				} else {
					if (noPieceYet) {
						sign=pieces[x][y].Mine;
						streak++;
					} else if (sign!=pieces[x][y].Mine) { //broke the streak
						if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
							threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
						}
						streak=1;
						sign = pieces[x][y].Mine;
						if (!previousEmpty) //Decide to count empty spaces or not
							potentialStreak=0;
					} else {
						streak++;
					}
					previousEmpty=false;
					noPieceYet=false;
				}
				
				x--; //move across, too
			}
			
			if ((previousEmpty || streak==1) && !noPieceYet)
			{
				if (streak+potentialStreak>=board.NumberToWin) { //remember the threat
					threats.add(new Threat(emptyX, emptyY, streak, potentialStreak, sign)); //I can look at even/odd later
				}
			}
		}
		
		
		//analyze threat data
		Threat t;
		ArrayList<Threat> trash = new ArrayList<Threat>();
		boolean potentialWin=false;
		for (int i=0;i<threats.size();i++) {
			t = threats.get(i);
			if((!t.sign)&&(t.streak==board.NumberToWin-1)) { //opponent has n-1 in a row
				if (t.y>0) { //can the opponent play there and win
					if (pieces[t.x][t.y-1]!=null) 
						return Integer.MIN_VALUE; //lose condition!	
				} else {
					return Integer.MIN_VALUE;//lose condition!
				}
			} else if ((t.sign)&&(t.streak==board.NumberToWin)) {
				if (t.y>0) {
					if (pieces[t.x][t.y-1]!=null)
						potentialWin=true; //possible win, as long as we don't notice a lose
				}
			}
			//total++; just for testing
		}
		if (potentialWin)
			return Integer.MAX_VALUE;
		
		
		//remove useless threats from consideration
		Collections.sort(threats, Threat.getComparator());
		ArrayList<Threat> newThreats = threats;
		for (int i=(threats.size()-1);i>0;i--) { //if a threat is 
			t=threats.get(i);
			Threat t2 = threats.get(i-1);
			if (t.x==t2.x) {
				if (t.y>=t2.y) {
					if (t.sign!=t2.sign) {
						newThreats.remove(i);
						trash.add(t);
					}
				}
			}
		}
		threats=newThreats;
		
		//tally remaining relevant threats
		int val=0;
		for (int i=0;i<threats.size();i++) {
			t=threats.get(i);
			
			val=t.streak*100;
			val+=t.potentialStreak*10;
			val+=t.y;
			
			val*=(t.threat==board.PlayerNumber) ? 1.5 : 1; //bonus, it's in the preferred row
			
			if (!t.sign) { //opponent threat
				val*=-1;
				val--; //play defensively
			}
			
			total+=val;
		}
		
		return total;
	}
	
}
