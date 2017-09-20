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

        //sets preferred size for chart panel and scroll pane
        ganttChartPanel.setPreferredSize(new Dimension((int) ((CPAProjectApplicationGUI.APPLICATION_WIDTH - 500) / 1.5),
                (int) ((CPAProjectApplicationGUI.APPLICATION_HEIGHT - 100) / 1.25)));

        ganttScrollPane.setPreferredSize(new Dimension((int) ((CPAProjectApplicationGUI.APPLICATION_WIDTH - 300) / 1.5),
                (int) ((CPAProjectApplicationGUI.APPLICATION_HEIGHT - 100) / 1.5)));
        //sets vertical speed of scroll
        ganttScrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_BAR_SPEED);
        //sets titled border for gantt chart panel
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Dependencies");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        //compound border uses empty border for margins
        Border margin = new EmptyBorder(10,10,10,10);
        ganttScrollPane.setBorder(new CompoundBorder(border, margin));


        //set general task panel
        setGeneralTaskPanel();
        //sets layout
        setCustomLayout();
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

        Dimension separationDimension = new Dimension(0, 10);

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
        JLabel durationLabel = new JLabel(task.getDuration().toString());
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
        JLabel descriptionLabel = new JLabel(
                task.getDescription().equals("") ? DEFAULT_NO_DESCRIPTION : task.getDescription());
        descriptionLabel.setFont(FontCollection.DEFAULT_FONT_PLAIN);
        descriptionPanel.add(descriptionNameLabel);
        descriptionPanel.add(descriptionLabel);

        generalTaskPanel.setLayout(new BoxLayout(generalTaskPanel, BoxLayout.PAGE_AXIS));
        generalTaskPanel.add(namePanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension));
        generalTaskPanel.add(durationPanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension));
        generalTaskPanel.add(startTimePanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension));
        generalTaskPanel.add(descriptionPanel);
        //separation
        generalTaskPanel.add(Box.createRigidArea(separationDimension));
        //sets titled border
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.LIGHT_GRAY), "Task Panel");
        border.setTitleFont(FontCollection.DEFAULT_FONT_PLAIN);
        generalTaskPanel.setBorder(border);

    }

    private void setCustomLayout() {

        //constraints for tree view of dependencies

        //constraints for options for dependencies

        //constraints for general task panel
        GridBagConstraints generalTaskPanelConstraints = new GridBagConstraints();
        generalTaskPanelConstraints.gridx = 4;
        generalTaskPanelConstraints.gridy = 0;
        generalTaskPanelConstraints.weightx = 0.5;
        generalTaskPanelConstraints.weighty = 1;
        generalTaskPanelConstraints.gridheight = 3;
        generalTaskPanelConstraints.insets = new Insets(20, 30, 20, 30);
        generalTaskPanelConstraints.fill = GridBagConstraints.VERTICAL;
        //generalTaskPanelConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        add(generalTaskPanel, generalTaskPanelConstraints);

        //constraints for scrollpane with gantt chart
        GridBagConstraints scrollPaneGanttConstraints = new GridBagConstraints();
        scrollPaneGanttConstraints.gridx = 4;
        scrollPaneGanttConstraints.gridy = 3;
        scrollPaneGanttConstraints.weightx = 0.5;
        scrollPaneGanttConstraints.weighty = 1;
        scrollPaneGanttConstraints.gridheight = 10;
        scrollPaneGanttConstraints.insets = new Insets(0, 30, 50, 30);
        //scrollPaneGanttConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        scrollPaneGanttConstraints.fill = GridBagConstraints.VERTICAL;
        add(ganttScrollPane, scrollPaneGanttConstraints);
    }
}
