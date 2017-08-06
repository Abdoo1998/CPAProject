package GUI;


import application.OverallTask;

import javax.swing.*;
import java.awt.*;

public class OverallTaskViewComponent extends JPanel {

    private final JLabel name;
    private final JLabel duration;
    private final JLabel startTime;

    public OverallTaskViewComponent(OverallTask task, Dimension dimension) {
        this.name = new JLabel(task.getTaskName());
        name.setFont(FontCollection.BIG_BOLD_FONT);
        this.duration = new JLabel("Duration: " + task.getDuration().toString());
        duration.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        this.startTime = new JLabel("Starts at: " + task.getStartTime().toString());
        startTime.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        setBackground(new Color(150, 120, 23));
        setTaskViewComponentLayout();
        setPreferredSize(dimension);
    }

    private void setTaskViewComponentLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints nameConstraints = new GridBagConstraints();
        nameConstraints.gridx = 0;
        nameConstraints.gridy = 0;
        nameConstraints.insets = new Insets(0, 10, 10, 10);
        add(name, nameConstraints);

        GridBagConstraints durationConstraints = new GridBagConstraints();
        durationConstraints.gridx = 0;
        durationConstraints.gridy = 1;
        durationConstraints.insets = new Insets(0, 10, 10, 10);
        add(duration, durationConstraints);

        GridBagConstraints startTimeConstraints = new GridBagConstraints();
        startTimeConstraints.gridx = 0;
        startTimeConstraints.gridy = 2;
        durationConstraints.insets = new Insets(0, 10, 10, 10);
        add(startTime, startTimeConstraints);
    }

}
