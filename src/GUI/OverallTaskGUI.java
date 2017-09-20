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

    /** A reference to the application GUI*/
    private CPAProjectApplicationGUI applicationReference;
    /** Label representing the starting time of the OverallTask*/
    private JLabel startTimeLabel;
    /** Custom text field for time representing the to-be-inputted starting time*/
    private TimeTextField startTimeField;
    /** Label representing the description label*/
    private JLabel descriptionLabel;
    /** Text area representing the description of the OverallTask*/
    private JTextArea description;
    /** Button representing the create button that will generate the OverallTask*/
    private JButton button;

    //String constants
    /** Starting time string*/
    static final String START_TIME = "Start time";
    /** Button string*/
    private static final String BUTTON_STRING = "Create";
    /** Title of the OverallTaskGUI frame*/
    private static final String FRAME_TITLE = "Create New Task";
    /** Description string*/
    static final String DESCRIPTION_LABEL = "Description";

    /**
     * OverallTaskGUI constructor. Sets the title to the FRAME_TITLE static constant.
     * Initialises the starting time field, label and the create button, and lays out these
     * components in the GUI.
     */
    public OverallTaskGUI(CPAProjectApplicationGUI applicationReference) {
        super();
        this.applicationReference = applicationReference;
        setTitle(FRAME_TITLE);
        setStartTimeField();
        setStartTimeLabel();
        setDescription();
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

    public String getDescription() {
        return description.getText();
    }

    /**
     * Initialises the start time field with the corresponding labels.
     */
    private void setStartTimeField() {
        //Constructor takes the text for the labels in between the number fields
        this.startTimeField = new TimeTextField("h", "m");
    }

    /**
     * Initialises the starting time label
     */
    private void setStartTimeLabel() {
        this.startTimeLabel = new JLabel(START_TIME + ":");
        //for accessibility reasons
        startTimeLabel.setLabelFor(startTimeField);
        startTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Initialises the description label and the description text area.
     */
    private void setDescription() {
        this.descriptionLabel = new JLabel(DESCRIPTION_LABEL + ": ");
        descriptionLabel.setLabelFor(description);
        descriptionLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //creates JTextArea with expected width 10 and height 20, just used so it is displayed properly (ignore numbers)
        this.description = new JTextArea(10, 20);
        description.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //disables one single line text area
        description.setLineWrap(true);
        //sets the area to wrap around words and not characters
        description.setWrapStyleWord(true);
        description.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
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
        startTimeLabelConstraints.weightx = 0.5;
        startTimeLabelConstraints.weighty = 0.5;
        startTimeLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        startTimeLabelConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(startTimeLabel, startTimeLabelConstraints);

        //constraints for start time field
        GridBagConstraints startTimeFieldConstraints = new GridBagConstraints();
        startTimeFieldConstraints.gridx = 1;
        startTimeFieldConstraints.gridy = 2;
        startTimeFieldConstraints.weightx = 0.5;
        startTimeFieldConstraints.weighty = 0.5;
        startTimeFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        startTimeFieldConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(startTimeField, startTimeFieldConstraints);

        //constraints for description label
        GridBagConstraints descriptionLabelConstraints = new GridBagConstraints();
        descriptionLabelConstraints.gridx = 0;
        descriptionLabelConstraints.gridy = 3;
        descriptionLabelConstraints.weightx = 0.5;
        descriptionLabelConstraints.weighty = 0.5;
        descriptionLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        descriptionLabelConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(descriptionLabel, descriptionLabelConstraints);

        //constraints for description
        GridBagConstraints descriptionConstraints = new GridBagConstraints();
        descriptionConstraints.gridx = 0;
        descriptionConstraints.gridy = 4;
        descriptionConstraints.gridwidth = 2;
        descriptionConstraints.gridheight = 2;
        descriptionConstraints.weightx = 0.5;
        descriptionConstraints.weighty = 0.5;
        descriptionConstraints.fill = GridBagConstraints.HORIZONTAL;
        descriptionConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(description, descriptionConstraints);

        //constraints for button
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 6;
        buttonConstraints.weightx = 0.5;
        buttonConstraints.weighty = 0.5;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
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
        String descriptionString = getDescription();
        //error checking
        if (taskName.equals("") || duration == null || startingTime == null) {
            //dont do anything, description is optional
            return;
        }
        applicationReference.addOverallTask(new OverallTask(taskName, duration, startingTime, descriptionString));
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
