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

    private JLabel startTimeLabel;
    private TimeTextField startTimeField;
    private JButton button;

    private static final String START_TIME = "Start time";
    private static final String BUTTON_STRING = "Create";
    private static final String FRAME_TITLE = "Create New Task";

    public OverallTaskGUI() {
        super();
        setTitle(FRAME_TITLE);
        setStartTimeField();
        setStartTimeLabel();
        setButton();
        setOverallTaskLayout();
    }

    public Time getStartTime() {
        return startTimeField.getTime();
    }


    private void setStartTimeField() {
        this.startTimeField = new TimeTextField("h", "m");
    }

    private void setStartTimeLabel() {
        this.startTimeLabel = new JLabel(START_TIME + ":");
        startTimeLabel.setLabelFor(startTimeField);
        startTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }


    private void setButton() {

        this.button = new JButton(BUTTON_STRING);
        button.setActionCommand(BUTTON_STRING);
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds the frame as an action listener to the button
        button.addActionListener(this);
        //sets button to be default when pressed enter
        getRootPane().setDefaultButton(button);

    }

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
