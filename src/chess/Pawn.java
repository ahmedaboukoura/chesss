package chess;

public class Pawn extends Piece{
	
	public Pawn(boolean t) {
		super.white = t;
	}
	
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