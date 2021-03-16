package ua.edu.sumdu.j2se.obolonska.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public abstract class TaskTimer extends Timer {
    private final HashMap<Task, TimerTask> taskMap = new HashMap<>();

    public TaskTimer(){}

    public TaskTimer(boolean b) {
        super(b);
    }

    public void add(Task task) {
        if (task.nextTimeAfter(LocalDateTime.now()) == null) {
            return;
        }
        TimerTask timerTask = TaskT(task);

        taskMap.put(task, timerTask);
        if (task.isRepeated()) {
            this.scheduleAtFixedRate(timerTask,
                    Date.from(task.nextTimeAfter(LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant()),
                    task.getRepeatInterval() * 1000L);
        } else {
            this.schedule(timerTask,
                    Date.from(task.getStartTime().atZone(ZoneId.systemDefault()).toInstant()));
        }
    }

    public void remove(Task t) {
        TimerTask remT = taskMap.get(t);
        if (remT != null){
            remT.cancel();
            this.purge();
        }
    }

    public abstract TimerTask TaskT(Task task);

}