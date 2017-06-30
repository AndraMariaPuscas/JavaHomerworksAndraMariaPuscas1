/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class eBooksStoreAdminEBooks extends HttpServlet {

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
        //set connection to the database
        String user = "andra";
        String password = "andra";
        String url = "jdbc:derby://localhost:1527/eBooksDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        // try (PrintWriter out = response.getWriter()) {
        // check push on Insert button
        if (request.getParameter("ebook_insert") != null) {
            String title = request.getParameter("ebook_title");
            String author = request.getParameter("ebook_author");
            String sisbn = request.getParameter("ebook_isbn");
            int isbn = Integer.parseInt(sisbn);
            String sprice = request.getParameter("ebook_price");
            int price = Integer.parseInt(sprice);
            String srating = request.getParameter("ebook_rating");
            int rating = Integer.parseInt(srating);
            int ID_Author = -1;
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
                String query = "SELECT MAX(ebook_id) AS MAXID FROM BOOKS";
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
                String query_author = "SELECT MAX(ID_AUTHOR) AS MAXID_AUTHOR FROM AUTHORS";
                ResultSet resultSet_author = null;
                resultSet_author = statement.executeQuery(query_author);
                boolean resultSetHasRows1 = resultSet_author.next();
                if (resultSetHasRows1) {
                    int maxid_author = resultSet_author.getInt(1);
                    ID_Author = maxid_author + 1;
                } else {
                    ID_Author = 1;
                }

                // aici as putea sa fac o tranzactie, pentru a face toate inserturile deodata, sa nu fac inserturi separate (in cazul in care unu din inserturi nu se face, dar totusi baga datele in restul tabelelor
                // connection.setAutoCommit(false) - la realizarea conexiunii in baza de date
                // if nothing bad happen until now
                if (!noOperation) {
                    // realize the insert
                    //call stored procedure to insert a new book
                    String DML = "INSERT INTO BOOKS VALUES (?, ?, ?, ?, ?)";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setInt(1, isbn);
                    pstmnt.setString(2, title);
                    pstmnt.setInt(3, price);
                    pstmnt.setInt(4, rating);
                    pstmnt.setInt(5, ID);
                    pstmnt.execute();

                    String DML_Author = "INSERT INTO AUTHORS VALUES (?, ?)";
                    pstmnt = connection.prepareStatement(DML_Author);
                    pstmnt.setInt(1, ID_Author);
                    pstmnt.setString(2, author);
                    pstmnt.execute();

                    String DML_EBooks_Authors = "INSERT INTO EBOOKS_AUTHORS VALUES (?, ?, ?)";
                    pstmnt = connection.prepareStatement(DML_EBooks_Authors);
                    pstmnt.setInt(1, ID);
                    pstmnt.setInt(2, isbn);
                    pstmnt.setInt(3, ID_Author);
                    pstmnt.execute();
                    // display a message for ok
                }
            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } // check push on Update button
        else if (request.getParameter("ebook_update") != null) { // update

//update merge doar daca completez toate campurile, daca las isbn gol, crapa la parseInt(sisbn) pt ca incearca sa parseze un string gol
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
                String[] selectedCheckboxes = request.getParameterValues("ebooks_checkbox");
                String title = request.getParameter("ebook_title");
                String author = request.getParameter("ebook_author");
                String sisbn = request.getParameter("ebook_isbn");
                int isbn = Integer.parseInt(sisbn);
                String sprice = request.getParameter("ebook_price");
                int price = Integer.parseInt(sprice);
                String srating = request.getParameter("ebook_rating");
                int rating = Integer.parseInt(srating);
                int ID_Author = -1;
                int ID = -1;

                boolean noOperation = false;
                // if nothing bad happen move forward
                if (!noOperation) {
                    // only price and rating can be updated
                    //if both price and rating are "" do nothing
                    if (!(("".equals(price)) && ("".equals(rating)))) {
                        // operate updates for all selected rows
                        for (String s : selectedCheckboxes) {
                            // realize update of all selected rows
                            int BOOK_ID = Integer.parseInt(s);
                            if ("".equals(price)) { // only rating should be updated
                                String DML = "UPDATE BOOKS SET rating=? WHERE EBOOK_ID=?";
                                pstmnt = connection.prepareStatement(DML);
                                pstmnt.setInt(1, rating);

                                pstmnt.setInt(2, BOOK_ID);
                            } else if ("".equals(rating)) {// only price should be updated
                                String DML = "UPDATE BOOKS SET price=? WHERE EBOOK_ID=?";
                                pstmnt = connection.prepareStatement(DML);

                                pstmnt.setInt(1, price);
                                pstmnt.setInt(2, BOOK_ID);
                            } else {
                                String DML = "UPDATE BOOKS SET isbn=?, title=?,price=?, rating=?  WHERE EBOOK_ID=?";
                                pstmnt = connection.prepareStatement(DML);
                                pstmnt.setInt(1, isbn);
                                pstmnt.setString(2, title);
                                pstmnt.setInt(3, price);
                                pstmnt.setInt(4, rating);

                                pstmnt.setInt(5, BOOK_ID);
                                pstmnt.execute();
                            }
                            //boolean execute = pstmnt.execute();
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } //        } catch (Exception ex) {
        //            // display a message for NOT OK
        //            Logger.getLogger(eBooksStoreAdminUserRolesServlet.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        // check push on Delete button
        else if (request.getParameter("ebook_delete") != null) { // delete 
            // declare specific variables
            ResultSet resultSet = null;
            PreparedStatement pstmnt = null;
            Connection connection = null;
            try {

                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // identify selected checkbox and for each execute the delete operation
                String[] selectedCheckboxes = request.getParameterValues("ebooks_checkbox");
                // more critical DB operations should be made into a transaction
                connection.setAutoCommit(false);
                for (String s : selectedCheckboxes) {
                    // realize delete of all selected rows
                    int BOOK_ID = Integer.parseInt(s);
                    String DML = "DELETE FROM BOOKS WHERE EBOOK_ID=?";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setInt(1, BOOK_ID);
                    pstmnt.execute();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                    } catch (Exception ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } // check push on Cancel button
        else if (request.getParameter("ebook_cancel") != null) { // cancel
            request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
        }

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
        return "Servlet serves eBooksStoreAdminEBooks.JSP page";
    }// </editor-fold>

}
