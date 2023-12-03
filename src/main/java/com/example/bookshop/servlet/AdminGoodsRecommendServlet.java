package com.example.bookshop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.bookshop.service.GoodsService;

import java.io.IOException;

@WebServlet(name = "admin_goods_recommend",urlPatterns = "/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget=Integer.parseInt(request.getParameter("typeTarget"));
        if(method.equals("add")) {
            gService.addRecommend(id, typeTarget);
        }else {
            gService.removeRecommend(id, typeTarget);
        }
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    //doGet()方法省略
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
