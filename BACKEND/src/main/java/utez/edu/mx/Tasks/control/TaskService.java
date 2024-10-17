package utez.edu.mx.Tasks.control;

import utez.edu.mx.Tasks.model.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final Manager manager = new Manager();

    public void addTask(Task task) {
        manager.addTask(task);
    }

    public boolean isTaskPending(String name) {
        return manager.isTaskPending(name);
    }

    public int countPendingTasks() {
        return manager.countPendingTasks();
    }

    public void removeTask(String name) {
        manager.removeTask(name);
    }

    public List<Task> getPendingTasks() {
        return manager.getPendingTasks();
    }

    public List<Task> getTasks() {
        return manager.getTasks();
    }

    public void clearTasks() {
        manager.clearTasks();
    }

    public void toggleTaskStatus(String name) {
        manager.toggleTaskStatus(name);
    }

    public void saveTasksToFile(String filename) {
        manager.saveTasksToFile(filename);
    }
}
