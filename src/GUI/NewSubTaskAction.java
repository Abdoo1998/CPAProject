package GUI;

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

    /**
     * Constructor for the action. Calls super and initialises the application GUI field
     * @param applicationReference a reference to the CPAProjectApplicationGUI
     */
    public NewSubTaskAction(CPAProjectApplicationGUI applicationReference) {
        super();
        this.applicationReference = applicationReference;
    }

    /**
     * Overriden actionPerformed method to show the GUI when a certain button in the menu is pressed
     * @param actionEvent the action event generated
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO: Add sub task to Overall task or Subtask
    }
}
