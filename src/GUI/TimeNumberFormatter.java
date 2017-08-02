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

    public TimeNumberFormatter() {
        super();
    }

    public TimeNumberFormatter(NumberFormat numberFormat) {
        super(numberFormat);
    }

    @Override
    public Object stringToValue(String s) throws ParseException {
        if (s.equals("")) {
            return null;
        }

        return super.stringToValue(s);
    }
}
