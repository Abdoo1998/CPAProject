package GUI.menus.actions;

import GUI.CPAProjectApplicationGUI;
import GUI.SubTaskGUI;
import application.OverallTask;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class representing the intention of the user of creating a new SubTask
 *
 * @author gorosgobe
 */
public class NewSubTaskAction extends AbstractAction {

    /** A reference to the application GUI*/
    private CPAProjectApplicationGUI applicationReference;
    /** A boolean indicating whether the overall parent task should be selected by default*/
    private OverallTask parentTask;

    /**
     * Constructor for the action. Calls super and initialises the application GUI field
     * @param applicationReference a reference to the CPAProjectApplicationGUI
     * @param parentTask the parent overall task to be selected by default
     */
    public NewSubTaskAction(CPAProjectApplicationGUI applicationReference, OverallTask parentTask) {
        super();
        this.applicationReference = applicationReference;
        this.parentTask = parentTask;
    }

    public CPAProjectApplicationGUI getApplicationReference() {
        return applicationReference;
    }

    public OverallTask getParentTask() {
        return parentTask;
    }

    /**
     * Overriden actionPerformed method to show the GUI when a certain button in the menu is pressed
     * @param actionEvent the action event generated
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        SubTaskGUI subTaskGUI;

        if (parentTask != null) {
            subTaskGUI = new SubTaskGUI(applicationReference.getTasks(), parentTask);
        } else {
            subTaskGUI = new SubTaskGUI(applicationReference.getTasks());
        }
        SwingUtilities.invokeLater(subTaskGUI::showGUI);

    }
}
