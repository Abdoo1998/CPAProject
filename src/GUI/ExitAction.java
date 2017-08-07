package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Class representing the exit application action
 *
 * @author gorosgobe
 */
public class ExitAction extends AbstractAction {

    /** A reference to the application GUI*/
    private CPAProjectApplicationGUI applicationReference;

    /**
     * Constructor for the exit action.
     * @param applicationReference a reference to the CPAProjectApplicationGUI
     */
    public ExitAction(CPAProjectApplicationGUI applicationReference) {
        super();
        this.applicationReference = applicationReference;
    }

    /**
     * Overriden actionPerformed method, saves the data and closes the application
     * @param actionEvent the action event generated
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //save data into file
        saveData();
        //close application window
        applicationReference.dispatchEvent(new WindowEvent(applicationReference, WindowEvent.WINDOW_CLOSING));

    }

    /**
     * Saves the user's data
     */
    private void saveData() {
        //TODO: PABLO, SAVE DATA INTO FILE
    }
}
