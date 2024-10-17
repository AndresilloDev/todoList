package utez.edu.mx.Tasks.control;

import utez.edu.mx.Tasks.model.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Manager {
    private LinkedList<Task> tasks;

    public Manager() {
        tasks = new LinkedList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean isTaskPending(String name) {
        return tasks.stream().anyMatch(task -> task.getName().equalsIgnoreCase(name) && task.isPending());
    }

    public int countPendingTasks() {
        return (int) tasks.stream().filter(Task::isPending).count();
    }

    public void removeTask(String name) {
        tasks.removeIf(task -> task.getName().equalsIgnoreCase(name));
    }

    public LinkedList<Task> getPendingTasks() {
        return new LinkedList<>(tasks.stream().filter(Task::isPending).toList());
    }

    public LinkedList<Task> getTasks() {
        return new LinkedList<>(tasks);
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void toggleTaskStatus(String name) {
        tasks.stream()
                .filter(task -> task.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(Task::toggleStatus);
    }

    public void saveTasksToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
