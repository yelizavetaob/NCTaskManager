package ua.edu.sumdu.j2se.obolonska.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        try{
            outputStream.writeInt(tasks.size());
            Iterator<Task> taskIterator = tasks.iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
            if (task.isRepeated()) {
                outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
            } else {
                outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
            }
        }
    } finally {
            outputStream.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        try {
            int size = inputStream.readInt();
            for (; size > 0; size--) {
                Task task;
                int titleLength = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                if (interval > 0) {
                    LocalDateTime start = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
            }
        }finally {
            inputStream.close();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(tasks, fileOutputStream);
        }finally {
            fileOutputStream.close();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws  IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            read(tasks, fileInputStream);
        }finally {
            fileInputStream.close();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gWriter = new Gson();
        try {
            gWriter.toJson(tasks, out);
        } finally {
            out.close();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson gReader = new Gson();
        try {
            AbstractTaskList currentTask = gReader.fromJson(in, tasks.getClass());
            for (Task task : currentTask) {
                tasks.add(task);
            }
        } finally {
            in.close();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(tasks, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            read(tasks, fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }
}