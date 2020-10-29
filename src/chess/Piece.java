package chess;

/**
 * @author Sean Murphy
 * @author Ahmed Aboukoura
 * 
 */


public abstract class Piece {
	
	boolean white;
	boolean hasMoved= false;
	
	public abstract boolean legalMove(String str);
	
	
}