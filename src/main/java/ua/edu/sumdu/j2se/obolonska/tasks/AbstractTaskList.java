package ua.edu.sumdu.j2se.obolonska.tasks;

import java.util.stream.*;

public abstract class AbstractTaskList implements Iterable<Task>{

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);
    
    public abstract ListTypes.types getType();

    public abstract Stream<Task> getStream();
    
    final public AbstractTaskList incoming(int from, int to) {
           AbstractTaskList taskList = TaskListFactory.createTaskList(this.getType());
           getStream().forEach(task -> {
               if(task.nextTimeAfter(from) <= to){
                   remove(task);
               }
           });
           return taskList;
      }
}

