package chess;

public class King extends Piece{
	
	public King(boolean t) {
		super.white = t;
	}
	
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