package chess;

/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * 
 * Pawn extends Piece and has functions to initialize and check move validity,
 * 	and its unique attack validity
 */

public class Pawn extends Piece{
	
	/**
	 * @param t  allows us to define a pawn of a certain color
	 */
	
	public Pawn(boolean t) {
		super.white = t;
	}
	
	/**
	 * Here we check to see if a pawn is perfroming a legal attack, which
	 *  is forward diagnol (up for white and down for black)
	 * 
	 * @param str is formatted x1y1_x2y2
	 * @return
	 */
	
	public boolean ifAttack(String str) {
		
		int x1 = Character.getNumericValue(str.charAt(0));
		int y1 = Character.getNumericValue(str.charAt(1));
		int x2 = Character.getNumericValue(str.charAt(3));
		int y2 = Character.getNumericValue(str.charAt(4));
		if(super.white) {
			if(y2==y1-1 && (x2==x1+1 || x2==x1-1)) {
				return true;
			}
		}
		if(!super.white) {
			if(y2==y1+1 && (x2==x1+1 || x2==x1-1)) {
				return true;
			}
		}
	
		return false;
	}
	
	
	/**
	 * 
	 * legalMove checks the validity of a move in terms of a pieces defined movement,
	 *  in this case checking a forward move of one or two spots depending on if it moved already
	 *  
	 *  note that we check the validity of the move in other ways too, this method ONLY covers movement
	 * 
	 */
	public boolean legalMove(String str) {
		
		int x1 = Character.getNumericValue(str.charAt(0));
		int y1 = Character.getNumericValue(str.charAt(1));
		int x2 = Character.getNumericValue(str.charAt(3));
		int y2 = Character.getNumericValue(str.charAt(4));
		if(x1==x2) {
			
		
			if(super.white) {
				if(y1==y2+2 && !hasMoved) {
					return true;
				
				}
				if(y1==y2+1) {
					return true;
				}
			}if(!super.white) {
				if(y1==y2-2 && !hasMoved) {
					return true;
				
				}
				if(y1==y2-1) {
					return true;
				}
			}
		}
		return false;
	}
}