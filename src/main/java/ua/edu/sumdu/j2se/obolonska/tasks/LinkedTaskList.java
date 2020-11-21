package ua.edu.sumdu.j2se.obolonska.tasks;

public class LinkedTaskList {
    private Node first;
    private Node tail;
    private int countOfTasks;

    private class Node {
        Node next;
        Task task;
        Node(Task task, Node next) {
            this.task = task;
            this.next = next;
        }
    }

    public void add(Task task){
        Node linkedTasksList = new Node(task, null);
        if(first == null){
            first = linkedTasksList;
            tail = first;
        }else{
            tail.next = linkedTasksList;
            tail = tail.next;
        }
        countOfTasks++;
    }

    public boolean remove(Task task){
        Node prev = null;
        if(task == null){
            return false;
        }
        Node position = first;
        for(; position != null; position = position.next) {
            if (task.equals(position.task)) {
                if (position == first) {
                    first = position.next;
                } else if (position != tail) {
                    prev.next = position.next;
                } else {
                    prev.next = null;
                    tail = prev;
                }
                countOfTasks--;
                return true;
            }
            prev = position;
        }
        return false;
    }

    public int size(){
        return countOfTasks;
    }

    public Task getTask(int index){
        if (index < 0 || index >= countOfTasks) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + countOfTasks);
        }
        Node returnTask = first;
        for (int i = 0; i < index; i++) {
            returnTask = returnTask.next;
        }
        return returnTask.task;
    }

    public LinkedTaskList incoming(int from, int to){
        LinkedTaskList listOfTasks  = new LinkedTaskList();
        for (Node position = first; position != null; position = position.next) {
            if (position.task.nextTimeAfter(from) > from && position.task.nextTimeAfter(from) <= to) {
                listOfTasks.add(position.task);
            }
        }
        return listOfTasks;
    }
}
