package chess;

public class Rook extends Piece {
	
	public Rook(boolean t) {
		super.white = t;
	}
	

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
	
	public boolean legalCastle(String str) {
		if(super.hasMoved==false) {
		return true;
		}else {
			return false;
		}
	}
}