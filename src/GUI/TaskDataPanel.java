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
    /** The Gantt Chart to represent the dependencies of the OverallTask*/
    private JFreeChart ganttChart;


    public TaskDataPanel(CPAProjectApplicationGUI applicationReference, OverallTask task) {
        this.applicationReference = applicationReference;
        this.task = task;

        IntervalCategoryDataset dataset = createDataset();

        this.ganttChart = ChartFactory.createGanttChart(
                task.getTaskName(),
                "Subtasks",
                "Date",
                dataset,
                true,
                false,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(ganttChart);
        //disables the free chart menu
        chartPanel.setPopupMenu(null);
        add(chartPanel);
    }

    private IntervalCategoryDataset createDataset() {
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
}
