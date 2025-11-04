package com.student.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.student.model.Student;
import com.student.util.HibernateUtil;
import java.util.List;

public class StudentCRUD {
    public static void main(String[] args) {
        // CREATE
        createStudent("Ravi", 21, "Computer Science");
        createStudent("Sneha", 22, "Electronics");

        // READ
        System.out.println("\nAll Students:");
        readStudents();

        // UPDATE
        updateStudent(1, "Ravi Sharma", 22, "AI & ML");

        // DELETE
        deleteStudent(2);

        // Final Read
        System.out.println("\nAfter Update & Delete:");
        readStudents();
    }

    public static void createStudent(String name, int age, String course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student(name, age, course);
        session.save(s);

        tx.commit();
        session.close();
        System.out.println("Student added successfully: " + s.getName());
    }

    public static void readStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        students.forEach(System.out::println);
        session.close();
    }

    public static void updateStudent(int id, String name, int age, String course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            session.update(s);
            System.out.println("Student updated: " + s);
        }

        tx.commit();
        session.close();
    }

    public static void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        if (s != null) {
            session.delete(s);
            System.out.println("Deleted Student ID: " + id);
        }

        tx.commit();
        session.close();
    }
}
