package com.smoothstack.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smoothstack.jdbc.daoImpl.SchoolUpdateDao;
import com.smoothstack.jdbc.daoImpl.StudentUpdateDao;
import com.smoothstack.jdbc.model.School;
import com.smoothstack.jdbc.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SchoolStudentDaoTest {
    private Connection connection;
    private SchoolUpdateDao schoolDao;
    private StudentUpdateDao studentDao;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL", "sa", "");
        connection.createStatement().execute("CREATE TABLE School (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");
        connection.createStatement().execute("CREATE TABLE Student (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), schoolId INT, FOREIGN KEY (schoolId) REFERENCES School(id))");
        schoolDao = new SchoolUpdateDao(connection);
        studentDao = new StudentUpdateDao(connection);
    }

    @Test
    public void testInsertSchoolAndStudents() throws SQLException {
        School school = new School(0, "Green Oaks High School");
        schoolDao.insert(school);
        assertNotNull(school.getId());

        studentDao.insert(new Student("Bob Anderson", school.getId()));
        studentDao.insert(new Student("Janet Collins", school.getId()));

        List<Student> students = studentDao.getAll();
        assertEquals(2, students.size());
    }

    @After
    public void tearDown() throws SQLException {
        connection.createStatement().execute("DROP TABLE Student");
        connection.createStatement().execute("DROP TABLE School");
        connection.close();
    }
}
