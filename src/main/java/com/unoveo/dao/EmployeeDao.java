package com.unoveo.dao;
import com.unoveo.dbutils.HibernateUtil;
import com.unoveo.entity.Employee;
import jakarta.transaction.SystemException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao {



        /**
         * Save User
         * @param employee
         */
        public void saveUser(Employee employee) {

            System.out.println("Employee dao  saveUser");
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // start a transaction
                System.out.println("session created");
                transaction = session.beginTransaction();
                // save the student object
                session.save(employee);
                // commit transaction
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

    /**
     //     * Update user
     //     * @param employee
     //     */
    public static void saveOrUpdateStudent(Employee employee ) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

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
    }

    /**
     * Delete User
     * @param id
     */
    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
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
