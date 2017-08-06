package GUI;

import application.Duration;
import application.OverallTask;
import application.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * GUI representing an application.OverallTask
 *
 * @author gorosgobe
 */
public class OverallTaskGUI extends TaskGUI implements ActionListener {

    /** Label representing the starting time of the OverallTask*/
    private JLabel startTimeLabel;
    /** Custom text field for time representing the to-be-inputted starting time*/
    private TimeTextField startTimeField;
    /** Button representing the create button that will generate the OverallTask*/
    private JButton button;

    //String constants
    /** Starting time string*/
    private static final String START_TIME = "Start time";
    /** Button string*/
    private static final String BUTTON_STRING = "Create";
    /** Title of the OverallTaskGUI frame*/
    private static final String FRAME_TITLE = "Create New Task";

    /**
     * OverallTaskGUI constructor. Sets the title to the FRAME_TITLE static constant.
     * Initialises the starting time field, label and the create button, and lays out these
     * components in the GUI.
     */
    public OverallTaskGUI() {
        super();
        setTitle(FRAME_TITLE);
        setStartTimeField();
        setStartTimeLabel();
        setButton();
        setOverallTaskLayout();
    }

    /**
     * Gets the starting Time in the start time field (custom time field).
     * @return the starting time (Time object)
     */
    public Time getStartTime() {
        return startTimeField.getTime();
    }

    /**
     * Initialises the start time field with the corresponding labels.
     */
    private void setStartTimeField() {
        //Constructor takes the text for the labels in between the number fields
        this.startTimeField = new TimeTextField("h", "m");
    }

    /**
     * Initialises the startint time label
     */
    private void setStartTimeLabel() {
        this.startTimeLabel = new JLabel(START_TIME + ":");
        //for accessibility reasons
        startTimeLabel.setLabelFor(startTimeField);
        startTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Initialises the create button, adds the frame as its action listener
     * and sets it as the default button
     */
    private void setButton() {

        this.button = new JButton(BUTTON_STRING);
        button.setActionCommand(BUTTON_STRING);
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds the frame as an action listener to the button
        button.addActionListener(this);
        //sets button to be default when pressed enter
        getRootPane().setDefaultButton(button);

    }

    /**
     * Lays out every component in the OverallTaskGUI. GridBagLayout is used, see Java tutorials
     * for extra documentation on GridBagConstraints.
     */
    private void setOverallTaskLayout() {

        //constraints for start time label
        GridBagConstraints startTimeLabelConstraints = new GridBagConstraints();
        startTimeLabelConstraints.gridx = 0;
        startTimeLabelConstraints.gridy = 2;
        startTimeLabelConstraints.fill = GridBagConstraints.NONE;
        startTimeLabelConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(startTimeLabel, startTimeLabelConstraints);

        //constraints for start time field
        GridBagConstraints startTimeFieldConstraints = new GridBagConstraints();
        startTimeFieldConstraints.gridx = 1;
        startTimeFieldConstraints.gridy = 2;
        startTimeFieldConstraints.fill = GridBagConstraints.NONE;
        startTimeFieldConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(startTimeField, startTimeFieldConstraints);

        //constraints for button
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 3;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(button, buttonConstraints);

    }

    /**
     * Called by the library itself when button is pressed, does nothing if all fields
     * haven't been completed, else creates a new OverallTask
     * @param actionEvent the event generated
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //only action performed is the pressing of the button
        String taskName = getTaskNameText();
        Duration duration = getDuration();
        Time startingTime = getStartTime();
        //error checking
        if (taskName.equals("") || duration == null || startingTime == null) {
            //dont do anything
            return;
        }
        setTask(new OverallTask(taskName, duration, startingTime));
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
