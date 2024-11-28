package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {

    List<Course> courses = courseRepository.getCourseDayOfWeek(dayOfWeek);

    List<CourseInfoDto> list = new ArrayList<>();
    list = courses.stream().map(course -> {
      return new CourseInfoDto(
              course.getCourseName(),
              course.getFee(),
              course.getDayOfWeek().name(),
              course.getStudentName(),
              course.getCourseTime()
      );
    }).toList();

    return list;
  }

  public void changeFee(String studentName, int fee) {
    List<Course> courseList = courseRepository.getCourseListByStudent(studentName);
    for (Course course : courseList) {
      course.setFee(fee);
    }
    courseRepository.saveCourses(courseList);
  }
}
