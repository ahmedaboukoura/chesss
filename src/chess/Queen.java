package chess;

/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * Queen extends Piece and has functions to initialize and check move validity
 */

public class Queen extends Piece {

	/**
	 * @param t  allows us to define a Queen of a certain color
	 */
	
	public Queen(boolean t) {
		super.white = t;
	}
	/**
	 * 
	 * legalMove checks the validity of a move in terms of a pieces defined movement,
	 *  in this case checking diagnol movement by comparing changes in x and y
	 *  		OR it can go vertical or horizontal like the rook
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
		int dX = Math.abs(x2-x1);
		int dY = Math.abs(y2-y1);
		if (dX == dY) {
			return true;
		}
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
}