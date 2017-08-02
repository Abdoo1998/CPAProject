package application;

public abstract class Task {

  private final String name;
  private final Duration duration;

  public Task(String name, Duration duration) {
    this.name = name;
    this.duration = duration;
  }

  public String getTaskName() {
    return name;
  }

  public Duration getDuration() {
    return duration;
  }
}
