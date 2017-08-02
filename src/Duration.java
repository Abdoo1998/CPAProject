public final class Duration {

  private static final int MINUTES_IN_HOUR = 60;
  private static final int SECONDS_IN_MIN = 60;
  private final int hours;
  private final int minutes;

  public Duration(int hours, int minutes) {
    this.hours = hours;
    this.minutes = minutes;
  }

  public int getHours() {
    return hours;
  }

  public int getRemainingMinutes() {
    return minutes;
  }

  public int getTotalMinutes() {
    return (hours * MINUTES_IN_HOUR) + minutes;
  }

  public int getTotalSeconds() {
    return (hours * MINUTES_IN_HOUR) + (SECONDS_IN_MIN * minutes);
  }

  public Time getEndTime(Time startTime) {
    int endHour = startTime.getHours()    + hours;
    int endMin  = startTime.getMinutes()  + minutes;

    if (endMin >= MINUTES_IN_HOUR) {
      endHour++;
      endMin -= MINUTES_IN_HOUR;
    }

    return new Time(endHour, endMin);
  }
}
