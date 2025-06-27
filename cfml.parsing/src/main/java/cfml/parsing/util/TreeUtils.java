package cfml.parsing.util;

import java.util.Arrays;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import cfml.CFSCRIPTParser;

public class TreeUtils {
	
	/**
	 * Print the tree, splitting at appropriate points, instead of rendering a single long line.
	 *
	 * @param parseTree The parse tree to print.
	 * @param parser The parser used to retrieve rule names.
	 * @return A formatted string representation of the parse tree.
	 */
	public static String printTree(final ParseTree parseTree, final CFSCRIPTParser parser) {
		final TreePrinterListener listener = new TreePrinterListener(Arrays.asList(parser.getRuleNames()));
		ParseTreeWalker.DEFAULT.walk(listener, parseTree);
		return listener.toString();
	}
	
	public static String normalizeWhiteSpace(String input) {
		return input.replaceAll("\\s+", " ").replaceAll("([^\\w])\\s+", "$1");
	}
}
