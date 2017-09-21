package GUI;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Represents a text field used in the CPA application
 * @author gorosgobe
 */
public class CPATextField extends JTextField {

    /**
     * Creates a new CPATextfield object
     * @param i the column width as specified by the JTextfield constructor
     */
    public CPATextField(int i) {
        super(i);
        supportControlAction(KeyEvent.VK_C, new DefaultEditorKit.CopyAction());
        supportControlAction(KeyEvent.VK_V, new DefaultEditorKit.PasteAction());
        supportControlAction(KeyEvent.VK_X, new DefaultEditorKit.CutAction());
        setFont(FontCollection.DEFAULT_FONT_PLAIN);
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
