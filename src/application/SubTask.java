package application;

import java.util.ArrayList;
import java.util.List;

public class SubTask extends Task {

  private final List<SubTask> dependencies;
  private final List<Task> dependsOnMe;

  public SubTask(String name, Duration duration) {
    super(name, duration);
    this.dependencies  = new ArrayList<>();
    this.dependsOnMe = new ArrayList<>();
  }

  public List<SubTask> getDependencies() {
    return dependencies;
  }

  public List<Task> getDependsOnMe() {
    return dependsOnMe;
  }

  public void addDependency(SubTask dep) {
    dependencies.add(dep);
  }

  public void removeDependency(SubTask dep) {
    dependencies.remove(dep);
  }

  public void addDependsOnThis(SubTask dep) {
    dependsOnMe.add(dep);
  }

  public void removeDependsOnThis(SubTask dep) {
    dependsOnMe.remove(dep);
  }

  @Override
  public String toString() {
    return "Subtask: " + super.toString();
  }

  /**
   * Returns the subTask with equal task name as the taskName parameter. The method searches for the SubTask in
   * the dependencies of the parent task. A precondition is that the subTask is present in the dependencies of the parent
   * task given.
   * @param parent the task that contains the SubTask
   * @param taskName the string representing the task name of the subTask to return
   * @return the subTask associated to the taskToBeAdded parameter under parent.
   */
  public static SubTask findSubTaskInDependencies(OverallTask parent, String taskName) {
    //subtask is in dependencies as it has been selected in the tree view

    for (SubTask t : parent.getAllSubTasks()) {
      SubTask subTask = findRecursivelySubTask(t, taskName);
      if (subTask != null) {
        return subTask;
      }
    }
    //will not get here because of precondition
    return null;
  }

  /**
   * Recursive helper for findSubTaskInDependencies, returns the SubTask searched in the parent parameter that matches
   * the taskName string (with its task name field equal to it).
   * @param parent the task that contains the subtask
   * @param taskName the string representing the task name of the subTask to return
   * @return the subTask associated to the taskToBeAdded parameter under parent.
   */
  private static SubTask findRecursivelySubTask(SubTask parent, String taskName) {

    if (parent.getTaskName().equals(taskName)) {
      return parent;
    }

    for (SubTask t : parent.getDependencies()) {
      SubTask subTask = findRecursivelySubTask(t, taskName);
      if (subTask != null) {
        return subTask;
      }
    }

    return null;
  }

  /**
   * Finds the parent of the task with the given task name. A precondition is that the task
   * with the supplied name exists in the dependencies of the initial root value. Another precondition is that the child
   * is not directly under the overall task (in its dependencies).
   * @param task
   * @param subTaskName
   * @return
   */
  public static SubTask findParentSubTaskOf(OverallTask task, String subTaskName) {

    for (SubTask t : task.getAllSubTasks()) {
      SubTask parent = findRecursiveParentSubtaskOf(t, subTaskName);
      if (parent != null) {
        return parent;
      }
    }

    //will not get here
    return null;
  }

  /**
   * Recursive helper that finds the parent of the task with the given task name. A precondition is that the task
   * with the supplied name exists in the dependencies of the initial root value of the call in the non-helper function.
   * @param root The subtask from which to start searching for the parent of the task with supplied task name
   * @param taskName the name of the task of which we want to find the parent
   * @return the parent task of the task with supplied task name
   */
  private static SubTask findRecursiveParentSubtaskOf(SubTask root, String taskName) {

    for (SubTask t : root.getDependencies()) {
      if (t.getTaskName().equals(taskName)) {
        //then original subtask is the parent we are looking for
        return root;
      } else {
        SubTask parent = findRecursiveParentSubtaskOf(t, taskName);
        if (parent != null) {
          return parent;
        }
      }
    }

    return null;
  }
}
