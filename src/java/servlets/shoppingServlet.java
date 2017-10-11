/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 740991
 */
@WebServlet(name = "shoppingServlet", urlPatterns = {"/shoppingServlet"})
public class shoppingServlet extends HttpServlet 
{
    ArrayList<String> cart = new ArrayList<String> ();
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException 
//    {   
//       
//    }

    protected void doEverything (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       HttpSession session = request.getSession();
       int count = 0;
        //Show cart 
       String cartString = "";
       for (String element: cart)
        {
            cartString += "<tr>";
            cartString += element;
            cartString += "<tr>";
        }
        request.setAttribute("cart", cartString);
        
        String action = request.getParameter("action");
//----------------------------------------------------------------------------------------------------------        
        if (action.equals("login"))
        {
            String username = request.getParameter("username");
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }

//-----------------------------------------------------------------------------------------------------------
        if (action.equals("add")) 
        {
            session.setAttribute("item" + count, request.getParameter("addToCart"));
            count ++;
            //String addToCart = request.getParameter("addToCart");
            //save to array list
            //cart.add(addToCart);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
//-----------------------------------------------------------------------------------------------------------
        if (action.equals("delete"))
        {
            String removeFromCart = request.getParameter("removeFromCart");
            //save to array list
            //for (String element: cart)
            //{
//                if(element.equals(removeFromCart))
//                {
//                    cart.remove(element);
//                }
//            }
            for (int i = 0; i < count; i++){
                if (session.getParameter("item" + i).equals(removeFromCart))
                {
                    for (int y = i; y < count; y++)
                    {
                        int z = i + 1;
                        session.setAttribute("item" + i, "");
                        if (!session.getParameter("item" + z).isEmpty()||!session.getParameter("item" + z).isNull())
                        {
                            session.setAttribute("item" + i, session.getParameter("item" + z));
                        }
                    }
                }
            }
            count--;
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
//-----------------------------------------------------------------------------------------------------------
        if (action.equals("logout"))
        {
            session.invalidate();
            request.setAttribute("username", "");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}