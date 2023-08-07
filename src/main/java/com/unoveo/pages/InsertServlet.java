package com.unoveo.pages;

import com.unoveo.dao.EmployeeDao;
import com.unoveo.entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/table")
public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String firstname = req.getParameter("firstname");
        String lastname= req.getParameter("lastname");
        String age= req.getParameter("age");
        String city= req.getParameter("city");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        System.out.println(" table doPost called");
        PrintWriter pw = resp.getWriter();
        String id= req.getParameter("id");
        String name= req.getParameter("name");
        Integer age= Integer.valueOf(req.getParameter("age"));
        String city= req.getParameter("city");

        System.out.println(id + name +age + city);


        Employee employee = new Employee();

        employee.setName(name);
        employee.setAge(age);
        employee.setCity(city);


  EmployeeDao dao = new EmployeeDao();
dao.saveUser(employee);





    }
}