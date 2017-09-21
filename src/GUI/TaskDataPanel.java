package GUI;

import application.OverallTask;
import application.SubTask;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

import static GUI.TaskGUI.DEFAULT_INSETS;

/**
 * Class representing the data panel for a task in the task view of the application. This data panel is opened by
 * clicking on a certain task in the task view, and allows the user to update the task and to view its dependencies.
 *
 * @author gorosgobe
 */
public class TaskDataPanel extends JPanel {
    //TODO: Complete class
    /** A reference to the application*/
    private CPAProjectApplicationGUI applicationReference;
    /** The task to be a data panel of*/
    private OverallTask task;
    /** The scroll pane holding the gantt chart*/
    private JScrollPane ganttScrollPane;
    /** The Gantt Chart to represent the dependencies of the OverallTask*/
    private ChartPanel ganttChartPanel;
    /** Panel holding the name, duration, start time and description of the overall task*/
    private JPanel generalTaskPanel;
    /** Panel holding the name of the overall task*/
    private JPanel namePanel;
    /** Panel holding the duration of the overall task*/
    private JPanel durationPanel;
    /** Panel holding the start time of the overall task*/
    private JPanel startTimePanel;
    /** Panel holding the description of the overall task*/
    private JPanel descriptionPanel;
    /** Scroll pane holding the description panel*/
    private JScrollPane descriptionScrollPane;
    /** Panel holding all options for the opened overall task*/
    private OptionsPanel optionsPanel;

    //Default constants
    /** Vertical scrolling speed for gantt chart scroll pane*/
    private static final int VERTICAL_SCROLL_BAR_SPEED = 18;
    /** String used when no description was provided by the user*/
    static final String DEFAULT_NO_DESCRIPTION = "No description was provided by the user.";
    private static final Insets TOP_DEFAULT_INSETS = new Insets(10, 10, 20, 10);


    public TaskDataPanel(CPAProjectApplicationGUI applicationReference, OverallTask task) {
        this.applicationReference = applicationReference;
        this.task = task;
        //sets the gridbag layout
        this.setLayout(new GridBagLayout());

        //sets dependencies panel
        setDependenciesArea();
        //set general task panel
        setGeneralTaskPanel();
        //sets options panel
        setOptionsPanel();
        //sets layout
        setCustomLayout();
    }

    public OverallTask getTask() {
        return task;
    }

    JPanel getGeneralTaskPanel() {
        return generalTaskPanel;
    }

    JLabel getTaskNameLabel() {
        return (JLabel) getGeneralTaskPanel().getComponent(1);
    }

