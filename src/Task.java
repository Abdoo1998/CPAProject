import java.util.HashSet;
import java.util.Set;

public abstract class Task {

  private final String name;
  private final Duration duration;
  private final Set<Task> dependencies;

  public Task(String name, Duration duration) {
    this.name = name;
    this.duration = duration;
    this.dependencies = new HashSet<>();
  }

  public String getTaskName() {
    return name;
  }

  public Duration getDuration() {
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
