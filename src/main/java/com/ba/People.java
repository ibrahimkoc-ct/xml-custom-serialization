package com.ba;

public class People {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public People(int id, String name, String surname, int age,String job) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.job=job;
    }
}

