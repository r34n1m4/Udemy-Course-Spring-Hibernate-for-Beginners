package com.luv2code.hibernate.demo.employee;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeePrimaryKey {
    public EmployeePrimaryKey() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating 3 new Employee objects");
            Employee tempEmployee1 = new Employee("John", "Doe", "Amazon");
            Employee tempEmployee2 = new Employee("James", "Hatfield", "Metallica");
            Employee tempEmployee3 = new Employee("Ivan", "Ivanov", "USSR");

            session.beginTransaction();

            System.out.println("Saving the employees");
            session.save(tempEmployee1);
            session.save(tempEmployee2);
            session.save(tempEmployee3);

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
