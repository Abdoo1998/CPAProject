package GUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Abstract GUI representing a message.
 *
 * @author gorosgobe
 */
public abstract class AbstractMessage extends JFrame {

    /** String containing the title of the message*/
    private final String title;

    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /** A reference to the customisable panel*/
    private JPanel panel;
    /** An int used to put into different lines the message supplied in the argument to the constructor*/
    private static final int WORDS_PER_LINE = 16;

    public AbstractMessage(String title, String message) {
        this.title = title;
        this.panel = new JPanel(new GridBagLayout());

        String messageWithNewLines = insertNewLine(message);
        //creates the text area that will hold the message
        JTextPane textPane = new JTextPane();
        textPane.setText(messageWithNewLines);
        textPane.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        textPane.setEditable(false);
        textPane.setOpaque(false);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JScrollPane pane = new JScrollPane(textPane);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBorder(null);

        setResizable(false);

        setCustomLayout(pane);
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sets a custom layout for the message GUI.
     */
    private void setCustomLayout(JScrollPane pane) {

        this.getContentPane().add(panel);

        GridBagConstraints messageConstraints = new GridBagConstraints();
        messageConstraints.gridx = 0;
        messageConstraints.gridy = 0;
        messageConstraints.weightx = 1;
        messageConstraints.weighty = 1;
        messageConstraints.gridwidth = 3;
        messageConstraints.gridheight = 2;
        messageConstraints.insets = new Insets(10, 10, 10, 10);
        messageConstraints.fill = GridBagConstraints.BOTH;
        messageConstraints.anchor = GridBagConstraints.CENTER;
        panel.add(pane, messageConstraints);

    }

    private String insertNewLine(String message) {

        int count = 0;
        String copy = "";

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                count++;
                if (count == WORDS_PER_LINE) {
                    copy += '\n';
                    count = 0;
                } else {
                    copy += ' ';
                }
            } else {
                copy += message.charAt(i);
            }
        }
        return copy;
    }

    /**
     * Closes the frame
     */
    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Returns a closing action.
     * @param message the message to close
     * @return a closing action for the message supplied
     */
    public static Action getCloseAction(AbstractMessage message) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                message.close();
            }
        };
    }

    /**
     * Initialises and shows the Message GUI JFrame
     */
    public void createAndShowGUI() {

        setTitle(title);
        // Sets what to do when frame closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)).getImage());

        //shows the frame
        pack();
        setLocationRelativeTo(null); //centers frame
        setVisible(true);
    }

}
