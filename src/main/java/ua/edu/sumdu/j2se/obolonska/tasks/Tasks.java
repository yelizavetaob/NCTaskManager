package ua.edu.sumdu.j2se.obolonska.tasks;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(@NotNull Iterable<Task> tasks, @NotNull LocalDateTime start, @NotNull LocalDateTime end) {
        LinkedTaskList chosenTasks = new LinkedTaskList();

        for(Task task : tasks) {
            if(task.nextTimeAfter(start) != null && !task.nextTimeAfter(start).isAfter(end)) {
                chosenTasks.add(task);
            }
        }
        return chosenTasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(@NotNull Iterable<Task> tasks, @NotNull LocalDateTime start, @NotNull LocalDateTime end) {
        TreeMap<LocalDateTime, Set<Task>> taskCalendar = new TreeMap<>();

        for (Task task : tasks) {
            for (LocalDateTime key = task.nextTimeAfter(start); key.isBefore(end) || key.isEqual(end); key = task.nextTimeAfter(key)) {
                if (taskCalendar.containsKey(key)) {
                    taskCalendar.get(key).add(task);
                } else {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    taskCalendar.put(key, set);
                }
            }
        }
        return taskCalendar;
    }
}