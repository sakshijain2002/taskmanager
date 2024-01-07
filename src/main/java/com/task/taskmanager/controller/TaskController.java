package com.task.taskmanager.controller;

import com.task.taskmanager.entity.Tasks;
import com.task.taskmanager.exception.ResourceNotFoundException;
import com.task.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    @GetMapping
    public List<Tasks> getAllTasks(){
        return taskRepository.findAll();
        }


    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return ResponseEntity.ok(task);
    }

    // Create a new task
    @PostMapping("/saveTask")
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
        try {
            Tasks savedTask = taskRepository.save(task);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long id, @RequestBody Tasks taskDetails) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        Tasks updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}
