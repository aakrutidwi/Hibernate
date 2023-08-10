package com.unoveo.dao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.unoveo.dbutils.HibernateUtil;
import com.unoveo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {



    /**
     * Save User
     * @param employee
     */
    public void saveUser(Employee employee) {

        System.out.println("Employee dao  saveUser");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            System.out.println("session created");
            transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * //     * Update user
     * //     * @param employee
     * //
     */
    public Employee saveOrUpdateEmployee(Employee employee ) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            System.out.println("in update method ");
            session.saveOrUpdate(employee);
            Employee name = session.get(Employee.class, employee.getId());

            session.saveOrUpdate(name);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }
    public Employee getEmployeeById(int id) {
        Transaction transaction = null;
        Employee employee = null;
        System.out.println(" in get employee by id method");



        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            employee = session.get(Employee.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return employee;
    }

    public  String getAllEmployees() {
        Transaction transaction = null;
        List<Employee> employeelist = new ArrayList<>();
        JsonArray jsonArray=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee");
            employeelist = query.list();
            System.out.println("In conversion");
            Gson gson = new GsonBuilder().create();
            jsonArray = gson.toJsonTree(employeelist).getAsJsonArray();
            System.out.println(jsonArray);

            transaction.commit();
            System.out.println("try block of get all");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return  jsonArray.toString();
    }
    /**
     * Delete User
     * @param id
     */
    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {

                session.delete(employee);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


//


}