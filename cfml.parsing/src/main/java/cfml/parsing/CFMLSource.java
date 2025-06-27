package cfml.parsing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cfml.parsing.cfmentat.tag.CFMLTags;
import cfml.parsing.preferences.ParserPreferences;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Logger;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceFormatter;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.StartTagType;
import net.htmlparser.jericho.Tag;

/**
 * CFMLSource is a class that represents a source of CFML (ColdFusion Markup Language) content.
 * It provides methods to parse and manipulate CFML content, as well as to retrieve debugging information.
 */
public class CFMLSource implements Logger {
	
	private Source fSource;
	private List<String> messages = new ArrayList<String>();
	
	/**
	 * Constructs a CFMLSource from the given contents.
	 *
	 * @param contents The contents to initialize the source.
	 */
	public CFMLSource(String contents) {
		CFMLTags.register();
		if (contents != null && contents.contains("<!---")) {
			fSource = new Source(contents);
		} else {
			fSource = new Source(contents);
		}
		// fSource.ignoreWhenParsing(fSource.getAllElements(CFMLTags.CFML_CONTENT));
		// fSource.ignoreWhenParsing(fSource.getAllElements(CFMLTags.CFML_COMMENT));
		fSource.setLogger(this);
	}
	
	/**
	 * Constructs a CFMLSource from the given contents and parser preferences.
	 *
	 * @param contents The contents to initialize the source.
	 * @param prefs The parser preferences to use.
	 */
	public CFMLSource(String contents, ParserPreferences prefs) {
		CFMLTags.register(prefs);
		fSource = new Source(contents);
		// fSource.ignoreWhenParsing(fSource.getAllElements(CFMLTags.CFML_CONTENT));
		fSource.setLogger(this);
	}
	
	/**
	 * Constructs a CFMLSource from the given URL.
	 *
	 * @param url The URL to initialize the source.
	 * @throws IOException If an error occurs while reading the URL.
	 */
	public CFMLSource(URL url) throws IOException {
		CFMLTags.register();
		fSource = new Source(url);
		// fSource.ignoreWhenParsing(fSource.getAllElements(CFMLTags.CFML_CONTENT));
		fSource.setLogger(this);
	}
	
	/**
	 * Constructs a CFMLSource from the given URL and parser preferences.
	 *
	 * @param url The URL to initialize the source.
	 * @param prefs The parser preferences to use.
	 * @throws IOException If an error occurs while reading the URL.
	 */
	public CFMLSource(URL url, ParserPreferences prefs) throws IOException {
		CFMLTags.register(prefs);
		fSource = new Source(url);
		// fSource.ignoreWhenParsing(fSource.getAllElements(CFMLTags.CFML_CONTENT));
		fSource.setLogger(this);
	}
	
	/**
	 * Gets debugging information for the source.
	 *
	 * @return A string containing debugging information.
	 */
	public String getDebuggingInfo() {
		return fSource.getDebugInfo();
	}
	
	/**
	 * Gets all elements of the specified start tag type.
	 *
	 * @param startTagType The start tag type to filter elements by.
	 * @return A list of elements matching the start tag type.
	 */
	public List<Element> getAllElements(StartTagType startTagType) {
		return fSource.getAllElements(startTagType);
	}
	
	/**
	 * Gets all child elements of the source.
	 *
	 * @return A list of child elements.
	 */
	public List<Element> getChildElements() {
		return fSource.getChildElements();
	}
	
	/**
	 * Ignores the specified elements when parsing.
	 *
	 * @param allElements The list of elements to ignore.
	 */
	public void ignoreWhenParsing(List allElements) {
		fSource.ignoreWhenParsing(allElements);
	}
	
	/**
	 * Gets cache debugging information for the source.
	 *
	 * @return A string containing cache debugging information.
	 */
	public String getCacheDebugInfo() {
		return fSource.getCacheDebugInfo();
	}
	
	/**
	 * Gets all elements in the source.
	 *
	 * @return A list of all elements.
	 */
	public List<Element> getAllElements() {
		return fSource.getAllElements();
	}
	
	public SourceFormatter getSourceFormatter() {
		return fSource.getSourceFormatter();
	}
	
	public List<StartTag> getAllStartTags() {
		return fSource.getAllStartTags();
	}
	
	public OutputDocument getOutputDocument() {
		return new OutputDocument(fSource);
	}
	
	public int getRow(int begin) {
		return fSource.getRow(begin);
	}
	
	public List<StartTag> getAllCFMLTags() {
		// return fSource.getAllStartTags("cf");
		return getTagsByName("cf");
	}
	
	public ParserTag getTagAt(int i) {
		ParserTag parserTag = makeParserTag(fSource.getTagAt(i));
		return parserTag;
	}
	
	public List<StartTag> getTagsByName(String tagName) {
		List<StartTag> tags = new ArrayList<StartTag>();
		for (Iterator<?> i = getAllStartTags().iterator(); i.hasNext();) {
			StartTag tagStart = (StartTag) i.next();
			if (tagStart.getName().startsWith(tagName)) {
				tags.add(tagStart);
			}
		}
		return tags;
	}
	
	public ParserTag getEnclosingTag(int i) {
		Tag tag = fSource.getEnclosingTag(i);
		if (tag == null) {
			return null;
		}
		return makeParserTag(tag);
	}
	
	public ParserTag getNextTag(int i) {
		Tag tag = fSource.getNextTag(i);
		return makeParserTag(tag);
	}
	
	public ParserTag getPreviousTag(int i) {
		Tag encosingTag = fSource.getEnclosingTag(i);
		Tag parserTag = fSource.getPreviousTag(i);
		if (parserTag.getBegin() == encosingTag.getBegin())
			return makeParserTag(fSource.getPreviousTag(encosingTag.getBegin() - 1));
		return makeParserTag(fSource.getPreviousTag(i));
	}
	
	private ParserTag makeParserTag(net.htmlparser.jericho.Tag nextTag) {
		if (nextTag == null) {
			return null;
		}
		ParserTag newTag = new ParserTag(nextTag);
		return newTag;
	}
	
	@Override
	public void error(String message) {
		messages.add(message);
	}
	
	@Override
	public void warn(String message) {
		
	}
	
	@Override
	public void info(String message) {
		
	}
	
	@Override
	public void debug(String message) {
		
	}
	
	@Override
	public boolean isErrorEnabled() {
		return true;
	}
	
	@Override
	public boolean isWarnEnabled() {
		return true;
	}
	
	@Override
	public boolean isInfoEnabled() {
		return false;
	}
	
	@Override
	public boolean isDebugEnabled() {
		return false;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
