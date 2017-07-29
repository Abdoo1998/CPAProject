import java.util.HashSet;
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

  public TaskGraph generateGraph() {
    // generates a 'tree/map' type structure as
    return null;
  }
}
