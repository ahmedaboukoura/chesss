package chess;
/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 * King extends Piece and has functions to initialize and check move validity,
 * 		including special cases like castling
 */

public class King extends Piece{
	
	/**
	 * initializes a new kings of either white or black
	 * @param t
	 */
	
	public King(boolean t) {
		super.white = t;
	}
	/**
	 * 
	 * Checks to see if the kings move is valid,
	 * 	in this case by checking the surrounding spots
	 * 	
	 * note that this is ONLY for checking movement legality, not validity or the bounds
	 */
	@Override
	public boolean legalMove(String str) {
		
		int y1 = Character.getNumericValue(str.charAt(1));
		int x1 = Character.getNumericValue(str.charAt(0));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		//System.out.println(str);
		
		
		if(y1==y2-1 || y1==y2+1 || y1==y2) {
			if(x1==x2-1 || x1==x2+1 || x1==x2){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks for possible castles, can only happen if neither piece has moved
	 * @param str
	 * @return
	 */
	
	public boolean castle(String str) {
	
		int y1 = Character.getNumericValue(str.charAt(1));
		int y2 = Character.getNumericValue(str.charAt(4));
		int x2 = Character.getNumericValue(str.charAt(3));
		if(hasMoved==false && (x2==6 || x2==2) && y1==y2) {
			hasMoved=true;
			return true;
		}
		return false;
	}
	
}