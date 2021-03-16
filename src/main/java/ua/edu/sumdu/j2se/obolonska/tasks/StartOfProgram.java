package ua.edu.sumdu.j2se.obolonska.tasks;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StartOfProgram {
    Scanner scanner = new Scanner(System.in);

    public void Start() throws IOException {

        int addTask, taskRepeat;
        Main main = new Main();
        //Открыть список задач или добавить новую задачу
        do {
            System.out.println("Добавить новую задачу нажмите 1. Открыть список задач нажмите 2");
            System.out.flush();
            addTask = scanner.nextInt();
        } while (addTask != 1 && addTask != 2);

        //Создать новую задачу
        if (addTask == 1) {

            //do {
            System.out.println("Нажмите 0, если задача не повторяется. Нажмите 1, если задача будет повторяется");
            taskRepeat = scanner.nextInt();
            //} while (taskRepeat != 0 && taskRepeat != 1);

            if (taskRepeat == 0) {
                CreateNewTask task = new CreateNewTask();
                main.tasks.add(task.createTaskNoRepeat());

                TaskIO.writeText(main.tasks, new File("file"));
            } else if (taskRepeat == 1) {
                CreateNewTask task = new CreateNewTask();
                main.tasks.add(task.createTaskRepeat());
                TaskIO.writeText(main.tasks, new File("file"));
            }
        }

        //Внести изменения
        else {
            System.out.println(main.tasks);

            //Внести изменения или вернуться в основное меню
            System.out.println("Нажмите 1, если хотите внести изменения. Нажмите 2, чтобы удалить задачу ");
            int changesTask = scanner.nextInt();

            if (changesTask == 1) {
                Changes changes = new Changes();
                changes.Change();
            } else if (changesTask == 2){
                DeleteTask deleteTask = new DeleteTask();
                deleteTask.DeleteTask();
            }
        }
    }
}
