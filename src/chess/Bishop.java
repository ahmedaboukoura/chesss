package chess;
/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * 
 * Bishop extends Piece and has functions to initialize and check move validity
 */

public class Bishop extends Piece{

	/**
	 * @param t  allows us to define a bishop of a certain color
	 */

	public Bishop(boolean t) {
		super.white = t;
	}
	/**
	 * 
	 * legalMove checks the validity of a move in terms of a pieces defined movement,
	 *  in this case checking diagnol movement by comparing changes in x and y
	 *  
	 *  note that we check the validity of the move in other ways too, this method ONLY covers movement
	 * 
	 */
	@Override
	public boolean legalMove(String str) {
		int x1 = Character.getNumericValue(str.charAt(0));
		int y1 = Character.getNumericValue(str.charAt(1));
		int x2 = Character.getNumericValue(str.charAt(3));
		int y2 = Character.getNumericValue(str.charAt(4));
		int deltaX = Math.abs(x2-x1);
		int deltaY = Math.abs(y2-y1);
		if (deltaX == deltaY) {
			
			return true;
		}
		return false;
	}
}