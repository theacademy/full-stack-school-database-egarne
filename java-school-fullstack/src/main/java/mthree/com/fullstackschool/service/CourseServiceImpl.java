package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.CourseDaoImpl;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        List<Course> courses = courseDao.getAllCourses();
        return courses;

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

        Course course = courseDao.findCourseById(id);
        return course;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        if(courseDao.findCourseById(course.getCourseId()) != null) {
            throw new IllegalArgumentException("ERROR: Could not create new because Course with Id: "
                    + course.getCourseId() + " already exists.");
        }
        Course newCourse = courseDao.createNewCourse(course);
        return newCourse;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        Course existingCourse = courseDao.findCourseById(id);
        if(existingCourse == null) {
            throw new IllegalArgumentException("ERROR: Could not update because Course with Id: "
                    + id + " does not exist.");
        }
        course.setCourseId(id);
        courseDao.updateCourse(course);
        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        Course removedCourse = courseDao.findCourseById(id);
        if(removedCourse == null) {
            throw new IllegalArgumentException("ERROR: Could not delete because Course with Id: "
                    + id + " does not exist.");
        }
        courseDao.deleteAllStudentsFromCourse(id);
        courseDao.deleteCourse(id);

        //YOUR CODE ENDS HERE
    }
}
