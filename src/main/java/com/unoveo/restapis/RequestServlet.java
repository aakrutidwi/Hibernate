package com.unoveo.restapis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unoveo.restutil.IGenServlet;
import com.unoveo.dao.EmployeeDao;
import com.unoveo.entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employee/*")
public class RequestServlet extends HttpServlet implements IGenServlet {

    private static final Gson gson = new GsonBuilder().create();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("PATCH")){
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();

        StringBuilder sb = new StringBuilder();
        String line = null;

        try{
            BufferedReader reader = req.getReader();
            while((line =reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(sb+" sb string");
        Employee emp ;

        System.out.println("after emp string conv");
        emp = new  Gson().fromJson(String.valueOf(sb),Employee.class);
        System.out.println(emp+" emp objects");

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.saveOrUpdateEmployee(emp);


        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        resp.setHeader("Context-Type","application/json");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in do get method of RequestServlet");

        String URI = req.getRequestURI();
        String path = req.getPathInfo();
        System.out.println("this is path>>  "+path);
        PrintWriter out = resp.getWriter();
        if(path==null || path.equals("/")){
            
            EmployeeDao employeeDao = new EmployeeDao();
            out.println( employeeDao.getAllEmployees());

        }else {
            System.out.println("get only user with id"+ path.substring(1));
         
            EmployeeDao employeeDao = new EmployeeDao();
            Employee emp =  employeeDao.getEmployeeById(Integer.parseInt(path.substring(1)));

            ObjectMapper obj = new ObjectMapper();
            String json= obj.writeValueAsString(emp);
            System.out.println(json);

                out.println(json);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        System.out.println("in delete method");
        String path = req.getPathInfo();
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.deleteUser(Integer.parseInt(path.substring(1)));

        resp.setStatus(200);
        resp.setHeader("Context-Type","application/json");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        String path = req.getPathInfo();

        System.out.println("In put method");
        System.out.println("get only user with id"+ path.substring(1));

        StringBuilder sb = new StringBuilder();
        String line = null;

        PrintWriter out = resp.getWriter();

        try{
            BufferedReader reader = req.getReader();
            while((line =reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(sb+" sb ");

        Employee oldEmployee =  new  Gson().fromJson(String.valueOf(sb),Employee.class);
        oldEmployee.setId(Integer.parseInt(path.substring(1)));
        System.out.println(oldEmployee);

        EmployeeDao employeeDao = new EmployeeDao();

        System.out.println("save or update called ");
        employeeDao.saveOrUpdateEmployee(oldEmployee);

        resp.setStatus(200);


        resp.setHeader("Context-Type","application/json");

    }




    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URI = request.getRequestURI();
        System.out.println("in do patch ");
        StringBuilder sb = new StringBuilder();
        String line = null;
        String path = request.getPathInfo();
        System.out.println("This is path >>>>>>>>>"+ path.substring(1));
        int empId = Integer.parseInt(path.substring(1));
        System.out.println(empId);
 // try bloxk reads request body and appends every line of body into strinbuilder object
        try{
            BufferedReader reader = request.getReader();
            while((line =reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


/// below line converts stringbuilder object(jsonr string) into java object of type employee nwith newWmployeee object
        Employee newEmployee = new Gson().fromJson(String.valueOf(sb),Employee.class);
        System.out.println("new Employee" +newEmployee);
//
//



        EmployeeDao employeeDao = new EmployeeDao();

//below line gets data of old employee from db
        Employee oldEmployee = employeeDao.getEmployeeById(Integer.parseInt(String.valueOf(empId)));
        System.out.println("This is employee from db"+oldEmployee.toString());
        System.out.println("This is employee from postman "+newEmployee.toString());

        if( newEmployee.getName() != null){
            System.out.println("name is not null set it to "+oldEmployee.getName());
            oldEmployee.setName(newEmployee.getName());
        }
        if(newEmployee.getAge() != null){
            System.out.println("age is not null sett it to "+oldEmployee.getAge());
            oldEmployee.setAge(newEmployee.getAge());
        }
        if(newEmployee.getCity() != null){
            System.out.println("city is not null sett it to "+oldEmployee.getCity());
            oldEmployee.setCity(newEmployee.getCity());
        }

        System.out.println("after setting props"+oldEmployee.toString());
        employeeDao.saveOrUpdateEmployee(oldEmployee);

        System.out.println("new employee updated" + employeeDao.getEmployeeById(Integer.parseInt(path.substring(1))));

    }
}

