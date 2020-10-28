package chess;



public class Bishop extends Piece{


	public Bishop(boolean t) {
		super.white = t;
	}
	
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