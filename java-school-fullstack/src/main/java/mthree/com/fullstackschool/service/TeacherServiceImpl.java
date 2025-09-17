package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao){
        this.teacherDao = teacherDao;
    }

    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE


            return teacherDao.findTeacherById(id);

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE


        if(teacherDao.findTeacherById(teacher.getTeacherId()) != null) {
            throw new IllegalArgumentException("ERROR: Could not create new because Teacher with Id: "
                    + teacher.getTeacherId() + " already exists.");
        }
        return teacherDao.createNewTeacher(teacher);

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE

        Teacher existingTeacher = teacherDao.findTeacherById(id);
        if(existingTeacher == null) {
            throw new IllegalArgumentException("ERROR: Could not update because Teacher with Id: "
                    + id + " does not exist.");
        }
        teacher.setTeacherId(id);
        teacherDao.updateTeacher(teacher);
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        Teacher removed = teacherDao.findTeacherById(id);
        if(removed == null){
            throw new IllegalArgumentException("ERROR: Could not delete because Teacher with Id: "
                    + id + " does not exist.");
        }
        teacherDao.deleteTeacher(id);


        //YOUR CODE ENDS HERE
    }
}
