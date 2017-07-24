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

    /* Text fields used in the GUI */
    private CPATextField taskNameField;
    private CPATextField startingTimeField;
    private CPATextField durationField;

    /* Strings used in the GUI*/
    private static final String TASK_STRING = "Task name";
    private static final String STARTING_TIME_STRING = "Start time";
    private static final String DURATION_STRING = "Duration";
    private static final String NEW_TASK_TITLE = "Create new task";
    private static final String INITIAL_MESSAGE = "Input task name, start time and duration";

    /*Default fields*/
    private static final int DEFAULT_COLUMNSIZE = 20;
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

    /**
     * Initialises the CPANewTaskGUI with DEFAULT_COLUMNSIZE for the CPATextField fields. See
     * constructor with one argument for more details
     */
    public CPANewTaskGUI() {
        this(DEFAULT_COLUMNSIZE);
    }

    /**
     * Initialises the CPATextFields with the given argument, blocks resizability, sets a preferred size
     * given by defaults DEFAULT_WIDTH and DEFAULT_HEIGHT, sets the background color to the default color
     * DEFAULT_COLOR, organises the layout of the GUI and adds the components to the content pane of the frame
     * and finally adds copy, cut and paste functionality for the text fields
     *
     * @param textFieldColumnWidth column width of the text fields within the new task GUI
     */
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

    /**
     * Creates a GridBag Layout and organises the different components of the new task GUI. This includes Labels,
     * TextFields and Buttons.
     */
    private void setCustomGridBagLayout() {

        getContentPane().setLayout(new GridBagLayout());
        //Create external margins (Insets objects)
        Insets defaultInsets = new Insets(0, 10, 10, 10);


        // constraints for task name text field
        GridBagConstraints taskNameConstraints = new GridBagConstraints();
        taskNameConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskNameConstraints.gridx = 1;
        taskNameConstraints.gridy = 1;
        taskNameConstraints.insets = defaultInsets;
        //adds task name text field
        getContentPane().add(getTaskNameField(), taskNameConstraints);

        //constraints for task label
        GridBagConstraints taskLabelConstraints = new GridBagConstraints();
        taskLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        taskLabelConstraints.gridx = 0;
        taskLabelConstraints.gridy = 1;
        taskLabelConstraints.insets = defaultInsets;
        //sets label for task name field
        JLabel taskLabel = new JLabel(TASK_STRING + ":");
        taskLabel.setLabelFor(taskNameField);
        taskLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds task label
        getContentPane().add(taskLabel, taskLabelConstraints);


        //constraints for starting time text field
        GridBagConstraints startingTimeConstraints = new GridBagConstraints();
        startingTimeConstraints.fill = GridBagConstraints.HORIZONTAL;
        startingTimeConstraints.gridx = 1;
        startingTimeConstraints.gridy = 2;
        startingTimeConstraints.insets = defaultInsets;
        //adds starting time text field
        getContentPane().add(getStartingTimeField(), startingTimeConstraints);

        //constraints for starting time label
        GridBagConstraints startingTimeLabelConstraints = new GridBagConstraints();
        startingTimeLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        startingTimeLabelConstraints.gridx = 0;
        startingTimeLabelConstraints.gridy = 2;
        startingTimeLabelConstraints.insets = defaultInsets;
        //sets label for starting time field
        JLabel startingTimeLabel = new JLabel(STARTING_TIME_STRING + ":");
        startingTimeLabel.setLabelFor(startingTimeField);
        startingTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds starting time label
        getContentPane().add(startingTimeLabel, startingTimeLabelConstraints);


        //constraints for duration time text field
        GridBagConstraints durationConstraints = new GridBagConstraints();
        durationConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationConstraints.gridx = 1;
        durationConstraints.gridy = 3;
        durationConstraints.insets = defaultInsets;
        //adds duration time text field
        getContentPane().add(getDurationField(), durationConstraints);

        //constrains for duration time label
        GridBagConstraints durationLabelConstraints = new GridBagConstraints();
        durationLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        durationLabelConstraints.gridx = 0;
        durationLabelConstraints.gridy = 3;
        durationLabelConstraints.insets = defaultInsets;
        //sets label for duration field
        JLabel durationLabel = new JLabel(DURATION_STRING + ":");
        durationLabel.setLabelFor(durationField);
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //adds duration time label
        getContentPane().add(durationLabel, durationLabelConstraints);


        //constraints for status
        GridBagConstraints statusConstraints = new GridBagConstraints();
        statusConstraints.fill = GridBagConstraints.NONE;
        statusConstraints.gridx = 0;
        statusConstraints.gridy = 4;
        statusConstraints.gridwidth = 2;
        //adds status label
        getContentPane().add(getStatus(), statusConstraints);

    }

    /**
     * Initialises the CPATextField objects of the new task GUI.
     * @param textFieldColumnWidth column width of the text fields within the new task GUI
     */
    private void setTextFields(int textFieldColumnWidth) {

        //creates and initialises task name text field
        this.taskNameField = new CPATextField(textFieldColumnWidth);
        taskNameField.setPreferredSize(new Dimension(150, 20));
        taskNameField.setActionCommand(TASK_STRING);
        taskNameField.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //creates and initialises starting time text field
        this.startingTimeField = new CPATextField(textFieldColumnWidth);
        startingTimeField.setActionCommand(STARTING_TIME_STRING);
        startingTimeField.setActionCommand(STARTING_TIME_STRING);
        startingTimeField.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.durationField = new CPATextField(textFieldColumnWidth);
        durationField.setActionCommand(DURATION_STRING);
        durationField.setActionCommand(DURATION_STRING);
        durationField.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.status = new JLabel(INITIAL_MESSAGE, SwingConstants.CENTER);
        status.setFont(FontCollection.DEFAULT_FONT_BOLD);
    }

    /**
     * Associates CTRL+C, CTRL+V, CTRL+X to copy, paste and cut functions for every text field
     */
    private void createActions() {

      //copy operations
      createActionForTextFields(KeyEvent.VK_C, new DefaultEditorKit.CopyAction());

      //cut operations
      createActionForTextFields(KeyEvent.VK_X, new DefaultEditorKit.CutAction());

      //paste operations
      createActionForTextFields(KeyEvent.VK_V, new DefaultEditorKit.PasteAction());

    }

    /**
     * Associates a key pressed given by its integer representation with the action given and adds it to
     * every text field
     * @param keyInt the integer representing the key pressed (i.e. KeyEvent.VK_C)
     * @param action the action to associate (i.e. new DefaultEditorKit.PasteAction())
     */
    private void createActionForTextFields(int keyInt, Action action) {
        taskNameField.supportControlAction(keyInt, action);
        startingTimeField.supportControlAction(keyInt, action);
        durationField.supportControlAction(keyInt, action);
    }

    /**
     * Sets the title and close operations of the GUI and shows it
     */
    public void createAndShowNewTaskGUI() {
        //pre: Frame has been initialised before in the constructor

        setTitle(NEW_TASK_TITLE);
        //DISPOSE_ON_CLOSE so the whole application doesn't close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //sets up frame
        pack();
        setVisible(true);

    }

    /**
     * Gets the taskName textField
     * @return the CPATextField object representing the task name field
     */
    public CPATextField getTaskNameField() {
        return taskNameField;
    }

    /**
     * Gets the startingTime textField
     * @return the CPATextField object representing the starting time field
     */
    public CPATextField getStartingTimeField() {
        return startingTimeField;
    }

    /**
     * Gets the duration textField
     * @return the CPATextField object representing the duration field
     */
    public CPATextField getDurationField() {
        return durationField;
    }

    /**
     * Gets the status label
     * @return the JLabel object representing the status field
     */
    public JLabel getStatus() {
        return status;
    }

}
