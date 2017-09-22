package GUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * A simple GUI for a message in the application, with two buttons.
 *
 * @author gorosgobe
 */
public class MessageGUI extends JFrame implements ActionListener {

    /** String containing the title of the message*/
    private final String title;

    /** Path to the application icon*/
    private static final String ICON_PATH = "GUI/images/mainIcon.png";
    /** Ok button string*/
    private static final String OK_BUTTON = "Ok";

    public MessageGUI(String title, String message) {
        this.title = title;

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

        JButton okButton = LayoutUtils.setButton(OK_BUTTON, this);

        setResizable(false);

        setCustomLayout(pane, okButton);
    }

    /**
     * Sets a custom layout for the message GUI.
     */
    private void setCustomLayout(JScrollPane pane, JButton okButton) {
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);

        panel.setLayout(new GridBagLayout());

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


        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 2;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.gridheight = 1;
        buttonConstraints.insets = DEFAULT_INSETS;
        buttonConstraints.fill = GridBagConstraints.NONE;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        panel.add(okButton, buttonConstraints);
    }

    private String insertNewLine(String message) {

        int wordsPerLine = 21;
        int count = 0;
        String copy = "";

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                count++;
                if (count == wordsPerLine) {
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.close();
    }
}
