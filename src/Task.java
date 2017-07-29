import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {

  private final String name;
  private final Time startTime;
  private final int duration;
  private final Set<Task> dependencies;

  public Task(String name, Time startTime, int duration) {
    this.name = name;
    this.startTime = startTime;
    this.duration = duration;
    this.dependencies = new HashSet<>();
  }

  public Time getStartTime() {
    return startTime;
  }

  public int getDuration() {
    return duration;
  }

  public Set<Task> getDependencies() {
    return dependencies;
  }

  public void addDependency(Task newDep) {
    dependencies.add(newDep);
  }

  public List<Task> applyCPA() {
    // returns the ordered list of tasks to perform in their most efficient
    // order
    return null;
  }
}
