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

    //Default constants
    /** String used when no description was provided by the user*/
    private static final String DEFAULT_NO_DESCRIPTION = "No description was provided by the user";
    private static final int VERTICAL_SCROLL_BAR_SPEED = 18;




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
        ganttChartPanel.setPreferredSize(new Dimension(CPAProjectApplicationGUI.APPLICATION_WIDTH - 500,
                (int) ((CPAProjectApplicationGUI.APPLICATION_HEIGHT - 100) / 1.5)));

        ganttScrollPane.setPreferredSize(new Dimension(CPAProjectApplicationGUI.APPLICATION_WIDTH - 300,
                (CPAProjectApplicationGUI.APPLICATION_HEIGHT - 100) / 2));
        //sets vertical speed of scroll
        ganttScrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_BAR_SPEED);
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

    private void setCustomLayout() {

        //constraints for scrollpane with gantt chart
        GridBagConstraints scrollPaneGanttConstraints = new GridBagConstraints();
        scrollPaneGanttConstraints.gridx = 0;
        scrollPaneGanttConstraints.gridy = 3;
        scrollPaneGanttConstraints.weightx = 0.5;
        scrollPaneGanttConstraints.weighty = 1;
        scrollPaneGanttConstraints.gridheight = 10;
        scrollPaneGanttConstraints.insets = new Insets(0, 30, 20, 30);
        scrollPaneGanttConstraints.anchor = GridBagConstraints.PAGE_END;
        scrollPaneGanttConstraints.fill = GridBagConstraints.NONE;
        add(ganttScrollPane, scrollPaneGanttConstraints);
    }
}
