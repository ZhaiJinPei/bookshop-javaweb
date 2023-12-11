package com.example.bookshop.service;

import com.example.bookshop.dao.OrderDao;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.OrderItem;
import com.example.bookshop.model.Page;
import com.example.bookshop.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("rawtypes,unchecked")
@Slf4j
public class OrderService {
    private final OrderDao oDao = new OrderDao();

    public void addOrder(Order order) {
        Connection con = null;
        try {
            con = DataSourceUtils.getConnection();
            con.setAutoCommit(false);

            oDao.insertOrder(con, order);
            int id = oDao.getLastInsertId(con);
            order.setId(id);
            for (OrderItem item : order.getItemMap().values()) {
                oDao.insertOrderItem(con, item);
            }

            con.commit();
        } catch (SQLException e) {
            log.info("SQLException", e);
            if (con != null)
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.info("SQLException", e);

                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> selectAll(int userid) {
        List<Order> list = null;
        try {
            list = oDao.selectAll(userid);
            for (Order o : list) {
                List<OrderItem> l = oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (SQLException e) {
            log.info("SQLException", e);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Page getOrderPage(int status, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 10;
        int totalCount = 0;
        try {
            totalCount = oDao.getOrderCount(status);
        } catch (Exception e) {
            log.info("SQLException", e);

        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list = null;
        try {
            list = oDao.selectOrderList(status, pageNumber, pageSize);
            for (Order o : (List<Order>) list) {
                List<OrderItem> l = oDao.selectAllItem(o.getId());
                o.setItemList(l);
            }
        } catch (Exception e) {
            log.info("SQLException", e);

        }
        p.setList(list);
        return p;
    }

    public void updateStatus(int id, int status) {
        try {
            oDao.updateStatus(id, status);
        } catch (Exception e) {
            log.info("SQLException", e);

        }
    }

    public void delete(int id) {
        Connection con = null;
        try {
            con = DataSourceUtils.getDataSource().getConnection();
            con.setAutoCommit(false);

            oDao.deleteOrderItem(con, id);
            oDao.deleteOrder(con, id);
            con.commit();
        } catch (Exception e) {
            log.info("SQLException", e);

            if (con != null)
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    log.info("SQLException", e);

                }
        }
    }
}
