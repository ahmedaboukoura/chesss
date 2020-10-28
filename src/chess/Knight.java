package chess;

public class Knight extends Piece{
	
	Knight(boolean t) {
		super.white = t;
	}

	
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