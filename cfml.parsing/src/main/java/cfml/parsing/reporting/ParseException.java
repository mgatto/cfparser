package cfml.parsing.reporting;

import org.antlr.v4.runtime.Token;

public class ParseException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1;
	
	private Token token;
	
	public ParseException(Token _t, String _msg) {
		super(_msg);
		token = _t;
	}
	
	public int getLine() {
		return token.getLine();
	}
	
	public int getCol() {
		return token.getCharPositionInLine();
	}
	
}
