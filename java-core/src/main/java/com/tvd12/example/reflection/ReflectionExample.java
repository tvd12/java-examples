package com.tvd12.example.reflection;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ReflectionExample {

    public static void main(String[] args) throws Exception {
        System.out.println(Employee.class.getMethod("setId", int.class));
        System.out.println(Employee.class.getDeclaredField("list")
            .getGenericType());
    }

    @Getter
    @Setter
    public static class Employee {

        @ExampleId
        private int id;
        private String firstName, lastName;
        private List<Integer> list;

    }

}
