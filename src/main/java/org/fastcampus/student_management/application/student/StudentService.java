package org.fastcampus.student_management.application.student;

import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

import java.util.NoSuchElementException;

public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void saveStudent(StudentInfoDto studentInfoDto) {
    Student student = new Student(studentInfoDto.getName(), studentInfoDto.getAge(), studentInfoDto.getAddress());
    studentRepository.save(student);
  }

  public Student getStudent(String name) {
    return studentRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));
  }

  public void activateStudent(String name) {
    setActivation(name, true);
  }

  public void deactivateStudent(String name) {
    setActivation(name, false);
  }

  private void setActivation(String name, boolean state) {
    Student student = studentRepository.findByName(name).orElseThrow(() -> new NoSuchElementException());
    boolean b = student.setActivated(state);
    if (b) {
      studentRepository.save(student);
    }
  }
}
