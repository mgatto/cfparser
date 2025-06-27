package cfml.parsing;

import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.StartTag;

public class ParserTag {
	
	private String fName;
	private int fStartTagBegin;
	private int fStartTagEnd;
	private int fEndTagBegin;
	private int fEndTagEnd;
	private int fBegin;
	private int fEnd;
	private Attributes fAttributes;
	
	/**
	 * Default constructor for ParserTag.
	 */
	public ParserTag() {
		// Auto-generated constructor stub
	}
	
	/**
	 * Constructs a ParserTag with specified attributes.
	 *
	 * @param name The name of the tag.
	 * @param startTagBegin The beginning position of the start tag.
	 * @param startTagEnd The ending position of the start tag.
	 * @param endTagBegin The beginning position of the end tag.
	 * @param endTagEnd The ending position of the end tag.
	 * @param attributes The attributes of the tag.
	 */
	public ParserTag(String name, int startTagBegin, int startTagEnd, int endTagBegin, int endTagEnd,
			Attributes attributes) {
		setName(name);
		setBegin(startTagBegin);
		setEnd(endTagEnd);
		setStartTagBegin(startTagBegin);
		setStartTagEnd(startTagEnd);
		setEndTagBegin(endTagBegin);
		setEndTagEnd(endTagEnd);
		setAttributes(attributes);
	}
	
	/**
	 * Constructs a ParserTag from a Jericho StartTag.
	 *
	 * @param tag The Jericho StartTag to initialize the ParserTag.
	 */
	public ParserTag(StartTag tag) {
		setName(tag.getName());
		setBegin(tag.getElement().getEnd());
		setEnd(tag.getElement().getBegin());
		setStartTagBegin(tag.getElement().getStartTag().getBegin());
		setStartTagEnd(tag.getElement().getStartTag().getEnd());
		if (tag.getElement().getEndTag() != null) {
			setEndTagBegin(tag.getElement().getEndTag().getBegin());
			setEndTagEnd(tag.getElement().getEndTag().getEnd());
		} else {
			setEndTagBegin(tag.getElement().getStartTag().getBegin());
			setEndTagEnd(tag.getElement().getStartTag().getEnd());
		}
		setAttributes(tag.getAttributes());
	}
	
	/**
	 * Constructs a ParserTag from a Jericho Tag.
	 *
	 * @param tag The Jericho Tag to initialize the ParserTag.
	 */
	public ParserTag(net.htmlparser.jericho.Tag tag) {
		setName(tag.getName());
		setBegin(tag.getElement().getEnd());
		setEnd(tag.getElement().getBegin());
		setStartTagBegin(tag.getElement().getStartTag().getBegin());
		setStartTagEnd(tag.getElement().getStartTag().getEnd());
		if (tag.getElement().getEndTag() != null) {
			setEndTagBegin(tag.getElement().getEndTag().getBegin());
			setEndTagEnd(tag.getElement().getEndTag().getEnd());
		} else {
			setEndTagBegin(tag.getElement().getStartTag().getBegin());
			setEndTagEnd(tag.getElement().getStartTag().getEnd());
		}
		setAttributes(tag.getElement().getAttributes());
	}
	
	/**
	 * Sets the name of the tag.
	 *
	 * @param fName The name to set.
	 */
	public void setName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Gets the name of the tag.
	 *
	 * @return The name of the tag.
	 */
	public String getName() {
		return fName;
	}
	
	/**
	 * Sets the beginning position of the tag.
	 *
	 * @param fStart The beginning position to set.
	 */
	public void setBegin(int fStart) {
		this.fBegin = fStart;
	}
	
	/**
	 * Gets the beginning position of the tag.
	 *
	 * @return The beginning position of the tag.
	 */
	public int getBegin() {
		return fBegin;
	}
	
	/**
	 * @param fEnd
	 *            the fEnd to set
	 */
	public void setEnd(int fEnd) {
		this.fEnd = fEnd;
	}
	
	/**
	 * @return the fEnd
	 */
	public int getEnd() {
		return fEnd;
	}
	
	/**
	 * @param fAttributes
	 *            the fAttributes to set
	 */
	public void setAttributes(Attributes fAttributes) {
		this.fAttributes = fAttributes;
	}
	
	/**
	 * @return the fAttributes
	 */
	public Attributes getAttributes() {
		return fAttributes;
	}
	
	/**
	 * @param fStartTagBegin
	 *            the fStartTagBegin to set
	 */
	public void setStartTagBegin(int fStartTagBegin) {
		this.fStartTagBegin = fStartTagBegin;
	}
	
	/**
	 * @return the fStartTagBegin
	 */
	public int getStartTagBegin() {
		return fStartTagBegin;
	}
	
	/**
	 * @param fStartTagEnd
	 *            the fStartTagEnd to set
	 */
	public void setStartTagEnd(int fStartTagEnd) {
		this.fStartTagEnd = fStartTagEnd;
	}
	
	/**
	 * @return the fStartTagEnd
	 */
	public int getStartTagEnd() {
		return fStartTagEnd;
	}
	
	/**
	 * @param fEndTagBegin
	 *            the fEndTagBegin to set
	 */
	public void setEndTagBegin(int fEndTagBegin) {
		this.fEndTagBegin = fEndTagBegin;
	}
	
	/**
	 * @return the fEndTagBegin
	 */
	public int getEndTagBegin() {
		return fEndTagBegin;
	}
	
	/**
	 * @param fEndTagEnd
	 *            the fEndTagEnd to set
	 */
	public void setEndTagEnd(int fEndTagEnd) {
		this.fEndTagEnd = fEndTagEnd;
	}
	
	/**
	 * @return the fEndTagEnd
	 */
	public int getEndTagEnd() {
		return fEndTagEnd;
	}
	
}
