/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andra
 */
public class eBooksStoreAdminUsersServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // declare specific DB info
        String user = "andra";
        String password = "andra";
        String url = "jdbc:derby://localhost:1527/eBooksDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        // check push on Insert button
        if (request.getParameter("admin_users_insert") != null) { // insert values from fields
            // set connection paramters to the DB
            // read values from page fields
            String username = request.getParameter("admin_users_username");
            String user_password = request.getParameter("admin_users_password");
            String srole = request.getParameter("admin_user_role");
            int role = -1;
            String name = request.getParameter("admin_users_name");
            String adress = request.getParameter("admin_users_address");
            String email = request.getParameter("admin_users_email");
            String sphone = request.getParameter("admin_users_phone");
            int phone = Integer.parseInt(sphone);
            int customerID = -1;
            int ID = -1;
            // declare specific variables
            ResultSet resultSet = null;
            Statement statement = null;
            Connection connection = null;
            PreparedStatement pstmnt = null;
            try {
                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // User ID should be generated incremental based on last value of ID
                statement = connection.createStatement();
                String query = "SELECT MAX(USER_ID) AS MAXID FROM USERS";
                resultSet = statement.executeQuery(query);
                boolean resultSetHasRows = resultSet.next();
                boolean noOperation = false;
                if (resultSetHasRows) {
                    // create new ID as MAXID + 1
                    int maxid = resultSet.getInt(1);
                    ID = maxid + 1;

                } else {
                    ID = 1;
                }
                // Role ID should be recovered from table User_role
                statement = connection.createStatement();
                query = "SELECT ID_ROLE AS USER_ROLE FROM USER_ROLE where ROLE = \'" + srole + "\'";
                resultSet = statement.executeQuery(query);
                resultSetHasRows = resultSet.next();

                if (resultSetHasRows) {
                    // create new ID as MAXID + 1
                    int role_id = resultSet.getInt(1);
                    role = role_id;

                } else {
                    ID = 1;
                }
                // if nothing bad happen until now
                if (!noOperation) {
                    // realize the insert
                    //call stored procedure to insert a new person
                    String DML = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setInt(1, ID);
                    pstmnt.setString(2, username);
                    pstmnt.setString(3, user_password);
                    pstmnt.setInt(4, role);
                    pstmnt.setString(5, name);
                    pstmnt.setString(6, adress);
                    pstmnt.setString(7, email);
                    pstmnt.setInt(8, phone);
                    pstmnt.execute();
                    // display a message for ok
                }
            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminUsersPage.jsp").forward(request, response);
            }
        } // check push on Update button
        else if (request.getParameter("admin_users_update") != null) { // update
            // declare specific variables
            ResultSet resultSet = null;
            Statement statement = null;
            PreparedStatement pstmnt = null;
            Connection connection = null;
            try {
                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // identify selected checkbox and for each execute the update operation
                String[] selectedCheckboxes = request.getParameterValues("admin_users_checkbox");
                String username = request.getParameter("admin_users_username");
                String user_password = request.getParameter("admin_users_password");
                String srole = request.getParameter("admin_user_role");
                int role = -1;
                String name = request.getParameter("admin_users_name");
                String adress = request.getParameter("admin_users_address");
                String email = request.getParameter("admin_users_email");
                String sphone = request.getParameter("admin_users_phone");
                int phone = Integer.parseInt(sphone);
                // Role ID should be recovered from table User_role
                statement = connection.createStatement();
                String query = "SELECT ID_ROLE AS USER_ROLE FROM USER_ROLE where ROLE = \'" + srole + "\'";
                resultSet = statement.executeQuery(query);
                boolean resultSetHasRows = resultSet.next();

                if (resultSetHasRows) {
                    // recover the role_id and assign it to variable role
                    int role_id = resultSet.getInt(1);
                    role = role_id; 
                }
                boolean noOperation = false;
                // if nothing bad happen move forward
                if (!noOperation) {
                    // if both username and password are "" do nothing
                    if (!(("".equals(username)) && ("".equals(user_password)))) {
                        // operate updates for all selected rows
                        for (String s : selectedCheckboxes) {
                            // realize update of all selected rows
                            int USER_ID = Integer.parseInt(s);
                            if ("".equals(username)) { // only password/s should be updated
                                String DML = "UPDATE USERS SET password=?,id_role=?, name=?, adress=?, email=?, phone=? WHERE USER_ID=?";
                                pstmnt = connection.prepareStatement(DML);
                                pstmnt.setString(1, user_password);
                                pstmnt.setInt(2, role);
                                pstmnt.setInt(3, USER_ID);
                            } else if ("".equals(user_password)) {// only username should be updated
                                String DML = "UPDATE USERS SET username=?,id_role=?, name=?, adress=?, email=?, phone=? WHERE USER_ID=?";
                                pstmnt = connection.prepareStatement(DML);
                                pstmnt.setString(1, username);
                                pstmnt.setInt(2, role);
                                pstmnt.setInt(3, USER_ID);
                            } else {
                                String DML = "UPDATE USERS SET username=?, password=?,id_role=?, name=?, adress=?, email=?, phone=? WHERE USER_ID=?";
                                pstmnt = connection.prepareStatement(DML);
                                pstmnt.setString(1, username);
                                pstmnt.setString(2, user_password);
                                pstmnt.setInt(3, role);
                                pstmnt.setString(4, name);
                                pstmnt.setString(5, adress);
                                pstmnt.setString(6, email);
                                pstmnt.setInt(7, phone);
                                pstmnt.setInt(8, USER_ID);
                                pstmnt.execute();
                            }
                        }
                    } else { // update one or more roles for one or more users
                        for (String s : selectedCheckboxes) {
                            // realize update of all selected rows
                            int USER_ID = Integer.parseInt(s);
                            String DML = "UPDATE USERS SET id_role=? WHERE USER_ID=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setInt(1, role);
                            pstmnt.setInt(2, USER_ID);
                            boolean execute = pstmnt.execute();
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminUsersPage.jsp").forward(request, response);
            }
        } // check push on Delete button
        else if (request.getParameter("admin_users_delete") != null) { // delete 
            // declare specific variables
            ResultSet resultSet = null;
            PreparedStatement pstmnt = null;
            Connection connection = null;
            try {

                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // identify selected checkbox and for each execute the delete operation
                String[] selectedCheckboxes = request.getParameterValues("admin_users_checkbox");
                // more critical DB operations should be made into a transaction
                connection.setAutoCommit(false);
                for (String s : selectedCheckboxes) {
                    // realize delete of all selected rows
                    int USER_ID = Integer.parseInt(s);
                    String DML = "DELETE FROM USERS WHERE USER_ID=?";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setInt(1, USER_ID);
                    pstmnt.execute();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminUsersPage.jsp").forward(request, response);
            }
        } // check push on Cancel button
        else if (request.getParameter("admin_users_cancel") != null) { // cancel
            request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
        } // check push on admin user roles button
        else if (request.getParameter("admin_userroles_open") != null) { // insert values from fields
            request.getRequestDispatcher("./eBooksStoreAdminUserRolesPage.jsp").forward(request, response);
        } // check push on admin customers button
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet is serving eBooksStoreAdminUsersPage.jsp";
    }// </editor-fold>

}
