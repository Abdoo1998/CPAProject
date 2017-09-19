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

    //Common fields used for all TaskGUIs
    /** Label for the name of the task*/
    private JLabel taskNameLabel;
    /** CPATextfield representing the field for the name of the task*/
    private CPATextField taskNameField;
    /** Label representing the later specified static duration string*/
    private JLabel durationLabel;
    /** Custom number field representing a time, specifically for the duration*/
    private TimeTextField durationField;
    /** Task to be created*/
    private Task task;

    //Strings used in TASKGUI
    /** Static string representing the label for the name of the task to be shown*/
    private static final String TASK_STRING = "Task name";
    /** Static string representing label for the duration to be shown */
    private static final String DURATION_STRING = "Duration";

    /*Default fields*/
    /** Constant representing the default column size required by text fields*/
    private static final int DEFAULT_COLUMNSIZE = 15;
    /** Constant representing the default margins used with GridBagConstraints*/
    protected static final Insets DEFAULT_INSETS = new Insets(0, 10, 20, 10);
    /** Constant representing the default margins used for components located at the top with GridBagConstraints*/
    protected static final Insets TOP_DEFAULT_INSETS = new Insets(20, 10, 20, 10);

    /**
     * Constructor for the TaskGUI object. Sets the default close operation to
     * close only the TaskGUI, not the whole application. Initialises the text field, duration
     * custom time text field, labels, positions everything in a GridBag Layout and blocks
     * resizability of the component.
     */
    public TaskGUI() {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTextField(DEFAULT_COLUMNSIZE);
        setDurationTimeTextField();
        setLabels();
        setCustomLayout();
        setResizable(false);
    }

    /**
     * Gets the text within the task name field
     * @return the string with the text of the task name field
     */
    public String getTaskNameText() {
        return taskNameField.getText();
    }

    /**
     * Gets the duration object from the duration field
     * @return the duration in the duration field
     */
    public Duration getDuration() {
        return durationField.getDuration();
    }

    /**
     * Gets the CPATextField object representing the task name
     * @return task name CPATextField object
     */
    public CPATextField getTaskNameField() {
        return taskNameField;
    }

    /**
     * Gets the TimeTextField representing the duration
     * @return duration TimeTextField object
     */
    public TimeTextField getDurationField() {
        return durationField;
    }

    /**
     * Gets the task field
     * @return the task held by the GUI
     */
    public Task getTask() {
        return task;
    }

    /**
     * Sets the task within the GUI component
     * @param task the task to set the GUI's task field to
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Method to show every TaskGUI. Shows the GUI in the center of the application frame
     */
    public void showGUI() {
        pack();
        // shows GUI in center of application frame. Requires to be called after pack() and before setVisible(true)
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initialises the task name text field. Also, gives the text field copy, cut and paste capabilities
     * @param textFieldColumnWidth the column width required by TextField constructor for layout purposes
     */
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

    /**
     * Initialises the duration custom TimeTextField, and sets the labels required by it.
     */
    private void setDurationTimeTextField() {
        //"h" and "m" are the labels located in between both number fields that the TimeTextField contains
        this.durationField = new TimeTextField("h", "m");

    }

    /**
     * Initialises and sets the labels to the constant strings declared in the class.
     */
    private void setLabels() {

        this.taskNameLabel = new JLabel(TASK_STRING + ":");
        this.durationLabel = new JLabel(DURATION_STRING + ":");

        //for accessibility purposes
        taskNameLabel.setLabelFor(taskNameField);
        durationLabel.setLabelFor(durationField);

        taskNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
    }

    /**
     * Sets the custom GridBagLayout for the TaskGUIs. Classes extending this abstract class will have to
     * take into account gridx and gridy values used in this method. For more information on the
     * GridBagLayout and GridBagConstraints, see the Java tutorials.
     */
    private void setCustomLayout() {

        getContentPane().setLayout(new GridBagLayout());

        //Constraints for taskNameLabel
        GridBagConstraints taskNameLabelConstraints = new GridBagConstraints();
        taskNameLabelConstraints.gridx = 0;
        taskNameLabelConstraints.gridy = 0;
        taskNameLabelConstraints.weightx = 0.5;
        taskNameLabelConstraints.weighty = 0.5;
        taskNameLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskNameLabelConstraints.insets = TOP_DEFAULT_INSETS;
        getContentPane().add(taskNameLabel, taskNameLabelConstraints);

        //Constraints for taskNameField
        GridBagConstraints taskNameFieldConstraints = new GridBagConstraints();
        taskNameFieldConstraints.gridx = 1;
        taskNameFieldConstraints.gridy = 0;
        taskNameFieldConstraints.weightx = 0.5;
        taskNameFieldConstraints.weighty = 0.5;
        taskNameFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskNameFieldConstraints.insets = TOP_DEFAULT_INSETS;
        getContentPane().add(taskNameField, taskNameFieldConstraints);


        //Constraints for durationLabel
        GridBagConstraints durationLabelConstraints = new GridBagConstraints();
        durationLabelConstraints.gridx = 0;
        durationLabelConstraints.gridy = 1;
        durationLabelConstraints.weightx = 0.5;
        durationLabelConstraints.weighty = 0.5;
        durationLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationLabelConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(durationLabel, durationLabelConstraints);


        //Constraints for durationField
        GridBagConstraints durationFieldConstraints = new GridBagConstraints();
        durationFieldConstraints.gridx = 1;
        durationFieldConstraints.gridy = 1;
        durationFieldConstraints.weightx = 0.5;
        durationFieldConstraints.weighty = 0.5;
        durationFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationFieldConstraints.insets = DEFAULT_INSETS;
        getContentPane().add(durationField, durationFieldConstraints);

    }

}
