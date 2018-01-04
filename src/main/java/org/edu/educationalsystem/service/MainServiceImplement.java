package org.edu.educationalsystem.service;

import org.edu.educationalsystem.DAO.CourseMapper;
import org.edu.educationalsystem.DAO.InstructorMapper;
import org.edu.educationalsystem.DAO.SectionMapper;
import org.edu.educationalsystem.DAO.TakesMapper;
import org.edu.educationalsystem.Domain.Course;
import org.edu.educationalsystem.Domain.Instructor;
import org.edu.educationalsystem.Domain.Section;
import org.edu.educationalsystem.Domain.Takes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MainServiceImplement implements MainService {
    @Resource
    private TakesMapper takesMapper;
    @Resource
    private InstructorMapper instructorMapper;
    @Override
    public List<Takes> getTakes(String ID) {
        return takesMapper.selectByID(ID);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorMapper.selectAll();
    }
}
