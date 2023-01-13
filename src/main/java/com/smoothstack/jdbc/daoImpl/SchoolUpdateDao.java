package com.smoothstack.jdbc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.jdbc.dao.Dao;
import com.smoothstack.jdbc.model.School;

public class SchoolUpdateDao implements Dao<School> {

	private final Connection connection;

    public SchoolUpdateDao(Connection connection) {
        this.connection = connection;
    }
	
    
    public void insert(School school) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO School (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, school.getName());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            school.setId(generatedKeys.getInt(1));
        }
    }

	 public void update(School school) throws SQLException {
	        PreparedStatement statement = connection.prepareStatement("UPDATE School SET name = ? WHERE id = ?");
	        statement.setString(1, school.getName());
	        statement.setInt(2, school.getId());
	        statement.executeUpdate();
	    }


	 public void delete(int id) throws SQLException {
	        PreparedStatement statement = connection.prepareStatement("DELETE FROM School WHERE id = ?");
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    }

	 public School getById(int id) throws SQLException {
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM School WHERE id = ?");
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int schoolId = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            return new School(schoolId, name);
	        } else {
	            return null;
	        }
	    }

	 public List<School> getAll() throws SQLException {
	        PreparedStatement statement = connection.prepareStatement("SELECT * FROM School");
	        ResultSet resultSet = statement.executeQuery();
	        List<School> schools = new ArrayList<School>();
	        while (resultSet.next()) {
	            int schoolId = resultSet.getInt("id");
	        }
			return schools;
	 }
}
