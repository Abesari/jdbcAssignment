package com.smoothstack.jdbc.model;

public class Student {
    private int id;
    private String name;
    private int schoolId;

    public Student(int id, String name, int schoolId) {
        this.id = id;
        this.name = name;
        this.schoolId = schoolId;
    }

    public Student(String string, int id2) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
