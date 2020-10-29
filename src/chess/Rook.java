package chess;

/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * Rook extends Piece and has functions to initialize and check move validity,
 * 	including special cases like castling
 */

public class Rook extends Piece {
	
	/**
	 * @param t  allows us to define a Rook of a certain color
	 */
	
	public Rook(boolean t) {
		super.white = t;
	}
	/**
	 * 
	 * legalMove checks the validity of a move in terms of a pieces defined movement,
	 *  in this case checking vertical and horizontal movement by comparing changes in x and y
	 *  
	 *  note that we check the validity of the move in other ways too, this method ONLY covers movement
	 * 
	 */

	@Override
	public boolean legalMove(String str) {
		
		int y1 = Character.getNumericValue(str.charAt(1));
		int x1 = Character.getNumericValue(str.charAt(0));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		if(x1==x2) {
			if(y2!=y1) {
				return true;
			}
		}
		if(y1==y2) {
			if(x2!=x1) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Here we check to see if it is performing a legal castle
	 * 
	 * @param str is formatted x1y1_x2y2
	 * @return
	 */
	
	
	public boolean legalCastle(String str) {
		if(super.hasMoved==false) {
		return true;
		}else {
			return false;
		}
	}
}