package ua.edu.sumdu.j2se.obolonska.tasks;

import java.io.*;


import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static File file = new File("file");
    public static AbstractTaskList tasks = new ArrayTaskList();

    public static void main(String[] args) throws IOException, InterruptedException{
        Scanner scanner = new Scanner(System.in);
        //Exception
        try {
            TaskIO.readText(tasks, file);
        } catch (IOException e) { }

        int endOfProgram;


        TaskTimer timer = new TaskTimer(true) {
            @Override
            public TimerTask TaskT(Task task) {
                TimerTask timerTask = new TimerTask(){

                    @Override
                    public void run() {
                        System.out.println(task.getTitle());
                    }
                };
                return timerTask;
            }
        };


        //Запуск программы
        do {
                StartOfProgram start = new StartOfProgram();
                start.Start();

                System.out.println("Нажмите 0, чтобы закрыть программу");
                endOfProgram = scanner.nextInt();
            } while (endOfProgram != 0) ;


        TaskIO.writeText(tasks, file);
        return;
    }
}


