package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.CourseMapper;
import mthree.com.fullstackschool.model.Course;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.sql.*;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_COURSE = "INSERT INTO course(courseName, courseDesc, teacherId) VALUES(?,?,?);";

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    INSERT_COURSE,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseDesc());
            statement.setInt(3, course.getTeacherId());
            return statement;
        }, keyHolder);

        course.setCourseId(keyHolder.getKey().intValue());
        return course;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        final String SELECT_ALL_COURSES = "SELECT * FROM course;";

        return jdbcTemplate.query(SELECT_ALL_COURSES, new CourseMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE

        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE cid = ?;";
            return jdbcTemplate.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);

        } catch(DataAccessException ex) {
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE

        final String UPDATE_COURSE = "UPDATE course SET " +
                "courseName = ?, " +
                "courseDesc = ?, " +
                "tearcherId = ? " +
                "WHERE cid = ?;";
        jdbcTemplate.update(UPDATE_COURSE,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId(),
                course.getCourseId() > 0);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE

        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE course_id = ?;";
        jdbcTemplate.update(DELETE_COURSE_STUDENT, id);

        final String DELETE_COURSE = "DELETE FROM course WHERE cid = ?;";
        jdbcTemplate.update(DELETE_COURSE, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE

        final String DELETE_ALL_STUDENTS = "DELETE FROM course_student WHERE course_id = ?;";
        jdbcTemplate.update(DELETE_ALL_STUDENTS, courseId);

        //YOUR CODE ENDS HERE
    }
}
