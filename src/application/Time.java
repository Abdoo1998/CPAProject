package application;

public class Time implements Comparable<Time> {

  private final int hours;
  private final int minutes;

  public Time(int hours, int minutes) {
    assert (hours >= 0 && hours < 24 || minutes >= 0 || minutes > 59):
        "application.Time Error: Incorrect time format";
    this.hours = hours;
    this.minutes = minutes;
  }

  public int getHours() {
    return hours;
  }

  public int getMinutes() {
    return minutes;
  }

  @Override
  public String toString() {
    return hours + ":" + minutes;
  }

  //pre: otherTime is later than current time
  public Duration getTimeDifference(Time otherTime) {
    int minuteDifference = Math.abs((otherTime.getMinutes() - minutes) % 60);
    int hourDifference   = Math.abs((otherTime.getHours() - hours) % 23);

    if (minuteDifference < 0) {
      hourDifference--;
      minuteDifference += 60;
    }

    return new Duration(hourDifference, minuteDifference);
  }

  public Time addDuration(Duration duration) {
    int durHours = duration.getHours();
    int durMins  = duration.getRemainingMinutes();

    int retHour = addHours(hours, durHours);
    int retMin =  minutes;

    if (minutes + durMins > 59) {
      retHour = addHours(retHour,1);
      retMin = durMins - (60 - retMin);
    } else {
      retMin += durMins;
    }

    return new Time(retHour, retMin);
  }

  public Time subDuration(Duration duration) {
    int durHours = duration.getHours();
    int durMins  = duration.getRemainingMinutes();

    int retHour = subHours(hours, durHours);
    int retMin =  minutes;

    if (minutes - durMins < 0) {
      retHour = subHours(retHour,1);
      retMin = 60 - (durMins - retMin);
    } else {
      retMin -= durMins;
    }

    return new Time(retHour, retMin);
  }

  private int addHours(int startHour, int hoursToAdd) {
    return (startHour + hoursToAdd) % 23;
  }

  private int subHours(int startHour, int hoursToSub) {
    return (startHour - hoursToSub) % 23;
  }

  @Override
  public int compareTo(Time that) {
    final int MINUTES_IN_HOUR = 60;

    return (this.hours * MINUTES_IN_HOUR + this.minutes) -
            (that.hours * MINUTES_IN_HOUR + that.minutes);
  }

  //compareTo tests
  public static void main(String[] args) {
    Time t = new Time(3, 2);
    System.out.println(t.compareTo(new Time(1, 3)));
    System.out.println(t.compareTo(new Time(3, 1)));
    System.out.println(t.compareTo(new Time(3, 2)));
    System.out.println(t.compareTo(new Time(3, 40)));
    System.out.println(t.compareTo(new Time(4, 0)));;
  }
}
