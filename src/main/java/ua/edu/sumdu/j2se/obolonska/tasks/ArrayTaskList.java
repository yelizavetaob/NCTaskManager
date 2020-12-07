package ua.edu.sumdu.j2se.obolonska.tasks;
import java.util.Arrays;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList implements Cloneable{
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
        for (int i = 0; i < countOfTasks; i++) {
            if (getTask(i).nextTimeAfter(from) >= from && getTask(i).nextTimeAfter(from) <= to) {
                arrayInTime.add(arrayTaskList[i]);
            }
        }
        return arrayInTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return countOfTasks == that.countOfTasks &&
                Arrays.equals(arrayTaskList, that.arrayTaskList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countOfTasks);
        result = 31 * result + Arrays.hashCode(arrayTaskList);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "countOfTasks=" + countOfTasks +
                ", arrayTaskList=" + Arrays.toString(arrayTaskList) +
                '}';
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException{
        ArrayTaskList copyArray = (ArrayTaskList) super.clone();
        arrayTaskList = arrayTaskList.clone();
        return copyArray;
    }
}
