package Actv2;

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
        System.out.println("\nTarea añadida correctamente ");
    }

    public boolean isTaskPending(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name) && task.isPending()) {
                return true;
            }
        }
        return false;
    }

    public int countPendingTasks() {
        int count = 0;
        for (Task task : tasks) {
            if (task.isPending()) {
                count++;
            }
        }
        return count;
    }

    public void removeTask(String name) {
        tasks.removeIf(task -> task.getName().equalsIgnoreCase(name));
        System.out.println("Tarea eliminada: " + name);
    }

    public void printPendingTasks() {
        System.out.println("Tareas pendientes:");
        for (Task task : tasks) {
            if (task.isPending()) {
                System.out.println("------------------------------------");
                System.out.println(task);
                System.out.println("------------------------------------");
            }
        }
    }

    public void clearTasks() {
        tasks.clear();
        System.out.println("Se eliminaron todas las tareas");
    }

    public void toggleTaskStatus(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.toggleStatus();
                System.out.println("Estatus de la tarea " + name + " ahora se encuentra" + (task.isPending() ? "Pendiente" : "Hecho"));
                return;
            }
        }
        System.out.println("No esta la tarea " + name);
    }

    public void saveTasksToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(filename))) 
            {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            System.out.println("Guardado correctamente " + filename);
        } catch (IOException e) {
            System.out.println("Skibidi fallo chin ;c " + e.getMessage());
        }
    }
}