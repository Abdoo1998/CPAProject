package GUI;

import application.Duration;
import application.Task;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;

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
    private TimeTextField durationField;
    private Task task;

    private static final String TASK_STRING = "Task name";
    private static final String DURATION_STRING = "Duration";

    /*Default fields*/
    protected static final int DEFAULT_COLUMNSIZE = 15;
    protected static final int DEFAULT_WIDTH = 650;
    protected static final int DEFAULT_HEIGHT = 400;
    protected static final Color DEFAULT_COLOR = new Color(20, 140, 5);
    protected static final Insets DEFAULT_INSETS = new Insets(0, 20, 20, 20);
    protected static final Insets TOP_DEFAULT_INSETS = new Insets(20, 20, 20, 20);


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

    public Duration getDuration() {
        return durationField.getDuration();
    }

    public CPATextField getTaskNameField() {
        return taskNameField;
    }

    public TimeTextField getDurationField() {
        return durationField;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void showGUI() {
        pack();
        setVisible(true);
    }

    private void setTextField(int textFieldColumnWidth) {

        this.taskNameField = new CPATextField(textFieldColumnWidth);
        taskNameField.setPreferredSize(new Dimension(150, 25));
        taskNameField.setActionCommand(TASK_STRING);
        taskNameField.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //copy, paste and cut action support
        taskNameField.supportControlAction(KeyEvent.VK_C, new DefaultEditorKit.CopyAction());
        taskNameField.supportControlAction(KeyEvent.VK_V, new DefaultEditorKit.PasteAction());
        taskNameField.supportControlAction(KeyEvent.VK_X, new DefaultEditorKit.CutAction());

    }

    private void setDurationSpinner() {
        this.durationField = new TimeTextField("h", "m");

    }

    private void setLabels() {

        this.taskNameLabel = new JLabel(TASK_STRING + ":");
        this.durationLabel = new JLabel(DURATION_STRING + ":");

        taskNameLabel.setLabelFor(taskNameField);
        durationLabel.setLabelFor(durationField);

        taskNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    private void setCustomLayout() {

        getContentPane().setLayout(new GridBagLayout());

        //Constraints for taskNameLabel
        GridBagConstraints taskNameLabelConstraints = new GridBagConstraints();
        taskNameLabelConstraints.gridx = 0;
        taskNameLabelConstraints.gridy = 0;
        taskNameLabelConstraints.fill = GridBagConstraints.NONE;
        taskNameLabelConstraints.insets = TOP_DEFAULT_INSETS;
        getContentPane().add(taskNameLabel, taskNameLabelConstraints);

        //Constraints for taskNameField
        GridBagConstraints taskNameFieldConstraints = new GridBagConstraints();
        taskNameFieldConstraints.gridx = 1;
        taskNameFieldConstraints.gridy = 0;
        taskNameFieldConstraints.fill = GridBagConstraints.NONE;
        taskNameFieldConstraints.insets = TOP_DEFAULT_INSETS;
        getContentPane().add(taskNameField, taskNameFieldConstraints);


        //Constraints for durationLabel
        GridBagConstraints durationLabelConstraints = new GridBagConstraints();
        durationLabelConstraints.gridx = 0;
        durationLabelConstraints.gridy = 1;
        durationLabelConstraints.fill = GridBagConstraints.NONE;
        durationLabelConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(durationLabel, durationLabelConstraints);


        //Constraints for durationField
        GridBagConstraints durationFieldConstraints = new GridBagConstraints();
        durationFieldConstraints.gridx = 1;
        durationFieldConstraints.gridy = 1;
        durationFieldConstraints.fill = GridBagConstraints.NONE;
        durationFieldConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(durationField, durationFieldConstraints);

    }

}
