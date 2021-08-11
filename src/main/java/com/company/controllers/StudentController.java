package com.company.controllers;

import com.company.dbhelper.DbConnection;
import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentController {

    // Initialize the scanner
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewStudent() {

        // Prompt the user for data.
        System.out.print("Enter the name of the student: ");
        String name = scanner.next();

        System.out.print("Enter the age of the student: ");
        int age = scanner.nextInt();

        try {
            // INSERT INTO students(name, age) VALUES('enakn', 23)
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO students(name, age)" +
                    " VALUES('" + name + "', " + age + ")");

            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void editStudent() {

        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();

        System.out.println("name, age");
        System.out.print("Enter the field you want to edit: ");
        String field = scanner.next();

        System.out.print("Enter the updated value: ");
        String update = scanner.next();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE students SET " + field + " = '" + update + "' WHERE id = " + studentId);
            ps.execute();
            System.out.println("Successfully updated student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudent() {

        System.out.print("Enter student id: ");
        int studentId = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM students WHERE id=" + studentId);
            ps.execute();
            System.out.println("Successfully deleted student");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Student getStudentById() {

        // Prompt the user to enter the id of the student they want to retrieve.
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM students WHERE id=" + id);
            rs = ps.executeQuery();

            int studentId, age;
            String name;

            Student student = new Student();
           while (rs.next()) {
               studentId = rs.getInt("id");
               name = rs.getString("name");
               age = rs.getInt("age");
               student.setId(studentId);
               student.setName(name);
               student.setAge(age);
           }


           return student;

//            return new Student(id, name, age);


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void addStudentScore() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.print("Enter the mathematics score: ");
        int mathScore = scanner.nextInt();

        System.out.print("Enter the English score: ");
        int englishScore = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO scores (Mathematics, English, student_id) " +
                    "VALUES("+ mathScore + ", " + englishScore + ", " + id + ")");

            ps.execute();
            System.out.println("Successfully added score.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void editScore() {
        // Request for the data.
        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();

        System.out.println("Mathematics, English");
        System.out.print("Enter the subject: ");
        String subject = scanner.next();

        System.out.print("Enter the updated score: ");
        int update = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET " + subject + " = " + update + " WHERE student_id = " + studentId);
            ps.execute();
            System.out.println("Successfully updated score.");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void deleteScore() {
        // Request for the data.
        System.out.print("Enter the student's id: ");
        int studentId = scanner.nextInt();

        System.out.println("Mathematics, English");
        System.out.print("Enter the subject: ");
        String subject = scanner.next();


        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET " + subject + " = 0 WHERE student_id = " + studentId);
            ps.execute();
            System.out.println("Successfully updated score.");
        } catch (SQLException e) {
            e.getMessage();
        }


    }



    // Assignment.
    // Add methods to remove a student's score, edit a student's score and remove a student and edit student's details.
    // SELECT * FROM students;


}
