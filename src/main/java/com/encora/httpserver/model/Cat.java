package com.encora.httpserver.model;


public class Cat {
    private Long id;
    private String name;
    private String breed;

    public Cat() {}

    public Cat(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.breed = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
