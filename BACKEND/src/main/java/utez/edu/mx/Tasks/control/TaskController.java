package utez.edu.mx.Tasks.control;

import utez.edu.mx.Tasks.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.ok("Tarea añadida correctamente");
    }

    @GetMapping("/{name}/pending")
    public ResponseEntity<Boolean> isTaskPending(@PathVariable String name) {
        boolean isPending = taskService.isTaskPending(name);
        return ResponseEntity.ok(isPending);
    }

    @GetMapping("/count/pending")
    public ResponseEntity<Integer> countPendingTasks() {
        int count = taskService.countPendingTasks();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> removeTask(@PathVariable String name) {
        taskService.removeTask(name);
        return ResponseEntity.ok("Tarea eliminada: " + name);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        List<Task> tasks = taskService.getPendingTasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearTasks() {
        taskService.clearTasks();
        return ResponseEntity.ok("Se eliminaron todas las tareas");
    }

    @PostMapping("/{name}/toggle")
    public ResponseEntity<String> toggleTaskStatus(@PathVariable String name) {
        taskService.toggleTaskStatus(name);
        return ResponseEntity.ok("Estatus de la tarea " + name + " cambiado");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTasksToFile(@RequestParam String filename) {
        taskService.saveTasksToFile(filename);
        return ResponseEntity.ok("Tareas guardadas en " + filename);
    }
}
