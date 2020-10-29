package chess;

import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * 
 * @author Sean Murphy
 * @author Ahmed 
 *
 *	Our Chess game uses a Board and Pieces to implement the popular game
 * White being first, the main method prompts players on their turn after
 *  displaying a visual of the gameboard
 *  We then encrypt the string to interpretable data,
 *  	determine if the move is acceptable,
 *  		and then makes the move or asks for another if it is not valid
 *  	This happens until one player puts another in "checkMate"
 *  	which is a function we implement in the Board class
 *  
 *  	Each piece has its own class in which its legal movement is defined,
 *  		and in some cases special scenarios like promotion and castling are dealt with accordingly
 *
 */
 
public class Chess {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board game = new Board();
		boolean drawC=false;
		game.create();
		boolean special=false;
		boolean ifE=false;
		boolean whiteTurn = true;
		Scanner sc = new Scanner(System.in);
		
		while(!ifE) {
			special=false;
			System.out.println(game.printBoard());
			if(whiteTurn) {
				String kingLoc = game.findKing(true);
				
				if(game.checkCheck(kingLoc, true)) {
					if(game.checkMate(kingLoc, true)) {
						System.out.println();
						System.out.println("Checkmate");
						System.out.println("Black Wins\n");
						break;
					}
				}
				System.out.print("White's Turn: ");
				
				if (drawC) {
					System.out.println("Draw? (Enter 'draw' to end game as a tie)");
				}
				
				String str = sc.nextLine();
				String tempStr = encrypt(str);
				String[] extra = tempStr.split(" ");
				
				if(drawC) {
					if(str.equals("draw")) {
						System.out.println();
						System.out.println("Draw\n");
						break;
					}else {
						drawC=false;
					}
				}else {
					if(str.equals("draw")) {
						System.out.println();
						System.out.println("Illegal Move, try again\n");
						continue;
					}
				}
				
				if(tempStr.equals("Invalid string")) {
					System.out.println();
					System.out.println("Illegal Move, try again\n");
					continue;
				}if(tempStr.equals("resign")) {
					System.out.println();
					System.out.println("Black Wins\n");
					break;
				}
				
				int y1 = Character.getNumericValue(tempStr.charAt(1));
				int x1 = Character.getNumericValue(tempStr.charAt(0));
				int y2 = Character.getNumericValue(tempStr.charAt(4));
				int x2 = Character.getNumericValue(tempStr.charAt(3));
				
				if( game.game[y1][x1].cPiece==null || whiteTurn!=game.game[y1][x1].cPiece.white) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				if(x1==x2 && y1==y2) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				if(game.game[y1][x1].cPiece instanceof Knight) {
					game.move(tempStr);
					
				}
				else if(!game.ifBlocked(tempStr)) {
					game.move(tempStr);
					
				}else if(game.ifBlocked(tempStr)) {
					System.out.println();
					System.out.println("Illegal Move, try again\n");
					continue;
				}else {
					System.out.println();
					System.out.println("Illegal Move, try again\n");
					continue;
				}
				System.out.println();
				kingLoc = game.findKing(false);
				if(game.unPromoted) {
					try{
						if(extra[2].equals("N")) {
							game.game[y2][x2].cPiece= new Knight(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}if(extra[2].equals("B")) {
							game.game[y2][x2].cPiece= new Bishop(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("R")) {
							game.game[y2][x2].cPiece= new Rook(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("Q")) {
							game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("draw?")) {
							game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
							drawC=true;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
					}
					
					
				}else {
					try {
						if(extra[2].equals("draw?")) {
							
							drawC=true;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						
					}
				}
				
				if(game.checkCheck(kingLoc, false)) {
					if(game.checkMate(kingLoc, false)) {
						System.out.println("Checkmate");
						System.out.println("White Wins\n");
						break;
					}
					System.out.println("Check\n");
				}
				if(game.game[y1][x1].cPiece!=null) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				whiteTurn=false;
			}else if(!whiteTurn) {
				special=false;
				String kingLoc = game.findKing(true);
				if(game.checkCheck(kingLoc, true)) {
					if(game.checkMate(kingLoc, true)) {
						System.out.println();
						System.out.println("Checkmate");
						System.out.println("White Wins\n");
						break;
					}
				}
				System.out.print("Black's Turn: ");
				

				if (drawC) {
					System.out.println("Draw? (Enter 'draw' to end game as a tie)");
				}
				
				String str = sc.nextLine();
				String tempStr = encrypt(str);
				String[] extra = tempStr.split(" ");
				
				
				if(drawC) {
					if(str.equals("draw")) {
						System.out.println();
						System.out.println("Draw\n");
						break;
					}else {
						drawC=false;
					}
				}
				if(str.equals("Invalid string")) {
					System.out.println();
					System.out.println("Illegal Move, try again\n");
					continue;
				}
				
				if(tempStr.equals("resign")) {
					System.out.println();
					System.out.println("White Wins\n");
					break;
				}
				
					
				
				int y1 = Character.getNumericValue(tempStr.charAt(1));
				int x1 = Character.getNumericValue(tempStr.charAt(0));
				int y2 = Character.getNumericValue(tempStr.charAt(4));
				int x2 = Character.getNumericValue(tempStr.charAt(3));
				
				if( game.game[y1][x1].cPiece==null || whiteTurn!=game.game[y1][x1].cPiece.white) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				if(x1==x2 && y1==y2) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				if(game.game[y1][x1].cPiece instanceof Knight) {
					game.move(tempStr);
					
				}else if(!game.ifBlocked(tempStr)) {
					game.move(tempStr);
					//tity
				}else if(game.ifBlocked(tempStr)) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}else {
					System.out.println();
				System.out.println("Illegal move, try again\n");
				}
				
				
				System.out.println();
				kingLoc = game.findKing(true);
				if(game.unPromoted) {
					try{
						if(extra[2].equals("N")) {
							game.game[y2][x2].cPiece= new Knight(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}if(extra[2].equals("B")) {
							game.game[y2][x2].cPiece= new Bishop(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("R")) {
							game.game[y2][x2].cPiece= new Rook(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("Q")) {
							game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
							try {
								if(extra[3].equals("draw?")) {
									
									drawC=true;
								}
							}catch(ArrayIndexOutOfBoundsException e){
								
							}
						}
						if(extra[2].equals("draw?")) {
							game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
							drawC=true;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						game.game[y2][x2].cPiece= new Queen(game.game[y2][x2].cPiece.white);
					}
					
					
				}else {
					try {
						if(extra[2].equals("draw?")) {
							
							drawC=true;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						
					}
				}
				
				if(game.checkCheck(kingLoc, true)) {
					if(game.checkMate(kingLoc, true)) {
						System.out.println("Checkmate");
						System.out.println("Black Wins\n");
						break;
					}
					System.out.println("Check\n");
				}
				
				
				if(game.game[y1][x1].cPiece!=null) {
					System.out.println();
					System.out.println("Illegal move, try again\n");
					continue;
				}
				whiteTurn=true;
			}
			
		}
	
	}
	
	
	/**
	 * Encrypt receives the string as a form like "e7 e5"
	 *  and then returns a string in the form like "46 44" 
	 *     where the return string is formated like x1y1_x2y2
	 *
	 * 
	 * @param str0 is the original string received 
	 * @return a string formatted with the first char being the x index and second being the y index
	 */
	
	public static String encrypt(String str0) {
		StringTokenizer stk = new StringTokenizer(str0); 
		boolean special=false;
		String token = "";
		String str="";
		if(str0.equals("resign")) {
			return "resign";
		}
		if(str0.equals("draw?")) {
			return "draw";
		}
		for(int i=0; stk.hasMoreTokens(); i++) {
			token=stk.nextToken();
			if(i<2 ) {
				if(token.length()!=2 && !str0.equals("draw")) {
					return "Invalid string";
				}
				switch (token.charAt(0)) {
		            case 'a': str+= '0';
		                     break;
		            case 'b':  str+= '1';
		                     break;
		            case 'c':  str+= '2';
		                     break;
		            case 'd':  str+= '3';
		                     break;
		            case 'e':  str+= '4';
		                     break;
		            case 'f':  str+= '5';
		                     break;
		            case 'g':  str+= '6';
		                     break;
		            case 'h':  str+= '7';
	                	break;
		            default: return "Invalid string";           
				}
				if(token.charAt(1)=='9' || token.charAt(1)=='0') {
					return "Invalid string";
				}
				if(Character.isDigit(token.charAt(1))) {
					
					if(Character.getNumericValue(token.charAt(1))<9 ) {
						str+=(8-Character.getNumericValue(token.charAt(1)));
						str+= " ";
					}
				}
				
			}
			
			if(i==2) {
				if(token.equals("N")) {
					special=true;
					str+="N";
				}
				else if(token.equals("B")) {
					special=true;
					str+="B";
				}
				else if(token.equals("Q")) {
					special=true;
					str+="Q";
				}
				else if(token.equals("R")) {
					special=true;
					str+="R";
				}
				else if(token.equals("draw?")) {
					str+= "draw?";
				}else if(!token.equals("")){
					return "Invalid string";
				}
			}
			
		}
		//System.out.println(str);
		return str;
	}

}