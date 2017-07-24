package GUI;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Represents the GUI component for a new Task
 * @author gorosgobe
 */
public class CPANewTaskGUI extends JFrame {

    private CPATextField taskNameField;
    private CPATextField startingTimeField;
    private CPATextField durationField;
    private static final String TASK_STRING = "Task name";
    private static final String STARTING_TIME_STRING = "Start time";
    private static final String DURATION_STRING = "Duration";

    private static final int DEFAULT_COLUMNSIZE = 20;
    private static final String INITIAL_MESSAGE = "Input task name, start time and duration";
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final Color DEFAULT_COLOR = new Color(20, 140, 5);

    /*
    JLabel status provides a message for the user telling them to fill in the fields.
    In case they don't fill in one of them, it reminds them that they must fill all the fields.
     */
    private JLabel status;

      /*
        textFieldColumnWidth is an int representing the number of columns in the textfield,
        required for layout purposes. Can be substituted with a private static final variable.
      */

    public CPANewTaskGUI() {
        this(DEFAULT_COLUMNSIZE);
    }

    public CPANewTaskGUI(int textFieldColumnWidth) {

        super();
        //initialises text fields and column width
        setTextFields(textFieldColumnWidth);
        //set resizability
        setResizable(false);

        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(DEFAULT_COLOR);

        // Sets a gridbag layout for the new Task and adds components
        setCustomGridBagLayout();
        //adds copy, cut and paste functionality to text fields
        createActions();
    }

    private void setCustomGridBagLayout() {

        getContentPane().setLayout(new GridBagLayout());

        // constraints for task name text field
        GridBagConstraints taskNameConstraints = new GridBagConstraints();
        taskNameConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskNameConstraints.gridx = 1;
        taskNameConstraints.gridy = 1;
        //adds task name text field
        getContentPane().add(getTaskNameField(), taskNameConstraints);

        //constraints for starting time text field
        GridBagConstraints startingTimeConstraints = new GridBagConstraints();
        startingTimeConstraints.fill = GridBagConstraints.HORIZONTAL;
        startingTimeConstraints.gridx = 1;
        startingTimeConstraints.gridy = 2;
        //adds starting time text field
        getContentPane().add(getStartingTimeField(), startingTimeConstraints);

        //constraints for duration time text field
        GridBagConstraints durationConstraints = new GridBagConstraints();
        durationConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationConstraints.gridx = 1;
        durationConstraints.gridy = 3;
        //adds duration time text field
        getContentPane().add(getDurationField(), durationConstraints);

        //constraints for status label
        GridBagConstraints statusConstraints = new GridBagConstraints();
        statusConstraints.fill = GridBagConstraints.NONE;
        statusConstraints.gridx = 1;
        statusConstraints.gridy = 4;
        //adds status label
        getContentPane().add(getStatus(), statusConstraints);


    }

    private void setTextFields(int textFieldColumnWidth) {

        this.taskNameField = new CPATextField(textFieldColumnWidth);
        taskNameField.setPreferredSize(new Dimension(150, 20));
        taskNameField.setActionCommand(TASK_STRING);
        //sets label for task name field
        JLabel taskLabel = new JLabel(TASK_STRING + ":");
        taskLabel.setLabelFor(taskNameField);
        taskLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.startingTimeField = new CPATextField(textFieldColumnWidth);
        startingTimeField.setActionCommand(STARTING_TIME_STRING);
        startingTimeField.setActionCommand(STARTING_TIME_STRING);
        //sets label for starting time field
        JLabel startingTimeLabel = new JLabel(STARTING_TIME_STRING + ":");
        startingTimeLabel.setLabelFor(startingTimeField);
        startingTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.durationField = new CPATextField(textFieldColumnWidth);
        durationField.setActionCommand(DURATION_STRING);
        durationField.setActionCommand(DURATION_STRING);
        //sets label for duration field
        JLabel durationLabel = new JLabel(DURATION_STRING + ":");
        durationLabel.setLabelFor(durationField);
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.status = new JLabel(INITIAL_MESSAGE, SwingConstants.CENTER);
        status.setFont(FontCollection.DEFAULT_FONT_BOLD);
    }

    private void createActions() {

      //copy operations
      createActionForTextFields(KeyEvent.VK_C, new DefaultEditorKit.CopyAction());

      //cut operations
      createActionForTextFields(KeyEvent.VK_X, new DefaultEditorKit.CutAction());

      //paste operations
      createActionForTextFields(KeyEvent.VK_V, new DefaultEditorKit.PasteAction());

    }

    private void createActionForTextFields(int keyInt, Action action) {
        taskNameField.supportControlAction(keyInt, action);
        startingTimeField.supportControlAction(keyInt, action);
        durationField.supportControlAction(keyInt, action);
    }

    public void createAndShowNewTaskGUI() {
        //pre: Frame has been initialised before in the constructor

        setTitle("New Task");
        //DISPOSE_ON_CLOSE so the whole application doesn't close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //sets up frame
        pack();
        setVisible(true);

    }

    public CPATextField getTaskNameField() {
        return taskNameField;
    }


    public CPATextField getStartingTimeField() {
        return startingTimeField;
    }

    public CPATextField getDurationField() {
        return durationField;
    }

    public JLabel getStatus() {
        return status;
    }

}
