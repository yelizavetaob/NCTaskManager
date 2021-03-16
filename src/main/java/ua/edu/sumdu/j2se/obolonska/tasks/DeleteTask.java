package ua.edu.sumdu.j2se.obolonska.tasks;

import java.util.Scanner;

public class DeleteTask {
    Scanner scanner = new Scanner(System.in);

    Main main = new Main();
    TaskTimer timer;

    public void DeleteTask(){
        System.out.println("Введите номер задачи, которую хотите изменить: ");
        int indexChooseTask = scanner.nextInt();
        Task chooseTask = main.tasks.getTask(indexChooseTask - 1);
        main.tasks.remove(chooseTask);
        timer.remove(chooseTask);
    }
}

