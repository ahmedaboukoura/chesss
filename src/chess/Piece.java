package chess;

public abstract class Piece {
	
	boolean white;
	boolean hasMoved= false;
	
	public abstract boolean legalMove(String str);
	
	
}