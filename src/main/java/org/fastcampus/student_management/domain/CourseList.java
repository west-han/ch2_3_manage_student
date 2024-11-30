package org.fastcampus.student_management.domain;

import java.util.List;

public class CourseList {
    private final List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public void changeAllCoursesFee(int fee) {
        courses.forEach(course -> {
            if (course.isSameDay(DayOfWeek.SATURDAY) || course.isSameDay(DayOfWeek.SUNDAY)) {
                course.changeFee((int) (fee * 1.5));
            } else {
                course.changeFee(fee);
            }
        });
    }
}
