package chess;
/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * Knight extends Piece and has functions to initialize and check move validity
 */

public class Knight extends Piece{
	
	/**
	 * @param t  allows us to define a Knight of a certain color
	 */
	Knight(boolean t) {
		super.white = t;
	}

	/**
	 * 
	 * legalMove checks the validity of a move in terms of a pieces defined movement,
	 *  in this case checking offset of x and y which should be 1 and 2 or 2 and 1
	 *  
	 *  note that we check the validity of the move in other ways too, this method ONLY covers movement
	 * 
	 */
	
	public boolean legalMove(String str) {
		
		int y1 = Character.getNumericValue(str.charAt(1));
		int x1 = Character.getNumericValue(str.charAt(0));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		if(x1==x2+1 || x1==x2-1) {
			if(y1==y2+2 || y1==y2-2) {
				return true;
			}
		}
		if(x1==x2+2 || x1==x2-2) {
			if(y1==y2+1 || y1==y2-1) {
				return true;
			}
		}
		return false;
	}
	

}