package application;

import java.util.*;

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

  public static boolean hasSubTaskBeenVisited(SubTask subTask, Set<SubTask> visitedSubTasks) {
    return visitedSubTasks.contains(subTask);
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

    Set<SubTask> visitedSubtasks = new HashSet<>();

    for (SubTask t : parent.getAllSubTasks()) {
      SubTask subTask = findRecursivelySubTask(t, taskName, visitedSubtasks);
      if (subTask != null) {
        return subTask;
      }
    }

    return null;
  }

  /**
   * Recursive helper for findSubTaskInDependencies, returns the SubTask searched in the parent parameter that matches
   * the taskName string (with its task name field equal to it).
   * @param parent the task that contains the subtask
   * @param taskName the string representing the task name of the subTask to return
   * @return the subTask associated to the taskToBeAdded parameter under parent.
   */
  private static SubTask findRecursivelySubTask(SubTask parent, String taskName, Set<SubTask> visitedSubTasks) {

    //if the subtask is the one we are looking for, return it
    if (parent.getTaskName().equals(taskName)) {
      return parent;
    }

    //if subtask has already been visited, return not found
    if (hasSubTaskBeenVisited(parent, visitedSubTasks)) {
      return null;
    }

    //we have checked already that its not the parent, so we have visited it
    visitedSubTasks.add(parent);

    //else
    for (SubTask t : parent.getDependencies()) {
      SubTask subTask = findRecursivelySubTask(t, taskName, visitedSubTasks);
      if (subTask != null) {
        return subTask;
      }
    }

    return null;
  }



  public static SubTask findParentOf(OverallTask task, String subTaskName) {


    for (SubTask t : task.getAllSubTasks()) {
      SubTask parent = findRecursiveParentSubtaskOf(t, subTaskName);
      if (parent != null) {
        return parent;
      }
    }

    return null;

  }


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

  public static void main(String[] args) {
    //test for find subtask in dependencies
    Duration dummyDuration = new Duration(0, 10);
    Time dummyTime = new Time(0, 10);

    OverallTask overallTask = new OverallTask("A", dummyDuration, dummyTime);

    SubTask B = new SubTask("B", dummyDuration);
    SubTask C = new SubTask("C", dummyDuration);
    SubTask D = new SubTask("D", dummyDuration);
    SubTask E = new SubTask("E", dummyDuration);
    SubTask F = new SubTask("F", dummyDuration);
    SubTask G = new SubTask("G", dummyDuration);
    SubTask H = new SubTask("H", dummyDuration);
    SubTask I = new SubTask("I", dummyDuration);
    SubTask J = new SubTask("J", dummyDuration);

    overallTask.addSubTask(B);
    overallTask.addSubTask(D);
    overallTask.addSubTask(G);

    B.addDependency(C);

    D.addDependency(E);
    D.addDependency(F);

    F.addDependency(E);

    G.addDependency(H);
    G.addDependency(J);

    H.addDependency(I);
    J.addDependency(I);

    System.out.println(findSubTaskInDependencies(overallTask, "A"));
    System.out.println(findSubTaskInDependencies(overallTask, "B"));
    System.out.println(findSubTaskInDependencies(overallTask, "C"));
    System.out.println(findSubTaskInDependencies(overallTask, "D"));
    System.out.println(findSubTaskInDependencies(overallTask, "E"));
    System.out.println(findSubTaskInDependencies(overallTask, "F"));
    System.out.println(findSubTaskInDependencies(overallTask, "G"));
    System.out.println(findSubTaskInDependencies(overallTask, "H"));
    System.out.println(findSubTaskInDependencies(overallTask, "I"));
    System.out.println(findSubTaskInDependencies(overallTask, "J"));



  }

}
