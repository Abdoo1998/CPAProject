package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * A simple GUI for a message in the application, with two buttons.
 *
 * @author gorosgobe
 */
public class MessageGUI extends AbstractMessage implements ActionListener {

    /** Ok button string*/
    private static final String OK_BUTTON = "Ok";

    public MessageGUI(String title, String message) {
        super(title, message);

        JButton okButton = LayoutUtils.setButton(OK_BUTTON, this);

        setResizable(false);

        setCustomLayout(okButton);
    }

    /**
     * Sets a custom layout for the message GUI.
     */
    private void setCustomLayout(JButton okButton) {

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 2;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.gridheight = 1;
        buttonConstraints.insets = DEFAULT_INSETS;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        getPanel().add(okButton, buttonConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.close();
    }
}
