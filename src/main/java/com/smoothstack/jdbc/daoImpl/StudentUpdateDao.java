package com.smoothstack.jdbc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.jdbc.dao.Dao;
import com.smoothstack.jdbc.model.Student;

public class StudentUpdateDao implements Dao<Student> {
    private final Connection connection;

    public StudentUpdateDao(Connection connection) {
        this.connection = connection;
    }

    
    public void insert(Student student) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Student (name, schoolId) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getName());
        statement.setInt(2, student.getSchoolId());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            student.setId(generatedKeys.getInt(1));
        }
    }

    
    public void update(Student student) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Student SET name = ?, schoolId = ? WHERE id = ?");
        statement.setString(1, student.getName());
        statement.setInt(2, student.getSchoolId());
        statement.setInt(3, student.getId());
        statement.executeUpdate();
    }

    
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Student WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    
    public Student getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Student WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int studentId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int schoolId = resultSet.getInt("schoolId");
            return new Student(studentId, name, schoolId);
        } else {
            return null;
        }
    }

    
    public List<Student> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Student");
        ResultSet resultSet = statement.executeQuery();
        List<Student> students = new ArrayList<Student>();
        while (resultSet.next()) {
            int studentId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int schoolId = resultSet.getInt("schoolId");
            students.add(new Student(studentId, name, schoolId));
        }
        return students;
    }
}

