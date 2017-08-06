package GUI;

import application.Duration;
import application.Time;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Class representing a JPanel that contains two formatted text fields that only accept
 * numbers and two labels associated to them
 *
 * @author gorosgobe
 */
public class TimeTextField extends JPanel {

    /** Formatted text field that will hold the hours inputted by the user*/
    private JFormattedTextField hours;
    /** label for the hours field*/
    private JLabel hourSymbol;
    /** Formatted text field that will hold the minutes inputted by the user*/
    private JFormattedTextField minutes;
    /** label for the minutes field*/
    private JLabel minuteSymbol;

    /**
     * Constructs a TimeTextField object
     * @param label1 first label for the first formatted text field
     * @param label2 second label for the second formatted text field
     */
    public TimeTextField(String label1, String label2) {
        //sets the number formatter
        NumberFormat format = NumberFormat.getInstance();
        TimeNumberFormatter formatter = new TimeNumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);

        //sets the formatted text field for hours
        this.hours = new JFormattedTextField(formatter);
        hours.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        hours.setPreferredSize(new Dimension(50, 25));
        hours.setHorizontalAlignment(JFormattedTextField.RIGHT);
        hours.setValue(0);

        //sets the hour label
        this.hourSymbol = new JLabel(label1);
        hourSymbol.setLabelFor(hours);
        hourSymbol.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //sets the formatted text field for minutes
        this.minutes = new JFormattedTextField(formatter);
        minutes.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        minutes.setPreferredSize(new Dimension(50, 25));
        minutes.setHorizontalAlignment(JFormattedTextField.RIGHT);
        minutes.setValue(0);

        //sets the minute label
        this.minuteSymbol = new JLabel(label2);
        minuteSymbol.setLabelFor(minutes);
        minuteSymbol.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //sets the layout for the time text field
        setTimetextFieldConstraints();

    }

    /**
     * Gets the time in the TimeTextField
     * @return the time in the TimeTextField if all fields are completed, else returns null
     */
    public Time getTime() {

        if (hours.getText().equals("") || minutes.getText().equals("")) {
            return null;
        }

        int numHours = Integer.parseInt(hours.getText());
        int numMinutes = Integer.parseInt(minutes.getText());

        if (numHours < 0 || numHours > 23) {
            numHours = 0;
        }

        if (numMinutes < 0 || numMinutes > 59) {
            numMinutes = 0;
        }

        return new Time(numHours, numMinutes);
    }

    /**
     * Gets the duration in the TimeTextField
     * @return the duration in the TimeTextField if all fields are completed, else returns null
     */
    public Duration getDuration() {

        if (hours.getText().equals("") || minutes.getText().equals("")) {
            return null;
        }

        int numHours = Integer.parseInt(hours.getText());
        int numMinutes = Integer.parseInt(minutes.getText());

        if (numHours < 0 || numHours > 23) {
            numHours = 0;
        }

        if (numMinutes < 0 || numMinutes > 59) {
            numMinutes = 0;
        }

        return new Duration(numHours, numMinutes);
    }

    /**
     * Sets the GridBagLayout for the component. For more information on GridBagLayout and
     * GridBagConstraints, go to the Java tutorials.
     */
    private void setTimetextFieldConstraints() {

        this.setLayout(new GridBagLayout());

        //constraints for hours field
        GridBagConstraints hourConstraints = new GridBagConstraints();
        hourConstraints.gridx = 0;
        hourConstraints.gridy = 0;
        hourConstraints.fill = GridBagConstraints.NONE;
        hourConstraints.insets = new Insets(0, 5, 0, 0);
        add(hours, hourConstraints);

        //constraints for hour label
        GridBagConstraints hourSymbolConstraints = new GridBagConstraints();
        hourSymbolConstraints.gridx = 1;
        hourSymbolConstraints.gridy = 0;
        hourSymbolConstraints.fill = GridBagConstraints.NONE;
        hourSymbolConstraints.insets = new Insets(0, 5, 0, 5);
        add(hourSymbol, hourSymbolConstraints);

        //constraints for minutes field
        GridBagConstraints minuteConstraints = new GridBagConstraints();
        minuteConstraints.gridx = 2;
        minuteConstraints.gridy = 0;
        minuteConstraints.fill = GridBagConstraints.NONE;
        minuteConstraints.insets = new Insets(0, 5, 0, 5);
        add(minutes, minuteConstraints);

        //constraints for minute label
        GridBagConstraints minuteSymbolConstraints = new GridBagConstraints();
        minuteSymbolConstraints.gridx = 3;
        minuteSymbolConstraints.gridy = 0;
        minuteSymbolConstraints.fill = GridBagConstraints.NONE;
        minuteSymbolConstraints.insets = new Insets(0, 0, 0, 5);
        add(minuteSymbol, minuteSymbolConstraints);
    }
}
