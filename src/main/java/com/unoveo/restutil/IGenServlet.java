package com.unoveo.restutil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IGenServlet {

    public abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
