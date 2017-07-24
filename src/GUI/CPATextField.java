package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a text field used in the CPA application
 * @author gorosgobe
 */
public class CPATextField extends JTextField {

    public CPATextField(int i) {
        super(i);
    }

    /**
     * Gives the GUI.CPATextField the capability of a CTRL+X combination of keys with a specific action,
     * where X is the given key.
     *
     * @param keyInt the key int associated with the control action to implement (i.e. KeyEvent.VK_C)
     * @param action the action to associate to CTRL+KEY
     */
    public void supportControlAction(int keyInt, Action action) {
        InputMap inputMap = this.getInputMap();
        KeyStroke key = KeyStroke.getKeyStroke(keyInt, Event.CTRL_MASK);
        inputMap.put(key, action);
    }

}
