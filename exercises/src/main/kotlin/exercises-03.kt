package dhbw

import java.util.Optional

sealed class CourseOfStudies(val description: String)

data class Wirtschaftsinformatik(val des: String) : CourseOfStudies(des)
data class Informatik(val des: String) : CourseOfStudies(des)
data class BWL(val des: String) : CourseOfStudies(des)

data class Lecture(val cade: String, val description: String, val creditPoints: Int)

data class Student(val name: String, val matriculationNumber: MatriculationNumber)

data class MatriculationNumber(val digits: IntArray)


data class StudyCourse(
    val description: String,
    val courseOfStudies: CourseOfStudies,
    val lectures: List<Lecture>,
    val students: List<Student>
)


fun StudyCourse.addStudent(student: Student): StudyCourse {
    return this.copy(students = this.students + student)
}

fun StudyCourse.addLecture(lecture: Lecture): StudyCourse {
    return this.copy(lectures = this.lectures + lecture)
}


fun StudyCourse.getLectureWithMostCreditPoints(): Optional<Lecture> {
    val biggest = lectures.maxByOrNull { it.creditPoints }
    return if (biggest != null) Optional.of(biggest) else Optional.empty()
}