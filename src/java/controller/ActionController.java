/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reader.ReadFiles;

/**
 *
 * @author K G
 */
public class ActionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String selectedAction = request.getParameter("selectedAction");
        ReadFiles rf = new ReadFiles();
        String url;
        String actionDescription;
        String usingAPIS;
        List<String> actionList = new ArrayList<String>();

        if (requestURI.endsWith("/action") && selectedAction == null) {
            actionList = rf.getActionNameList();
            request.setAttribute("actionList", actionList);
            url = "/viewByAction.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        } else if (selectedAction != null) {
            if (selectedAction.equals("+")) {
                url = "/viewByAction.jsp";
            } else {
                actionDescription = rf.getAction(selectedAction).getDescription();
                usingAPIS = rf.getAction(selectedAction).getUsingAPIS();
                actionList = rf.getActionNameList();
                request.setAttribute("actionList", actionList);
                request.setAttribute("actionDescription", actionDescription);
                request.setAttribute("usingAPIS", usingAPIS);
                url = "/viewByAction.jsp";
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }
        } else {
            actionList = rf.getActionNameList();
            request.setAttribute("actionList", actionList);
            url = "/viewByAction.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        }

    }

}
