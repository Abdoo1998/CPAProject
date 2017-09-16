package GUI;

import javax.swing.*;
import java.awt.*;

public class RemovableTabComponent extends JPanel {

    public RemovableTabComponent(String taskName, JTabbedPane pane, TaskDataPanel taskDataPanel) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel label = new JLabel(taskName);
        label.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        add(label);
        //adds space between label and button
        JButton removeButton = new RemoveButton(pane, this);
        add(removeButton);
    }

}