package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reader.ReadFiles;

/**
 *
 * @author K G
 */
public class APIController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String selectedAPI = request.getParameter("selectedAPI");
        String selectedAPIAction = request.getParameter("selectedAPIAction");
        ReadFiles rf = new ReadFiles();
        String url;
        String actionDescription;
        String path;
        String name;
        List<String> apiNameList = new ArrayList<String>();
        List<String> apiActionNameList = new ArrayList<String>();
        if (requestURI.endsWith("/api") && selectedAPI == null && selectedAPIAction == null) {
            //path = rf.getPath();
            apiNameList = rf.getAPINameList();
            //name = apiNameList.get(0);
            //request.setAttribute("name", name);
            request.setAttribute("apiNameList", apiNameList);
            //request.setAttribute("path", path);
            url = "/viewByAPI.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        } else if (selectedAPI != null) {
            if (selectedAPI.equals("+")) {
                url = "/viewByAPI.jsp";
            } else {
                apiNameList = rf.getAPINameList();
                apiActionNameList = rf.getAPI(selectedAPI).getAPIActions();
                request.setAttribute("apiNameList", apiNameList);
                request.setAttribute("apiActionNameList", apiActionNameList);
                url = "/viewByAPI.jsp";
            }
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);

        } else if (selectedAPIAction != null) {
            if (selectedAPIAction.equals("+")) {
                url = "/viewByAPI.jsp";
            } else {
                apiNameList = rf.getAPINameList();
                actionDescription = rf.getAction(selectedAPIAction).getDescription();
                request.setAttribute("apiNameList", apiNameList);
                request.setAttribute("apiActionNameList", apiActionNameList);
                request.setAttribute("actionDescription", actionDescription);
                url = "/viewByAPI.jsp";
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }

        } else {
            //path = rf.getPath();
            apiNameList = rf.getAPINameList();
            //name = apiNameList.get(0);
            //request.setAttribute("name", name);
            request.setAttribute("apiNameList", apiNameList);
            //request.setAttribute("path", path);
            url = "/viewByAPI.jsp";
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        }

    }
}
