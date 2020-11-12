package ua.edu.sumdu.j2se.obolonska.tasks;

import java.util.Objects;

public class Task {
    /**Title is the title of a task*/
    private String title;
    /**Time is the time of a task that is executed 1 time (Fom 01.02.2000)*
     * Start is the beginning of a repeating task
     * End is the end of a repetitive task
     * Interval is the duration of one task
     */
    private int time, start, end, interval;
    /**Define an active or a non-active task*/
    private boolean active;
    /**Constructor for creating a task that does not repeat */
    public Task(final String title, final int time) {
        this.title = title;
        this.time = time;
    }
    /**Constructor for creating a task that repeats */
    public Task(final String title, final int start, final int end, final int interval) {
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
     * or return start if the task is repeated*/
    public int getTime() {
        return interval != 0 ? start : time;
    }
    /**@param time execution of non-repeated task to set,
     * if task is repeated then task is transforming to non-repeated*/
    public void setTime(final int time) {
        this.time = time;
        this.start = 0;
        this.end = 0;
        this.interval = 0;
    }
    /**@return start time of interval of repeated task,
     *if task is non-repeated then returning execution time*/
    public int getStartTime() {
        return start != 0 ? start : time;
    }
    /**@return end time of interval of repeated task,
     *if task is non-repeated then returning execution time */
    public int getEndTime() {
        return end != 0 ? end : time;
    }
    /**@return interval if the task is repeating and
     * 0 if the task is not repeating*/
    public int getRepeatInterval() {
        return interval != 0 ? interval : 0;
    }
    /**@param start is the beginning of execution,
     * @param end is the end of execution,
     * @param interval is the period of running a repeating task to set
     * if the task is not repeated, then the task goes to the repeated one*/
    public void setTime(final int start, final int end, final int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = 0;
    }
    /**@return task is repeated or non-repeated*/
    public boolean isRepeated() {
        return interval != 0;
    }
    /**@return the current closest time of the active task*/
    public int nextTimeAfter(int current){
        int nextInterval;
        if(active){
            if(time != 0 && current < time){
                return time;
            }
            if(start != 0 && current < start) {
                return start;
            }
            if(start != 0 && current >= start){
                nextInterval = (current - (current - start) % interval) + interval;
                if (nextInterval < end) {
                    return nextInterval;
                }
            }
        }
        return -1;
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


