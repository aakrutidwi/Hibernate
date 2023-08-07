package com.unoveo.restutil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unoveo.entity.Employee;


public class ConvertToJson {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp = getobjData(emp);

        ObjectMapper obj = new ObjectMapper();

        System.out.println(emp);
    }
public static Employee getobjData(Employee emp){
emp.setName("Aakruti");
emp.setAge(Integer.valueOf("21"));
emp.setCity("Indore");
return  emp;
}

}

