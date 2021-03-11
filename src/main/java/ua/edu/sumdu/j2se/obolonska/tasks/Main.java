package ua.edu.sumdu.j2se.obolonska.tasks;

import java.io.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
   public static File file = new File("file");
   public static AbstractTaskList tasks = new ArrayTaskList();

    public static void main(String[] args) throws IOException{
        Scanner scn = new Scanner(System.in);
        try{
            TaskIO.readText(tasks, file);
        }catch (IOException e){};

        int addTask, choose, chs;
        double endP;
       do{
            do {
                System.out.println("Add new task - 1. Open list of tasks - 2.");
                addTask = scn.nextInt();
            } while (addTask != 1 && addTask != 2);

            //Create new task:
            if (addTask == 1) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите название: ");
                String title = sc.nextLine();

                System.out.println("If task repeat - 1, else print something else number");
                int taskRepeat = sc.nextInt();
                if (taskRepeat != 1) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Назначте время: ");
                    String str = scan.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    LocalDateTime time = LocalDateTime.parse(str, formatter);

                    Task task= new Task(title, time);
                    System.out.println(task.getTitle() + " " + task.getTime());

                    tasks.add(task);
                    TaskIO.writeText(tasks, new File("file"));
                }
                else {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Начало: ");
                    String str1 = scan.nextLine();
                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    LocalDateTime start = LocalDateTime.parse(str1, formatter1);

                    System.out.println("Конец: ");
                    String str2 = scan.nextLine();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    LocalDateTime end = LocalDateTime.parse(str2, formatter2);

                    System.out.println("Введите количесто повторений: ");
                    int countOfIntervals = scan.nextInt();

                    Task task = new Task(title, start, end, countOfIntervals);
                    System.out.println(task.getTitle() + " начнется в " + task.getStartTime() + " закончится в " + task.getEndTime() + ". Количество повторений: " + task.getRepeatInterval());

                    tasks.add(task);
                }
            }
            else {
                System.out.println(tasks);

                //Choose task
                System.out.println("Введите номер задачи, которую хотите изменить");
                int indexChangeTask = scn.nextInt();
                Task changeTask = tasks.getTask(indexChangeTask - 1);

                    do {
                    System.out.println("Delete - 0. Change - 1");
                    choose = scn.nextInt();
                    } while (choose != 0 && choose != 1);

                    if (choose == 0) {
                    //delete
                    System.out.println("Task to delete: ");
                    int indexDelTask = scn.nextInt();
                    Task remTask = tasks.getTask(indexDelTask - 1);
                    tasks.remove(remTask);
                } else {
                    do {
                        System.out.println("Time - 0. Title - 1.");
                        chs = scn.nextInt();
                    } while (chs != 0 && chs != 1);

                    if (chs == 1) {
                        System.out.println("Введите новое название: ");
                        System.out.flush();
                        String title = scn.next();
                        changeTask.setTitle(title);
                    }
                    else {
                        System.out.println("Введите новое время: ");
                        System.out.flush();
                        String scnTime = scn.next();
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                        //LocalDateTime time = LocalDateTime.parse(scnTime, formatter);
                        LocalDateTime time = LocalDateTime.parse("24.08.2022 12:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US));
                        changeTask.setTime(time);
                    }
                }
            }
            System.out.println("Print end");
            System.out.flush();
            endP = scn.nextDouble();
        } while (endP != 0);

        TaskIO.writeText(tasks, file);
        return;
    }
}


