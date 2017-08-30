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
}
