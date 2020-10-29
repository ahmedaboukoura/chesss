package chess;
/**
 * 
 * @author Sean Murphy
 *
 * Each box on our board needs to hold some values for us, so we store the x and y value
 *  along with the piece a value called yer which we use to determine if the piece moved
 */
public class Box {

	
	int x;
	int yer;
	int y;
	Piece cPiece;

	/**
	 * Checks to see if a board pieces is black or white when it is an empty square
	 * 
	 * @return
	 */
	
	public boolean isBlack() {
		if(y%2==0) {
			if(x%2==1) {
				return true;
			}
		}else if(y%2==1) {
			if(x%2==0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Takes Pieces and turns them into Strings to output when printing the board
	 */
	
	public String toString() {
		if(cPiece instanceof Rook) {
			if(cPiece.white) {
				return "wR";
			}else if(!cPiece.white) {
				return "bR";
			}
		}
		if(cPiece instanceof Pawn) {
			if(cPiece.white) {
				return "wP";
			}else if(!cPiece.white) {
				return "bP";
			}
		}
		if(cPiece instanceof Queen) {
			if(cPiece.white) {
				return "wQ";
			}else if(!cPiece.white) {
				return "bQ";
			}
		}
		if(cPiece instanceof Knight) {
			if(cPiece.white) {
				return "wN";
			}else if(!cPiece.white) {
				return "bN";
			}
		}
		if(cPiece instanceof Bishop) {
			if(cPiece.white) {
				return "wB";
			}else if(!cPiece.white) {
				return "bB";
			}
		}
		if(cPiece instanceof King) {
			if(cPiece.white) {
				return "wK";
			}else if(!cPiece.white) {
				return "bK";
			}
		}
		if(this.cPiece==null) {
			if(this.isBlack()==true) {
				return "##";
			}if(this.isBlack()==false) {
				return "  ";
			}
		}
		return "er";
	}
	
}
