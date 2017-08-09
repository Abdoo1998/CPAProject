package application;

public class TaskGraphArc {

  private Task task;
  private TaskGraphNode parent;
  private TaskGraphNode child;

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

  public void setParent(TaskGraphNode parent) {
    this.parent = parent;
  }

  public void setChild(TaskGraphNode child) {
    this.child = child;
  }
}