    private void setDependenciesArea() {
        IntervalCategoryDataset dataset = createDataset();

        JFreeChart ganttChart = ChartFactory.createGanttChart(
                "",
                "",
                "",
                dataset,
                true,
                true,
                false
        );


        this.ganttChartPanel = new ChartPanel(ganttChart);
        ganttChartPanel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //disables the menu
        ganttChartPanel.setPopupMenu(null);

        //these four lines fix problem with scaling the gantt chart and labels being scaled too, which meant that the
        // axis labels and the legend would be totally distorted.
        ganttChartPanel.setMinimumDrawHeight(50);
        ganttChartPanel.setMaximumDrawHeight(5000);
        ganttChartPanel.setMinimumDrawWidth(50);
        ganttChartPanel.setMaximumDrawWidth(5000);

        //sets the dependencies scroll pane
        setDependenciesScrollPane();

        //sets vertical speed of scroll
        ganttScrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_BAR_SPEED);
        //sets titled border for gantt chart panel
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Dependencies");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        //compound border uses empty border for margins
        Border margin = new EmptyBorder(10,10,10,10);
        ganttScrollPane.setBorder(new CompoundBorder(border, margin));
    }

    private TaskSeriesCollection createDataset() {
        TaskSeries dependencies = new TaskSeries("Dependencies of " + task.getTaskName() + " task");

        if (task.getAllSubTasks().isEmpty()) {
            return null;
        }

        for (SubTask t : task.getAllSubTasks()) {
            recursiveAdd(dependencies, t);
        }

        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(dependencies);

        return collection;
    }

    private void recursiveAdd(TaskSeries series, SubTask subTaskToAdd) {

        if (subTaskToAdd == null) {
            return;
        }

        SimpleTimePeriod tp1 = new SimpleTimePeriod(date(31, Calendar.OCTOBER, 2001),
                date(17, Calendar.NOVEMBER, 2001));

        //something with subTaskToAdd
        series.add(new Task(subTaskToAdd.getTaskName(), tp1));

        for (SubTask t : subTaskToAdd.getDependencies()) {
            recursiveAdd(series, t);
        }
    }

    private Date date(int day, int month, int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private void optimise() {
        //TODO: COMPLETE, THIS METHOD IS CALLED WHEN CPA IS EVALUATED ON THE GANTT CHART
    }

    private void setDependenciesScrollPane() {
      this.ganttScrollPane = new JScrollPane(ganttChartPanel);
      ganttScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      ganttScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void setGeneralTaskPanel() {
        this.generalTaskPanel = new JPanel(new GridBagLayout());

        //setting up name panel
        JLabel nameLabel = new JLabel(TaskGUI.TASK_STRING + ": ");
        nameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel taskName = new JLabel(task.getTaskName());
        taskName.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //constraints for name labels
        GridBagConstraints nameLabelConstraints = createConstraints(0,0, TOP_DEFAULT_INSETS);
        generalTaskPanel.add(nameLabel, nameLabelConstraints);
        GridBagConstraints nameConstraints = createConstraints(1, 0, TOP_DEFAULT_INSETS);
        generalTaskPanel.add(taskName, nameConstraints);

        //setting up duration labels
        JLabel durationNameLabel = new JLabel(TaskGUI.DURATION_STRING + ": ");
        durationNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel durationLabel = new JLabel(task.getDuration().toString() + " hrs");
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //constraints for duration labels
        GridBagConstraints durationNameLabelConstraints = createConstraints(0,1);
        generalTaskPanel.add(durationNameLabel, durationNameLabelConstraints);
        GridBagConstraints durationConstraints = createConstraints(1, 1);
        generalTaskPanel.add(durationLabel, durationConstraints);

        //setting up start time panel
        JLabel startTimeNameLabel = new JLabel(OverallTaskGUI.START_TIME + ": ");
        startTimeNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel startTimeLabel = new JLabel(task.getStartTime().toString());
        startTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //constraints for start time labels
        GridBagConstraints startTimeNameLabelConstraints = createConstraints(0,2);
        generalTaskPanel.add(startTimeNameLabel, startTimeNameLabelConstraints);
        GridBagConstraints startTimeConstraints = createConstraints(1, 2);
        generalTaskPanel.add(startTimeLabel, startTimeConstraints);

        //setting up description label, text area and scroll pane
        JLabel descriptionNameLabel = new JLabel(OverallTaskGUI.DESCRIPTION_LABEL + ": ");
        descriptionNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        //uses text area because otherwise a label would only represent a single line of text, problems with layout
        JTextArea descriptionTextArea = new JTextArea(
                task.getDescription().equals("") ? DEFAULT_NO_DESCRIPTION : task.getDescription(), 5, 20);
        descriptionTextArea.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //sets de scroll pane to hold the description area, to handle when the description is too long for the size
        //allocated for it
        this.descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        //constraints for description label and scroll pane
        GridBagConstraints descriptionLabelConstraints = createConstraints(0,3);
        generalTaskPanel.add(descriptionNameLabel, descriptionLabelConstraints);
        GridBagConstraints descriptionScrollConstraints = createConstraints(1, 3);
        generalTaskPanel.add(descriptionScrollPane, descriptionScrollConstraints);

        //sets titled border
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Task Panel");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        generalTaskPanel.setBorder(border);

    }

    private void setOptionsPanel() {
      this.optionsPanel = new OptionsPanel(task, this, applicationReference);
    }

    private void setCustomLayout() {

        //constraints for tree view of dependencies
        //TODO: create tree view of dependencies for addition
        //constraints for options for dependencies
        //TODO: create options for dependencies and overall task, then update task view and task in task data panel

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(generalTaskPanel);
        topPanel.add(optionsPanel);

        //constraints for top panel
        GridBagConstraints topPanelConstraints = new GridBagConstraints();
        topPanelConstraints.gridx = 0;
        topPanelConstraints.gridy = 0;
        topPanelConstraints.weightx = 1;
        topPanelConstraints.weighty = 1;
        topPanelConstraints.gridwidth = 3;
        topPanelConstraints.gridheight = 3;
        topPanelConstraints.insets = new Insets(20, 30, 20, 30);
        topPanelConstraints.fill = GridBagConstraints.BOTH;
        topPanelConstraints.anchor = GridBagConstraints.BASELINE_LEADING;
        add(topPanel, topPanelConstraints);

        //constraints for scrollpane with gantt chart : "Dependencies"
        GridBagConstraints scrollPaneGanttConstraints = new GridBagConstraints();
        scrollPaneGanttConstraints.gridx = 0;
        scrollPaneGanttConstraints.gridy = 3;
        scrollPaneGanttConstraints.weightx = 1;
        scrollPaneGanttConstraints.weighty = 1;
        scrollPaneGanttConstraints.gridwidth = 3;
        scrollPaneGanttConstraints.gridheight = 7;
        scrollPaneGanttConstraints.insets = new Insets(0, 30, 50, 30);
        scrollPaneGanttConstraints.fill = GridBagConstraints.BOTH;
        add(ganttScrollPane, scrollPaneGanttConstraints);
    }

    private GridBagConstraints createConstraints(int gridx, int gridy) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = DEFAULT_INSETS;

        return constraints;
    }

    private GridBagConstraints createConstraints(int gridx, int gridy, Insets insets) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = insets;

        return constraints;
    }
}
