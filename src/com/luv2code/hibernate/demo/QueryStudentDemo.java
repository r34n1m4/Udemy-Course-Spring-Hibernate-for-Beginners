package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            List theStudents = session
                    .createQuery("from Student").getResultList();
            System.out.println("\nList of students: ");
            displayStudents(theStudents);

            theStudents = session
                    .createQuery("from Student s where s.lastName='Doe'").getResultList();
            System.out.println("\nStudents with last name Doe: ");
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where"
                    + " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            System.out.println("\nStudents Doe or Daffy: ");
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where" +
                            " s.email LIKE '%luv2code.com'").getResultList();
            System.out.println("\nStudents with email %@luv2code.com");
            displayStudents(theStudents);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List theStudents) {
        for (Object tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
