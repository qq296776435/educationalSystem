package org.edu.educationalsystem.service;

import org.edu.educationalsystem.DAO.StudentMapper;
import org.edu.educationalsystem.Domain.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImplement implements LoginService  {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student login(Student student) {
        return studentMapper.selectByID(student);
    }
}
