package com.hariz.simplecrud.model;

public class Student {
    private String id, name, email;
    private String matricnum;

    public Student(){

    }

    public Student(String id, String name, String email, String matricnum){
        this.id = id;
        this.name = name;
        this.email = email;
        this.matricnum = matricnum;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricnum() {
        return matricnum;
    }

    public void setMatricnum(String matricnum) {
        this.matricnum = matricnum;
    }
}
