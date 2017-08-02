public class FixedTask extends Task{

  private final Time startTime;

  public FixedTask(String name, Duration duration, Time startTime) {
    super(name, duration);
    this.startTime = startTime;
  }

  public Time getStartTime() {
    return startTime;
  }
}
