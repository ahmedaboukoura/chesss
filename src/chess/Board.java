package chess;

/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 */

import java.util.*;
public class Board {
	
	Box[][] game = new Box[8][8];
	boolean brokeCast;
	boolean unPromoted;
	
	
	/**
	 * 
	 * Create() initializes our board with all of the starting pieces in the right places
	 * 
	 */
	
	public void create() {
		for(int i=0;i<8;i++) {
			for(int j=0; j<8; j++) {
				game[i][j]=new Box();
			}	
		}
		
		game[0][0].cPiece = new Rook(false);
		game[0][7].cPiece = new Rook(false);
		game[0][6].cPiece = new Knight(false);
		game[0][1].cPiece = new Knight(false);
		game[0][2].cPiece = new Bishop(false);
		game[0][5].cPiece = new Bishop(false);
		game[0][3].cPiece = new Queen(false);
		game[0][4].cPiece = new King(false);
		
		game[7][0].cPiece = new Rook(true);
		game[7][7].cPiece = new Rook(true);
		game[7][6].cPiece = new Knight(true);
		game[7][1].cPiece = new Knight(true);
		game[7][2].cPiece = new Bishop(true);
		game[7][5].cPiece = new Bishop(true);
		game[7][3].cPiece = new Queen(true);
		game[7][4].cPiece = new King(true);
		
		
		for(int i=0; i<8; i++) {
			for(int j =0; j<8; j++) {
				if(i==1) {
					game[i][j].cPiece = new Pawn(false);
				}
				if(i==6) {
					game[i][j].cPiece = new Pawn(true);
				}
				
				game[i][j].x=j;
				game[i][j].y=i;
				
			}
			
		}
		
	}
	
	/**
	 * move() takes the inputed string encrypted as x1y1_x2y2 and then moves a piece on the board
	 * @param str
	 */
	
