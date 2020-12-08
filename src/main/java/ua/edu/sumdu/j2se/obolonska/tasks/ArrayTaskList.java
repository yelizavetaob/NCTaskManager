package ua.edu.sumdu.j2se.obolonska.tasks;
import java.util.Arrays;

public class ArrayTaskList {
    private int countOfTasks = 0;
    private Task[] arrayTaskList = new Task[10];

    public void add(Task task) {
        arrayTaskList[countOfTasks] = task;
        countOfTasks++;
        if (countOfTasks == arrayTaskList.length) {
            arrayTaskList = Arrays.copyOf(arrayTaskList, arrayTaskList.length*2);
        }
    }

    public boolean remove(Task task) {
        boolean stateOfRemove = false;
        int loc;
        for(int i=0; i<countOfTasks; i++){
            if(task.equals(arrayTaskList[i])){
                arrayTaskList[i] = null;
                countOfTasks--;
                loc = i;
                    for (int k = loc; k < countOfTasks; k++){
                        arrayTaskList[k] = arrayTaskList[k + 1];
                    }
                stateOfRemove = true;
            }
        }
        return stateOfRemove;
    }

    public int size(){
        int size = countOfTasks;
        return countOfTasks;
    }

    public Task getTask(int index){
        return arrayTaskList[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayInTime = new ArrayTaskList();
        for (int k = 0; k < countOfTasks; k++) {
            if (getTask(k).nextTimeAfter(from) >= from && getTask(k).nextTimeAfter(from) <= to) {
                arrayInTime.add(arrayTaskList[k]);
            }
        }
        return arrayInTime;
    }
}
