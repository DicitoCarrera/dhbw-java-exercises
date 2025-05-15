
import java.util.*;
import java.util.stream.*;

import java.util.List;

class Main {
    public static void main(String[] args) {

        var cs = new Informatik("Software Engineering");

        var course = new StudyCourse("CS 2025", cs, List.of(), List.of());

        course = course
                .addLecture(new Lecture("PRG", "Programming", 5))
                .addLecture(new Lecture("DBS", "Databases", 6))
                .addStudent(new Student("Alice", new MatriculationNumber(new int[]{1, 2, 3, 4})));

        course.printCourseType();

        course.getLectureWithMostCreditPoints()
                .ifPresent(l -> System.out.println("Top Lecture: " + l.description()));
    }
}

sealed interface CourseOfStudies permits Wirtschaftsinformatik, Informatik, BWL {
    String description();
}

record Wirtschaftsinformatik(String description) implements CourseOfStudies {
}

record Informatik(String description) implements CourseOfStudies {
}

record BWL(String description) implements CourseOfStudies {
}


record Lecture(String code, String description, int creditPoints) {
}


record MatriculationNumber(int[] digits) {
}


record Student(String name, MatriculationNumber matriculationNumber) {
}

record StudyCourse(
        String description,
        CourseOfStudies courseOfStudies,
        List<Lecture> lectures,
        List<Student> students
) {

    public StudyCourse addStudent(Student student) {
        return new StudyCourse(
                description,
                courseOfStudies,
                lectures,
                Stream.concat(students.stream(), Stream.of(student)).toList()
        );
    }

    public StudyCourse addLecture(Lecture lecture) {
        return new StudyCourse(
                description,
                courseOfStudies,
                Stream.concat(lectures.stream(), Stream.of(lecture)).toList(),
                students
        );
    }

    public Optional<Lecture> getLectureWithMostCreditPoints() {
        return lectures.stream().max(Comparator.comparingInt(Lecture::creditPoints));
    }

    public void printCourseType() {
        switch (courseOfStudies) {
            case Wirtschaftsinformatik wi -> System.out.println("WI: " + wi.description());
            case Informatik inf -> System.out.println("INF: " + inf.description());
            case BWL bwl -> System.out.println("BWL: " + bwl.description());
        }
    }
}