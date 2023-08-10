package com.unoveo.restutil;

import com.unoveo.restutil.IGenServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public abstract class MyServlet implements IGenServlet {



    public abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}