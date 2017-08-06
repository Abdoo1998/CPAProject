package GUI;


import application.OverallTask;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the OverallTask GUI component in the Task View tab of the main application frame.
 *
 * @author gorosgobe
 */
public class OverallTaskViewComponent extends JPanel {

    /** Label representing the name of the OverallTask*/
    private final JLabel name;
    /** Label representing the duration of the OverallTask*/
    private final JLabel duration;
    /** Label representing the starting time of the OverallTask*/
    private final JLabel startTime;

    /**
     * Constructor for the OverallTaskViewComponent that takes the task that will populate it and the dimension
     * of the component. The dimension is required so the CPAProjectApplicationGUI can specify who to modify its
     * task panel within the scroll pane to add more OverallTaskViewComponents. This constructor also initalises
     * the task's layout and positions its components, and then sets the preferred size to the dimension given.
     * @param task the overall task to represent with the component
     * @param dimension the dimension of the component
     */
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

    /**
     * Positions the name, duration and starting time in the component with a GridBagLayout. For more information
     * on GridBagLayout and GridBagConstraints, see the Java tutorials.
     */
    private void setTaskViewComponentLayout() {
        setLayout(new GridBagLayout());

        //constraints for the name
        GridBagConstraints nameConstraints = new GridBagConstraints();
        nameConstraints.gridx = 0;
        nameConstraints.gridy = 0;
        nameConstraints.insets = new Insets(0, 10, 10, 10);
        add(name, nameConstraints);

        //constraints for the duration
        GridBagConstraints durationConstraints = new GridBagConstraints();
        durationConstraints.gridx = 0;
        durationConstraints.gridy = 1;
        durationConstraints.insets = new Insets(0, 10, 10, 10);
        add(duration, durationConstraints);

        //constraints for the starting time
        GridBagConstraints startTimeConstraints = new GridBagConstraints();
        startTimeConstraints.gridx = 0;
        startTimeConstraints.gridy = 2;
        durationConstraints.insets = new Insets(0, 10, 10, 10);
        add(startTime, startTimeConstraints);
    }

}
