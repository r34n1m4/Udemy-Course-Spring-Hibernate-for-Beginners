package com.luv2code.hibernate.demo.employee;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeSearch {
    private static void displayEmployees(List employees) {
        for (Object tempEmployee : employees) {
            System.out.println(tempEmployee);
        }
    }
    public EmployeeSearch() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (factory) {
            Session session = factory.getCurrentSession();
            int employeeId = 1;

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting employee with id: " + employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);
            System.out.println(myEmployee);

            System.out.println("\nEmployee List: ");
            List employees = session.createQuery("from Employee").getResultList();
            System.out.println("\nDisplay Employees");
            displayEmployees(employees);

            System.out.println("\nSearch employee by a company");
            employees = session
                    .createQuery("from Employee s where s.company='USSR'").getResultList();
            System.out.println("\nBy company:");
            displayEmployees(employees);
        }
    }
}
