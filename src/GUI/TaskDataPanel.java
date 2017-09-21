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

    //Default constants
    /** Vertical scrolling speed for gantt chart scroll pane*/
    private static final int VERTICAL_SCROLL_BAR_SPEED = 18;
    /** String used when no description was provided by the user*/
    static final String DEFAULT_NO_DESCRIPTION = "No description was provided by the user.";




    public TaskDataPanel(CPAProjectApplicationGUI applicationReference, OverallTask task) {
        this.applicationReference = applicationReference;
        this.task = task;
        //sets the gridbag layout
        this.setLayout(new GridBagLayout());

        //sets dependencies panel
        setDependenciesArea();
        //set general task panel
        setGeneralTaskPanel();
        //sets layout
        setCustomLayout();
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

        //sets the scroll pane
        setScrollPane();

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

    public JFreeChart getGanttChart() {
        return ganttChartPanel.getChart();
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

    private void setScrollPane() {
      this.ganttScrollPane = new JScrollPane(ganttChartPanel);
      ganttScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      ganttScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void setGeneralTaskPanel() {
        this.generalTaskPanel = new JPanel();

        Dimension separationDimension1 = new Dimension(0, 8);
        Dimension separationDimension2 = new Dimension(0, 4);


        this.namePanel = new JPanel();
        this.durationPanel = new JPanel();
        this.startTimePanel = new JPanel();
        this.descriptionPanel = new JPanel();

        //setting up name panel
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel(TaskGUI.TASK_STRING + ": ");
        nameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel taskName = new JLabel(task.getTaskName());
        taskName.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        namePanel.add(nameLabel);
        namePanel.add(taskName);

        //setting up duration panel
        durationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel durationNameLabel = new JLabel(TaskGUI.DURATION_STRING + ": ");
        durationNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel durationLabel = new JLabel(task.getDuration().toString() + " hrs");
        durationLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        durationPanel.add(durationNameLabel);
        durationPanel.add(durationLabel);

        //setting up start time panel
        startTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel startTimeNameLabel = new JLabel(OverallTaskGUI.START_TIME + ": ");
        startTimeNameLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        JLabel startTimeLabel = new JLabel(task.getStartTime().toString());
        startTimeLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        startTimePanel.add(startTimeNameLabel);
        startTimePanel.add(startTimeLabel);

        //setting up description panel
        descriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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
        descriptionPanel.add(descriptionNameLabel);
        descriptionPanel.add(descriptionTextArea);

        generalTaskPanel.setLayout(new BoxLayout(generalTaskPanel, BoxLayout.PAGE_AXIS));
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension1));
        generalTaskPanel.add(namePanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension2));
        generalTaskPanel.add(durationPanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension2));
        generalTaskPanel.add(startTimePanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension2));
        generalTaskPanel.add(descriptionPanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension1));
        //sets titled border
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Task Panel");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        generalTaskPanel.setBorder(border);

    }

    private void setCustomLayout() {

        //constraints for tree view of dependencies
        //TODO: create tree view of dependencies for addition
        //constraints for options for dependencies
        //TODO: create options for dependencies and overall task, then update task view and task in task data panel

        //constraints for general task panel
        GridBagConstraints generalTaskPanelConstraints = new GridBagConstraints();
        generalTaskPanelConstraints.gridx = 4;
        generalTaskPanelConstraints.gridy = 0;
        generalTaskPanelConstraints.weightx = 1;
        generalTaskPanelConstraints.weighty = 0.75;
        generalTaskPanelConstraints.gridwidth = 3;
        generalTaskPanelConstraints.gridheight = 3;
        generalTaskPanelConstraints.insets = new Insets(20, 30, 20, 30);
        generalTaskPanelConstraints.fill = GridBagConstraints.VERTICAL;
        generalTaskPanelConstraints.anchor = GridBagConstraints.BASELINE_LEADING;
        add(generalTaskPanel, generalTaskPanelConstraints);

        //constraints for scrollpane with gantt chart
        GridBagConstraints scrollPaneGanttConstraints = new GridBagConstraints();
        scrollPaneGanttConstraints.gridx = 4;
        scrollPaneGanttConstraints.gridy = 3;
        scrollPaneGanttConstraints.weightx = 1;
        scrollPaneGanttConstraints.weighty = 1;
        scrollPaneGanttConstraints.gridwidth = 3;
        scrollPaneGanttConstraints.gridheight = 7;
        scrollPaneGanttConstraints.insets = new Insets(0, 30, 50, 30);
        scrollPaneGanttConstraints.fill = GridBagConstraints.BOTH;
        add(ganttScrollPane, scrollPaneGanttConstraints);
    }
}
