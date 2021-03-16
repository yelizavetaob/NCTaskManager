package ua.edu.sumdu.j2se.obolonska.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ChangeTask {
    Scanner scanner = new Scanner(System.in);

    public String changeTitle(){
        System.out.println("Введите новое название: ");
        String title = scanner.nextLine();

        return title;
    }

    public LocalDateTime changeTime() {
        System.out.println("Введите новое время: ");
        String scnTime = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.UK);
        LocalDateTime time = LocalDateTime.parse(scnTime, formatter);

        return time;
    }

    public int countOfIntervals(){
        System.out.println("Введите количесто повторений: ");
        int countOfIntervals = scanner.nextInt();
        return countOfIntervals;
    }

    public LocalDateTime changeStart(){

        System.out.println("Начало: ");
        String scnStart = scanner.nextLine();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.UK);
        LocalDateTime start = LocalDateTime.parse(scnStart, formatter1);

        return start;
    }

    public LocalDateTime changeEnd(){
        System.out.println("Конец: ");
        String scnEnd = scanner.nextLine();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.UK);
        LocalDateTime end = LocalDateTime.parse(scnEnd, formatter2);

        return end;
    }
}


