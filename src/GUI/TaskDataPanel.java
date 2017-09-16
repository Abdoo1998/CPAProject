package GUI;

import application.OverallTask;

import javax.swing.*;

/**
 * Class representing the data panel for a task in the task view of the application. This data panel is opened by
 * clicking on a certain task in the task view, and allows the user to update the task and to view its dependencies.
 *
 * @author gorosgobe
 */
public class TaskDataPanel extends JPanel {
    //TODO: Complete class
    /** A reference to the application*/
    private CPAProjectApplicationGUI applicationReference;
    /** The task to be a data panel of*/
    private OverallTask task;


    public TaskDataPanel(CPAProjectApplicationGUI applicationReference, OverallTask task) {
        this.applicationReference = applicationReference;
        this.task = task;
    }
}
