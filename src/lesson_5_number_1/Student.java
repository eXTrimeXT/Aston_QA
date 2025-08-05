package lesson_5_number_1;

import java.util.List;

public class Student {
    private final String name;
    private final String group;
    private int course;
    private final List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public double getAverageGrade() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public void promoteToNextCourse() {
        this.course++;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return name + " (Группа: " + group + ", Курс: " + course + ")";
    }
}