	public void move(String str) {
		
		brokeCast=false;
		unPromoted=false;
		int y1 = Character.getNumericValue(str.charAt(1));
		int x1 = Character.getNumericValue(str.charAt(0));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		
		Piece oPiece=game[y1][x1].cPiece;
		Piece nPiece=game[y2][x2].cPiece;
		
		if(oPiece==null) {
			return;
		}
		if(oPiece instanceof King) {
			
			if(nPiece==null && (Math.abs(x1-x2)==1 || Math.abs(x1-x2)==0) && (Math.abs(y1-y2)==1 || Math.abs(y1-y2)==0)) {
				if(oPiece.legalMove(str)) {
					oPiece.hasMoved = true;
					game[y2][x2].cPiece=oPiece;
					game[y1][x1].cPiece = null;
					return;
				}
				return;
			}
			if(((King) oPiece).castle(str)) {
				if(x2==6) {
					String temp = Integer.toString(y1);
					if(game[y1][x2+1].cPiece instanceof Rook) {
						if(game[y1][x2+1].cPiece.white==game[y1][x1].cPiece.white) {
							if(game[y1][x2+1].cPiece.hasMoved==false) {
								if(checkCheck("4"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(checkCheck("5"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(checkCheck("6"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(!ifBlocked("4" + temp + " " + "7"+ temp) && !brokeCast) {
									
									oPiece.hasMoved = true;
									game[y1][x2+1].cPiece.hasMoved=true;
									game[y2][x2].cPiece=oPiece;
									game[y1][x1].cPiece = null;
									game[y1][x1+1].cPiece = game[y1][x2+1].cPiece;
									game[y1][x2+1].cPiece = null;
									return;
								}
							}
						}
					}
				}if(x2==2) {
					String temp = Integer.toString(y1);
					if(game[y1][x2-2].cPiece instanceof Rook) {
						if(game[y1][x2-2].cPiece.white==game[y1][x1].cPiece.white) {
							if(game[y1][x2-2].cPiece.hasMoved==false) {
								if(checkCheck("4"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(checkCheck("3"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(checkCheck("2"+temp, game[y1][4].cPiece.white)) {
									brokeCast=true;
								}
								if(!ifBlocked("4" + temp + " " + "0"+ temp) && !brokeCast) {
									
									oPiece.hasMoved = true;
									game[y1][x2-2].cPiece.hasMoved=true;
									game[y2][x2].cPiece=oPiece;
									game[y1][x1].cPiece = null;
									game[y1][x1-1].cPiece = game[y1][x2-2].cPiece;
									game[y1][x2-2].cPiece = null;
									return;
								}
								
							}
						}
					}
				}
			}
		}
		else if(oPiece instanceof Pawn) {
			
				if(oPiece.legalMove(str)) {
					oPiece.hasMoved = true;
					game[y2][x2].cPiece=oPiece;
					game[y1][x1].cPiece = null;
					if(y1-y2==2) {
						game[y1-1][x2].yer=1;
					}
					if(y1-y2==-2) {
						game[y1+1][x2].yer=-1;
					}
					if(game[y2][x2].cPiece.white && y2==0) {
						unPromoted=true;
					}else if(game[y2][x2].cPiece.white && y2==7) {
						unPromoted=true;
					}
					return;
				}if(((Pawn) oPiece).ifAttack(str)) {
					if(game[y2][x2].yer==-1 && game[y2][x2].cPiece==null) {
						oPiece.hasMoved = true;
						game[y2][x2].cPiece=oPiece;
						game[y1][x1].cPiece = null;
						game[y2+1][x2].cPiece=null;
						game[y2][x2].yer=5;
						return;
					}
					if(game[y2][x2].yer==1 && game[y2][x2].cPiece==null) {
						oPiece.hasMoved = true;
						game[y2][x2].cPiece=oPiece;
						game[y1][x1].cPiece = null;
						game[y2-1][x2].cPiece=null;
						game[y2][x2].yer=5;
						return;
					}
					if(nPiece.white!=oPiece.white) {
						oPiece.hasMoved = true;
						game[y2][x2].cPiece=oPiece;
						game[y1][x1].cPiece = null;
						return;
						
					}
					if(nPiece.white==oPiece.white) {
						return;
					}
				}
				return;
			
		}else if(oPiece.legalMove(str)) {
			if(nPiece==null) {
				oPiece.hasMoved = true;
				game[y2][x2].cPiece=oPiece;
				game[y1][x1].cPiece = null;
				return;
			}
			if(nPiece.white==oPiece.white) {
				return;
			}
			if(nPiece.white!=oPiece.white) {
				oPiece.hasMoved = true;
				game[y2][x2].cPiece=oPiece;
				game[y1][x1].cPiece = null;
				return;
			}
		}
		
		
	}
	/**
	 * Checks that the path is clear and that no pieces are blocking the move
	 * 
	 * @param str - formatted x1y1_x2y2
	 * @return returns True if there are piece in between and false if not
	 */
	public boolean ifBlocked(String str) {
		int y1 = Character.getNumericValue(str.charAt(1));
		int x1 = Character.getNumericValue(str.charAt(0));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		int deltaY, deltaX;
		boolean seenPiece=false;
			if (x1 == x2 && y1 != y2) {
				if (y1 > y2) { 
					for (deltaY = y1-1; deltaY > y2; deltaY--) {
						if (game[deltaY][x1].cPiece != null) {
							seenPiece=true;
						}
					}
				}
				if (y1 < y2) //movement up
					for (deltaY = y1+1; deltaY < y2; deltaY++) {
						if (game[deltaY][x1].cPiece != null) {
							seenPiece=true;
						}
					}
			}
			
			
			// if it's horizontal movement
			if (x1 != x2 && y1 == y2) {
				if (x1 < x2) { //movement right
					for (deltaX = x1+1; deltaX < x2; deltaX++) {
						if (game[y1][deltaX].cPiece != null) {
							seenPiece=true;
						}
					}
				}if (x1 > x2) { //movement left
					for (deltaX = x1-1; deltaX > x2; deltaX--) {
						if (game[y1][deltaX].cPiece != null) {
							seenPiece=true;
						}
					}
				}
			}
			
			// diagonal movement
			int xDif = x2 - x1;
			int yDif = y2 - y1;
			if (Math.abs(xDif) == Math.abs(yDif)) {
				// up right
				if (yDif > 0 && xDif > 0) {
					deltaX = x1+1;
					for (deltaY = y1+1; deltaY < y2; deltaY++) {	
						if (game[deltaY][deltaX].cPiece != null) {
							seenPiece=true;
						}
						deltaX++;
					}
				}
				// up left
				if (yDif > 0 && xDif < 0) {
					deltaX = x1-1;
					for (deltaY = y1+1; deltaY < y2; deltaY++) {
						//System.out.println(deltaX+" " + deltaY);
						
						if (game[deltaY][deltaX].cPiece != null) {
							seenPiece=true;
						}
						deltaX--;
					}
				}
				// down right
				if (yDif < 0 && xDif > 0) {
					deltaX = x1+1;
					for (deltaY = y1-1; deltaY > y2; deltaY--) {
					
						if (game[deltaY][deltaX].cPiece != null) {
							seenPiece=true;
						}
						deltaX++;
					}
				}
				// down left 
				if (yDif < 0 && xDif < 0) {
					deltaX= x1-1;
					for (deltaY = y1-1; deltaY > y2; deltaY--) {
						//System.out.println(deltaX+" " +deltaY);
						if (game[deltaY][deltaX].cPiece != null) {
							seenPiece=true;
						}
						deltaX--;
					}
				}
			}
			
			return seenPiece;
		}

	/**
	 * finds the king and returns its position as a formatted string
	 * 
	 * @param whiteKing- boolean for being white or black
	 * @return
	 */
	
	public String findKing(boolean whiteKing) {
		for(int i=0; i<8; i++) {
			for(int j =0; j<8; j++) {
				if(game[i][j].cPiece instanceof King && game[i][j].cPiece.white==whiteKing) {
					String myKing = "" + j + ""+ i;
					return myKing;
				}
			}
			
		}
		return "Didn't find it";
	}
	
	/**
	 * Checks for checkmate by going to every spot the king can move to, and the spot that its in
	 * if it is check at every spot, then the king is in checkmate
	 *  but we need to be very careful about bounds here
	 * 
	 * @param kingL - where the king is
	 * @param whiteKing - white or black boolean
	 * @return
	 */
	
	public boolean checkMate(String kingL, boolean whiteKing) {
		int y = Character.getNumericValue(kingL.charAt(1));
		int x = Character.getNumericValue(kingL.charAt(0));
		int xMinus=x-1;
		boolean hasAMove=false;
		int xPlus=x+1;
		int yMinus=y-1;
		int yPlus= y+1;
		if(xPlus<8 && yPlus<8) {
				String temp = Integer.toString(xPlus)+Integer.toString(yPlus);
			//one=(Integer.toString(x) + Integer.toString(y) + " " + Integer.toString(xPlus) + Integer.toString(yPlus));
				if(!(checkCheck(temp, whiteKing)) && (game[yPlus][xPlus].cPiece==null || game[yPlus][xPlus].cPiece.white!=game[y][x].cPiece.white)) {
					//System.out.println("1");
					hasAMove=true;
				}
		}if(xPlus<8) {
			String temp = Integer.toString(xPlus)+Integer.toString(y);
			if(!(checkCheck(temp, whiteKing)) && (game[y][xPlus].cPiece==null || game[y][xPlus].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("2");
				hasAMove=true;
			}
		}
		if(xPlus<8 && yMinus>=0) {
			String temp = Integer.toString(xPlus)+Integer.toString(yMinus);
			if(!(checkCheck(temp, whiteKing)) && (game[yMinus][xPlus].cPiece==null || game[yMinus][xPlus].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("3");
				hasAMove=true;
			}
		}
		if(yMinus>=0) {
			String temp = Integer.toString(x)+Integer.toString(yMinus);
			if(!(checkCheck(temp, whiteKing)) && (game[yMinus][x].cPiece==null || game[yMinus][x].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("4");
				hasAMove=true;
			}
		}
		if(xMinus>=0 && yMinus>=0) {
			String temp = Integer.toString(xMinus)+Integer.toString(yMinus);
			if(!(checkCheck(temp, whiteKing)) && (game[yMinus][xMinus].cPiece==null || game[yMinus][xMinus].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("5");
				hasAMove=true;
			}
		}
		if(xMinus>=0) {
			String temp = Integer.toString(xMinus)+Integer.toString(y);
			if(!(checkCheck(temp, whiteKing)) && (game[y][xMinus].cPiece==null || game[y][xMinus].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("6");
				hasAMove=true;
			}
		}
		if(xMinus>=0 && yPlus<8) {
			String temp = Integer.toString(xMinus)+Integer.toString(yPlus);
			if(!(checkCheck(temp, whiteKing)) && (game[yPlus][xMinus].cPiece==null || game[yPlus][xMinus].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("7");
				hasAMove=true;
			}
		}
		if(yPlus<8) {
			String temp = Integer.toString(x)+Integer.toString(yPlus);
			if(!(checkCheck(temp, whiteKing)) && (game[yPlus][x].cPiece==null || game[yPlus][x].cPiece.white!=game[y][x].cPiece.white)) {
				//System.out.println("8");
				hasAMove=true;
			}
		}
		if(hasAMove==true) {
			//System.out.println("hasAMove");
			return false;
		}else {
		return true;
		}
		
	}
	
	
	
	/**
	 * 
	 * Checks if a king is in check by any piece in a possibly attacking are
	 *  checks up/downs and diagnols  plus possible knight spots
	 *  
	 * @param kingL - current king position
	 * @param whiteKing - white or black boolean
	 * @return
	 */
	
	
	
	public boolean checkCheck(String kingL, boolean whiteKing) {
		int y = Character.getNumericValue(kingL.charAt(1));
		int x = Character.getNumericValue(kingL.charAt(0));
		//System.out.println (kingL);
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				String xt = Integer.toString(j);
				String yt = Integer.toString(i);
				if(game[i][j].cPiece==null) {
					continue;
				}
				if(game[i][j].cPiece.legalMove(xt+yt+" " +x+y) && whiteKing!=game[i][j].cPiece.white) {
					if(game[i][j].cPiece instanceof Pawn) {
						continue;
					}else if(game[i][j].cPiece instanceof Knight) {
						return true;
					}else {
						if(!ifBlocked(xt+yt+" "+x+y)) {
							return true;
						}
					}
				}if(game[i][j].cPiece instanceof Pawn) {
					if(((Pawn) game[i][j].cPiece).ifAttack(xt+yt+" "+x+y)) {
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	/**
	 * @return a string that prints as a display of the board
	 */
	
	public String printBoard() {
		String str ="";
		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				str+=game[i][j].toString();
				str+=" ";
				if(j==7) {
					str+= 8-i+"\n";
				}
			}
		}
		str+=" a  b  c  d  e  f  g  h\n";
		return str;
	}
}