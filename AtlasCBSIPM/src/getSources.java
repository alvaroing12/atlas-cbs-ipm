import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class getSources extends HttpServlet {


    ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
    

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter salida = response.getWriter();

        String param = request.getParameter("target");
                try {
                        java.sql.Connection conn;
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/BDB?user=root&password=alvaro");

                        Statement stmt = conn.createStatement();
                        ResultSet rs2 = stmt.executeQuery("select TARGETS.ID_TARGET,TARGETS.NAME from TARGETS ORDER BY NAME");
                        while (rs2.next()) {
                                salida.println(rs2.getString(1)+" - "+rs2.getString(2)+";");
                        }
                        conn.close();

                }catch (SQLException E) {
                }

        salida.close();
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
