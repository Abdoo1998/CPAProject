package GUI;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Extension of NumberFormatter that allows the empty string as a valid number
 *
 * @author gorosgobe
 */
public class TimeNumberFormatter extends NumberFormatter {

    /**
     * Constructor for TimeNumberFormatter, simply calls super() with the format required
     * @param numberFormat the number format that NumberFormatter will accept
     */
    public TimeNumberFormatter(NumberFormat numberFormat) {
        super(numberFormat);
    }

    /**
     * Overrides the stringToValue method in NumberFormatter to return null when the supplied string to the
     * formatter is empty. This allows the user to delete numbers in the number fields used in the TimeTextField
     * component.
     * @param s string to parse
     * @return the value given by the string parameter
     * @throws ParseException
     */
    @Override
    public Object stringToValue(String s) throws ParseException {
        if (s.equals("")) {
            return null;
        }

        return super.stringToValue(s);
    }
}
