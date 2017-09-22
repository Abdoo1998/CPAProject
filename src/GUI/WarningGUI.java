package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * A warning GUI that performs the actions supplied. If no actions are supplied, both actions close the frame.
 *
 * @author gorosgobe
 */
public class WarningGUI extends AbstractMessage implements ActionListener {

    /** Continue button string*/
    private static final String CONTINUE_BUTTON = "Continue";
    /** Cancel button string*/
    private static final String CANCEL_BUTTON = "Cancel";
    /** The action for the continue button*/
    private Action continueButtonAction;
    /** The action for the cancel button*/
    private Action cancelButtonAction;

    /**
     * Creates a warning GUI with the title and message supplied. Default actions assigned to the buttons are closing actions.
     * @param title the title of the frame
     * @param message the message to display
     */
    public WarningGUI(String title, String message) {
        this(title, message, null, null);
    }

    /**
     * Creates a warning GUI with the title and message supplied. Also assigns the actions supplied to the continue and
     * cancel buttons
     * @param title the title of the frame
     * @param message the message to display
     * @param continueButtonAction the action for the continue button
     * @param cancelButtonAction the action for the cancel button
     */
    public WarningGUI(String title, String message, Action continueButtonAction, Action cancelButtonAction) {
        super(title, message);
        //if it is null, default to close
        if (continueButtonAction != null) {
            this.continueButtonAction = continueButtonAction;
        } else {
            this.continueButtonAction = AbstractMessage.getCloseAction(this);
        }

        //if it is null, default to close
        if (cancelButtonAction != null) {
            this.cancelButtonAction = cancelButtonAction;
        } else {
            this.cancelButtonAction = AbstractMessage.getCloseAction(this);
        }

        JButton continueButton = LayoutUtils.setButton(CONTINUE_BUTTON, this);
        JButton cancelButton = LayoutUtils.setButton(CANCEL_BUTTON, this);

        setResizable(false);

        setCustomLayout(continueButton, cancelButton);

    }

    public void setContinueButtonAction(Action continueButtonAction) {
        this.continueButtonAction = continueButtonAction;
    }

    public void setCancelButtonAction(Action cancelButtonAction) {
        this.cancelButtonAction = cancelButtonAction;
    }

    /**
     * Sets the custom layout of the warning GUI
     * @param continueButton the continue button to set
     * @param cancelButton the cancel button to set
     */
    private void setCustomLayout(JButton continueButton, JButton cancelButton) {

        GridBagConstraints continueButtonConstraints = new GridBagConstraints();
        continueButtonConstraints.gridx = 2;
        continueButtonConstraints.gridy = 2;
        continueButtonConstraints.gridwidth = 1;
        continueButtonConstraints.gridheight = 1;
        continueButtonConstraints.insets = DEFAULT_INSETS;
        continueButtonConstraints.fill = GridBagConstraints.NONE;
        continueButtonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        getPanel().add(continueButton, continueButtonConstraints);

        GridBagConstraints cancelButtonConstraints = new GridBagConstraints();
        cancelButtonConstraints.gridx = 3;
        cancelButtonConstraints.gridy = 2;
        cancelButtonConstraints.gridwidth = 1;
        cancelButtonConstraints.gridheight = 1;
        cancelButtonConstraints.insets = DEFAULT_INSETS;
        cancelButtonConstraints.fill = GridBagConstraints.NONE;
        cancelButtonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        getPanel().add(cancelButton, cancelButtonConstraints);
    }

    /**
     * Performs the actions previously supplied to the warning GUI
     * @param actionEvent the action event generated
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //perform the actions supplied
        switch (actionEvent.getActionCommand()) {
            case CONTINUE_BUTTON: {
                continueButtonAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                break;
            }
            case CANCEL_BUTTON: {
                cancelButtonAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        }

    }
}
