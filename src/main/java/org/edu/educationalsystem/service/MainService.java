package org.edu.educationalsystem.service;

import org.edu.educationalsystem.Domain.Course;
import org.edu.educationalsystem.Domain.Instructor;
import org.edu.educationalsystem.Domain.Section;
import org.edu.educationalsystem.Domain.Takes;

import java.util.List;

public interface MainService {
    List<Takes> getTakes(String ID);
    List<Instructor> getAllInstructors();
}
