package org.edu.educationalsystem.service;

import org.edu.educationalsystem.Domain.Instructor;
import org.edu.educationalsystem.Domain.Student;

public interface LoginService {
    Student login(Student student);
    Instructor login(Instructor instructor);
}
