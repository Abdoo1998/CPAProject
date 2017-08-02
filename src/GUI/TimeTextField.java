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

    private JFormattedTextField hours;
    private JLabel hourSymbol;
    private JFormattedTextField minutes;
    private JLabel minuteSymbol;

    public TimeTextField(String label1, String label2) {

        NumberFormat format = NumberFormat.getInstance();
        TimeNumberFormatter formatter = new TimeNumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);

        this.hours = new JFormattedTextField(formatter);
        hours.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        hours.setPreferredSize(new Dimension(50, 25));
        hours.setHorizontalAlignment(JFormattedTextField.RIGHT);
        hours.setValue(0);

        this.hourSymbol = new JLabel(label1);
        hourSymbol.setLabelFor(hours);
        hourSymbol.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.minutes = new JFormattedTextField(formatter);
        minutes.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        minutes.setPreferredSize(new Dimension(50, 25));
        minutes.setHorizontalAlignment(JFormattedTextField.RIGHT);
        minutes.setValue(0);

        this.minuteSymbol = new JLabel(label2);
        minuteSymbol.setLabelFor(minutes);
        minuteSymbol.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        setTimetextFieldConstraints();

    }


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

    private void setTimetextFieldConstraints() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints hourConstraints = new GridBagConstraints();
        hourConstraints.gridx = 0;
        hourConstraints.gridy = 0;
        hourConstraints.fill = GridBagConstraints.NONE;
        hourConstraints.insets = new Insets(0, 5, 0, 0);
        add(hours, hourConstraints);

        GridBagConstraints hourSymbolConstraints = new GridBagConstraints();
        hourSymbolConstraints.gridx = 1;
        hourSymbolConstraints.gridy = 0;
        hourSymbolConstraints.fill = GridBagConstraints.NONE;
        hourSymbolConstraints.insets = new Insets(0, 5, 0, 5);
        add(hourSymbol, hourSymbolConstraints);

        GridBagConstraints minuteConstraints = new GridBagConstraints();
        minuteConstraints.gridx = 2;
        minuteConstraints.gridy = 0;
        minuteConstraints.fill = GridBagConstraints.NONE;
        minuteConstraints.insets = new Insets(0, 5, 0, 5);
        add(minutes, minuteConstraints);

        GridBagConstraints minuteSymbolConstraints = new GridBagConstraints();
        minuteSymbolConstraints.gridx = 3;
        minuteSymbolConstraints.gridy = 0;
        minuteSymbolConstraints.fill = GridBagConstraints.NONE;
        minuteSymbolConstraints.insets = new Insets(0, 0, 0, 5);
        add(minuteSymbol, minuteSymbolConstraints);
    }
}
