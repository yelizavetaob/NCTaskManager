package ua.edu.sumdu.j2se.obolonska.tasks;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateNewTask {
    Scanner scanner = new Scanner(System.in);

    public Task createTaskNoRepeat(){
        System.out.println("Введите название: ");
        String title = scanner.nextLine();

        System.out.println("Назначте время: ");
        String str = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime time = LocalDateTime.parse(str, formatter);

        Task task = new Task(title, time);
        return task;
    }

    public Task createTaskRepeat(){
        System.out.println("Введите название: ");
        String title = scanner.nextLine();
        System.out.println("Начало: ");
        String str1 = scanner.nextLine();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse(str1, formatter1);

        System.out.println("Конец: ");
        String str2 = scanner.nextLine();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime end = LocalDateTime.parse(str2, formatter2);

        System.out.println("Введите количесто повторений: ");
        int countOfIntervals = scanner.nextInt();

        Task task = new Task(title, start, end, countOfIntervals);
        return task;
    }
}

