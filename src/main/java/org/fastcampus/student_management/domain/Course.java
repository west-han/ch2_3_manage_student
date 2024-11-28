package org.fastcampus.student_management.domain;

public class Course {
  private final Student student;
  private final String courseName;
  private int fee;
  private final DayOfWeek dayOfWeek;
  private final Long courseTime;

  public Course(Student student, String courseName, int fee, DayOfWeek dayOfWeek, Long courseTime) {
    if (student == null) {
      throw new IllegalArgumentException("학생은 필수 입력값입니다.");
    }

    this.student = student;
    this.courseName = courseName;
    this.fee = fee;
    this.dayOfWeek = dayOfWeek;
    this.courseTime = courseTime;
  }

  public String getCourseName() {
    return courseName;
  }

  public boolean isSameDay(DayOfWeek dayOfWeek) {
    return this.dayOfWeek.equals(dayOfWeek);
  }

  public boolean isActivateUser() {
    return student.isActivate();
  }

  public String getStudentName() {
    return student.getName();
  }

  public int getFee() {
    return fee;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public Long getCourseTime() {
    return courseTime;
  }

  public void setFee(int fee) {
    this.fee = fee;
  }
}
