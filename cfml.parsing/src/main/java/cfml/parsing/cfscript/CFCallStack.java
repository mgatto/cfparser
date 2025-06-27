package cfml.parsing.cfscript;

import java.util.Stack;

public class CFCallStack extends Stack<CFCall> {
	
	private static final long serialVersionUID = 1L;
	
	public CFScopeStack localScope() {
		return peek().scopeStack();
	}
	
}
