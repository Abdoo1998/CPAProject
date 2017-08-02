package GUI;

import application.Time;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

/**
 * Abstract class representing the GUI common components for application.Task extending classes
 * such as application.OverallTask or application.SubTask
 *
 * @author gorosgobe
 */
public abstract class TaskGUI extends JFrame {

    private JLabel taskNameLabel;
    private CPATextField taskNameField;
    private JLabel durationLabel;
    private JSpinner durationField;

    private static final String TASK_STRING = "Task name";
    private static final String DURATION_STRING = "Duration";

    /*Default fields*/
    protected static final int DEFAULT_COLUMNSIZE = 20;
    protected static final int DEFAULT_WIDTH = 650;
    protected static final int DEFAULT_HEIGHT = 400;
    protected static final Color DEFAULT_COLOR = new Color(20, 140, 5);
    protected static final Insets DEFAULT_INSETS = new Insets(0, 10, 10, 10);
    protected static final Insets TOP_DEFAULT_INSETS = new Insets(10, 10, 10, 10);


    public TaskGUI() {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTextField(DEFAULT_COLUMNSIZE);
        setDurationSpinner();
        setLabels();
        setCustomLayout();
        setResizable(false);
    }

    public String getTaskNameText() {
        return taskNameField.getText();
    }

    public Time getDuration() {

        Date durationDate = (Date) durationField.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(durationDate);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        return new Time(hours, minutes);

    }

    public CPATextField getTaskNameField() {
        return taskNameField;
    }

    public JSpinner getDurationField() {
        return durationField;
    }

    public void showGUI() {
        pack();
        setVisible(true);
    }

    private void setTextField(int textFieldColumnWidth) {

        this.taskNameField = new CPATextField(textFieldColumnWidth);
        taskNameField.setPreferredSize(new Dimension(150, 20));
        taskNameField.setActionCommand(TASK_STRING);
        taskNameField.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //copy, paste and cut action support
        taskNameField.supportControlAction(KeyEvent.VK_C, new DefaultEditorKit.CopyAction());
        taskNameField.supportControlAction(KeyEvent.VK_V, new DefaultEditorKit.PasteAction());
        taskNameField.supportControlAction(KeyEvent.VK_X, new DefaultEditorKit.CutAction());

    }

    private void setDurationSpinner() {

        this.durationField = new JSpinner();

        //sets the calendar date to 00:00 on the epoch
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));

        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 1439); // number of minutes in a day - 1
        Date latestDate = calendar.getTime();

        //creates new Date Spinner model and set format to hh:mm
        SpinnerDateModel dateModel = new SpinnerDateModel(earliestDate, earliestDate, latestDate, Calendar.MINUTE);
        durationField.setModel(dateModel);
        durationField.setEditor(new JSpinner.DateEditor(durationField, "hh:mm"));

    }

    private void setLabels() {

        this.taskNameLabel = new JLabel(TASK_STRING + ":");
        this.durationLabel = new JLabel(DURATION_STRING + ":");

        taskNameLabel.setLabelFor(taskNameField);
        durationLabel.setLabelFor(durationField);
    }

    private void setCustomLayout() {

        getContentPane().setLayout(new GridBagLayout());

        //Constraints for taskNameLabel
        GridBagConstraints taskNameLabelConstraints = new GridBagConstraints();
        taskNameLabelConstraints.gridx = 0;
        taskNameLabelConstraints.gridy = 0;
        taskNameLabelConstraints.fill = GridBagConstraints.NONE;
        taskNameLabelConstraints.insets = TOP_DEFAULT_INSETS;

        //Constraints for taskNameField
        GridBagConstraints taskNameFieldConstraints = new GridBagConstraints();
        taskNameFieldConstraints.gridx = 1;
        taskNameFieldConstraints.gridy = 0;
        taskNameFieldConstraints.fill = GridBagConstraints.NONE;
        taskNameFieldConstraints.insets = TOP_DEFAULT_INSETS;

        //Constraints for durationLabel
        GridBagConstraints durationLabel = new GridBagConstraints();
        durationLabel.gridx = 0;
        durationLabel.gridy = 1;
        durationLabel.fill = GridBagConstraints.NONE;
        durationLabel.insets = DEFAULT_INSETS;

        //Constraints for durationField
        GridBagConstraints durationField = new GridBagConstraints();
        durationField.gridx = 1;
        durationField.gridy = 1;
        durationField.fill = GridBagConstraints.NONE;
        durationField.insets = DEFAULT_INSETS;

    }

}
