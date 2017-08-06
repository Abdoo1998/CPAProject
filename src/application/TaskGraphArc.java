package application;

public class TaskGraphArc {

  private Task task;
  private final TaskGraphNode parent;
  private final TaskGraphNode child;

  public TaskGraphArc(Task task, TaskGraphNode parent, TaskGraphNode child) {
    assert (task instanceof SubTask): "Cannot add an application.OverallTask to arc";
    this.task = task;
    this.parent = parent;
    this.child = child;
  }

  public Task getTask() {
    return task;
  }

  public TaskGraphNode getParent() {
    return parent;
  }

  public TaskGraphNode getChild() {
    return child;
  }
}
