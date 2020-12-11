package ua.edu.sumdu.j2se.obolonska.tasks;

import java.util.stream.*;

public abstract class AbstractTaskList implements Iterable<Task>{

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(int from, int to) throws CloneNotSupportedException {
        AbstractTaskList taskList = (AbstractTaskList) this.clone();
        for (int i = 0; i < this.size(); i++) {
            if (this.getTask(i).nextTimeAfter(from) < from && this.getTask(i).nextTimeAfter(from) >= to) {
                taskList.remove(this.getTask(i));
            }
        }
        return taskList;
    }
}

