package chess;

public class Queen extends Piece {

	public Queen(boolean t) {
		super.white = t;
	}
	

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