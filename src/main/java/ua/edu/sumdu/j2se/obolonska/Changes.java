package ua.edu.sumdu.j2se.obolonska.tasks;

import java.util.Scanner;

public class Changes {
    Scanner scanner = new Scanner(System.in);

    public void Change() {
        Main main = new Main();

        System.out.println("Введите номер задачи, которую хотите изменить: ");
        int indexChooseTask = scanner.nextInt();
        Task chooseTask = main.tasks.getTask(indexChooseTask - 1);

        ChangeTask changeTask = new ChangeTask();
        System.out.println("Нажмите 1, если хотите изменить имя. Нажмите 2, если хотите изменить время или к-во повторений");
        int taskChange = scanner.nextInt();

        if (taskChange == 1) {
            //сменить название
            chooseTask.setTitle(changeTask.changeTitle());
        } else {
            //Задача повторяется
            if (chooseTask.isRepeated()) {
                System.out.println("Нажмите 1, если хотите изменить к-в повторений");
                int changeCountOfIntervals = scanner.nextInt();

                if (changeCountOfIntervals == 1) {
                    int countOfIntervals = changeTask.countOfIntervals();

                    if(countOfIntervals > 0){
                        System.out.println("Нажмите 1, если хотите изменить начало. Нажмите 2, если хотите изменить окончание. Нажмите 3, если хотите изменить начало и окончание.");
                        int changeTime = scanner.nextInt();

                        if (changeTime == 1) {
                            chooseTask.setTime(changeTask.changeStart(), chooseTask.getEndTime(), countOfIntervals);
                        } else if (changeTime == 2) {
                            chooseTask.setTime(changeTask.changeStart(), changeTask.changeEnd(), countOfIntervals);
                        } else if (changeTime == 3) {
                            chooseTask.setTime(chooseTask.getStartTime(), changeTask.changeEnd(), countOfIntervals);
                        }
                    } else {
                        System.out.println("Нажмите 1, если хотите изменить время начала");
                        int changeTime = scanner.nextInt();

                        if (changeTime == 1) {
                            chooseTask.setTime(changeTask.changeTime());
                        } else {
                            chooseTask.setTime(chooseTask.getStartTime());
                        }
                    }
                } else {
                    System.out.println("Нажмите 1, если хотите изменить начало. Нажмите 2, если хотите изменить окончание. Нажмите 3, если хотите изменить начало и окончание.");
                    int changeTime = scanner.nextInt();

                    if (changeTime == 1) {
                        chooseTask.setTime(changeTask.changeStart(), chooseTask.getEndTime(), chooseTask.getRepeatInterval());
                    } else if (changeTime == 2) {
                        chooseTask.setTime(changeTask.changeStart(), changeTask.changeEnd(), chooseTask.getRepeatInterval());
                    } else if (changeTime == 3) {
                        chooseTask.setTime(chooseTask.getStartTime(), changeTask.changeEnd(), chooseTask.getRepeatInterval());
                    }

                }
            } else {
                System.out.println("Нажмите 1, если хотите изменить к-в повторений");
                int changeCountOfIntervals = scanner.nextInt();

                if (changeCountOfIntervals == 1) {
                    System.out.println("Нажмите 1, если хотите изменить время начала");
                    int changeTime = scanner.nextInt();

                    if (changeTime == 1) {
                        chooseTask.setTime(chooseTask.getStartTime(), changeTask.changeEnd(), changeTask.countOfIntervals());
                    } else {
                        chooseTask.setTime(chooseTask.getTime(), changeTask.changeEnd(), changeTask.countOfIntervals());
                    }
                } else {
                    chooseTask.setTime(changeTask.changeTime());
                }
            }
        }
    }
}

