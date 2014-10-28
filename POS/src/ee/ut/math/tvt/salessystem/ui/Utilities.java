package ee.ut.math.tvt.salessystem.ui;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.regex.Pattern;

public class Utilities {

	public static boolean isNumeric(String str) {
		if (str.isEmpty()) return false;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	private static final Pattern DOUBLE_PATTERN = Pattern
			.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
					+ "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
					+ "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
					+ "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

	public static boolean isFloat(String s) {
		return DOUBLE_PATTERN.matcher(s).matches();
	}
}
