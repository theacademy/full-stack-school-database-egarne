package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    private final StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;

    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        List<Student> students = studentDao.getAllStudents();
        return students;

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        Student student = studentDao.findStudentById(id);
        return student;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if(studentDao.findStudentById(student.getStudentId()) != null) {
            throw new IllegalArgumentException("ERROR: Could not create new because Student with Id: "
                    + student.getStudentId() + " already exists.");
        }
        Student newStudent = studentDao.createNewStudent(student);
        return newStudent;

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        Student existingStudent = studentDao.findStudentById(id);
        if(existingStudent == null) {
            throw new IllegalArgumentException("ERROR: Could not update because Student with Id: "
                    + id + " does not exist.");
        }
        student.setStudentId(id);
        studentDao.updateStudent(student);

        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        Student removedStudent = studentDao.findStudentById(id);
        if(removedStudent == null) {
            throw new IllegalArgumentException("ERROR: Could not delete because Student with Id: "
                    + id + " does not exist.");
        }
        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student removedStudent = studentDao.findStudentById(studentId);
        if(removedStudent == null) {
            throw new IllegalArgumentException("ERROR: Student with Id "
                    + studentId + " does not exist.");
        }

        Course currentCourse = courseDao.findCourseById(courseId);
        if(currentCourse == null) {
            throw new IllegalArgumentException("ERROR: Course with Id "
                    + courseId + " does not exist.");
        }

        studentDao.deleteStudentFromCourse(studentId, courseId);

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = studentDao.findStudentById(studentId);
        if(student == null) {
            throw new IllegalArgumentException("ERROR: Student with Id "
                    + studentId + " does not exist.");
        }

        Course currentCourse = courseDao.findCourseById(courseId);
        if(currentCourse == null) {
            throw new IllegalArgumentException("ERROR: Course with Id "
                    + courseId + " does not exist.");
        }

        studentDao.addStudentToCourse(studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
