package com.task.taskmanager.repository;

import com.task.taskmanager.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks,Long> {

}
