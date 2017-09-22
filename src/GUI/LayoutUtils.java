package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * Class holding functions commonly used in the layout creation.
 *
 * @author gorosgobe
 */
public final class LayoutUtils {

    private LayoutUtils() {}

    public static GridBagConstraints createConstraints(int gridx, int gridy) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = DEFAULT_INSETS;

        return constraints;
    }

    public static GridBagConstraints createConstraints(int gridx, int gridy, Insets insets) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = insets;

        return constraints;
    }

    public static GridBagConstraints createConstraints(int gridx, int gridy, Insets insets, int anchor) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = anchor;
        constraints.insets = insets;

        return constraints;
    }

    public static JButton setButton(String labelAndIdentifier, ActionListener listener) {

        JButton button = new JButton(labelAndIdentifier);
        button.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        button.setActionCommand(labelAndIdentifier);
        button.addActionListener(listener);

        return button;
    }
}
