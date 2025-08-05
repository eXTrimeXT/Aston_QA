package lesson_5_number_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void promoteSuccessfulStudents(Set<Student> students) {
        students.forEach(student -> {
            if (student.getAverageGrade() >= 3) {
                student.promoteToNextCourse();
            }
        });
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        students.stream()
                .filter(student -> student.getCourse() == course)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван Иванов", "ГР1", 1, Arrays.asList(4, 5, 3)));
        students.add(new Student("Петр Петров", "ГР2", 1, Arrays.asList(2, 3, 2)));
        students.add(new Student("Сидор Сидоров", "ГР1", 2, Arrays.asList(5, 5, 5)));

        System.out.println("Все студенты:");
        students.forEach(System.out::println);

        removeUnderperformingStudents(students);
        System.out.println("\nПосле удаления студентов с средним баллом < 3:");
        students.forEach(System.out::println);

        promoteSuccessfulStudents(students);
        System.out.println("\nПосле перевода успешных студентов:");
        students.forEach(System.out::println);

        System.out.println();
        printStudents(students, 2);
    }
}
