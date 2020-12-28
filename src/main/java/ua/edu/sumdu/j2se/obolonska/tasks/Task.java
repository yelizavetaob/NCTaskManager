package ua.edu.sumdu.j2se.obolonska.tasks;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Task implements Cloneable, Serializable{
    /**Title is the title of a task*/
    private String title;
    /**Time is the time of a task that is executed 1 time (Fom 01.02.2000)*
     * Start is the beginning of a repeating task
     * End is the end of a repetitive task
     * Interval is the duration of one task
     */
    private LocalDateTime time, start, end;
    private int interval;
    /**Define an active or a non-active task*/
    private boolean active;
    /**Constructor for creating a task that does not repeat */
    public Task(final String title, final LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("argument 'time' is below 0");
        }
        this.title = title;
        this.time = time;
    }
    /**Constructor for creating a task that repeats */
    public Task(final String title, final LocalDateTime start, final LocalDateTime end, final int interval) {
        if (start == null) {
            throw new IllegalArgumentException("start is less than zero");
        } else if (end == null) {
            throw new IllegalArgumentException("end is less than zero");
        } else if (interval <= 0){
            throw new IllegalArgumentException("interval is less than or equal to zero");
        } else if(start.isAfter(end)){
            throw new IllegalArgumentException("start is greater than end");
        }

        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    /**@return title of task*/
    public String getTitle() {
        return title;
    }
    /**@param title title to set*/
    public void setTitle(final String title) {
        this.title = title;
    }
    /**return activity of task*/
    public boolean isActive() {
        return active;
    }
    /**@param active state of task to set*/
    public void setActive(final boolean active) {
        this.active = active;
    }
    /**@return time if the task is non-repeated
     * or @return start if the task is repeated*/
    public LocalDateTime getTime() {
        return interval != 0 ? start : time;
    }
    /**@param time execution of non-repeated task to set,
     * if task is repeated then task is transforming to non-repeated*/
    public void setTime(final LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("argument 'time' is less than 0");
        }
        this.time = time;
        this.start = null;
        this.end = null;
        this.interval = 0;
    }
    /**@return start time of interval of repeated task,
     *if task is non-repeated then returning execution time*/
    public LocalDateTime getStartTime() {
        return start != null ? start : time;
    }
    /**@return end time of interval of repeated task,
     *if task is non-repeated then returning execution time */
    public LocalDateTime getEndTime() {
        return end != null ? end : time;
    }
    /**@return interval if the task is repeating and
     * 0 if the task is not ing*/
    public int getRepeatInterval() {
        return interval != 0 ? interval : 0;
    }
    /**@param start is the beginning of execution,
     * @param end is the end of execution,
     * @param interval is the period of running a repeating task to set
     * if the task is not repeated, then the task goes to the repeated one*/
    public void setTime(final LocalDateTime start, final LocalDateTime end, final int interval) {
        if (start == null) {
            throw new IllegalArgumentException("start is less than zero");
        } else if (end == null) {
            throw new IllegalArgumentException("end is less than zero");
        } else if (interval <= 0){
            throw new IllegalArgumentException("interval is less than or equal to zero");
        } else if(start.isAfter(end)){
            throw new IllegalArgumentException("start is greater than end");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = null;
    }
    /**@return task is repeated or non-repeated*/
    public boolean isRepeated() {
        return interval != 0;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    /**@return the current closest time of the active task*/
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            throw new IllegalArgumentException("current is null");
        }
        LocalDateTime nextDate;
        if (active) {
            if (time != null && current.isBefore(time)) {
                return time;
            }
            if (start != null && current.isBefore(start)) {
                return start;
            }
            if (start != null) {
                nextDate = LocalDateTime.ofEpochSecond((current.toEpochSecond(ZoneOffset.UTC) - ((current.toEpochSecond(ZoneOffset.UTC)
                                - start.toEpochSecond(ZoneOffset.UTC)) % interval)) + interval, current.getNano(), ZoneOffset.UTC);
                if (!nextDate.isAfter(end)) {
                    return nextDate;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                getTitle().equals(task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTime(), start, end, interval, isActive());
    }
}
