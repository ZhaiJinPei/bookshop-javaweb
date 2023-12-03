package com.example.bookshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.User;
import com.example.bookshop.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "order_list", urlPatterns = "/order_list")
public class OrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u = (User) request.getSession().getAttribute("user");
        if(u==null)  //如果未登录
        {
            response.sendRedirect("/index");
            return;
        }
//        System.out.println("OrderListServlet------"+u.toString());
        List<Order> list = oService.selectAll(u.getId());

        request.setAttribute("orderList", list);
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
