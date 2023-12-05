package com.example.bookshop.model;

import com.example.bookshop.utils.PriceUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Data
public class Order {
    private int id;
    private float total;  //总价
    private int amount;  // 商品总数
    private int status;  //1未付款/2已付款/3已发货/4已完成
    private int paytype;  //1微信/2支付宝/3货到付款
    private String name;  //用户名
    private String phone;  //用户电话
    private String address;  //用户地址

    //    private Date datetime;  //订单日期 (日期格式与数据库的不兼容)
    private LocalDateTime datetime;  //订单日期(改后)
    private User user;
    private Map<Integer,OrderItem> itemMap = new HashMap<>();
    private List<OrderItem> itemList = new ArrayList<>();

    public void setUsername(String username) {
        user = new User();
        user.setUsername(username);
    }
    public void addGoods(Goods g) {
        if(itemMap.containsKey(g.getId())) {
            OrderItem item = itemMap.get(g.getId());
            item.setAmount(item.getAmount()+1);
        }else {
            OrderItem item = new OrderItem(g.getPrice(),1,g,this);
            itemMap.put(g.getId(), item);
        }
        amount++;
        total = PriceUtils.add(total, g.getPrice());
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void lessen(int goodsid) {
        if(itemMap.containsKey(goodsid)) {
            OrderItem item = itemMap.get(goodsid);
            item.setAmount(item.getAmount()-1);
            amount--;
            total = PriceUtils.subtract(total, item.getPrice());
            if(item.getAmount()<=0) {
                itemMap.remove(goodsid);
            }
        }
    }
    public void delete(int goodsid)
    {
        if(itemMap.containsKey(goodsid)) {
            OrderItem item = itemMap.get(goodsid);
            total = PriceUtils.subtract(total, item.getAmount()*item.getPrice());
            amount-=item.getAmount();
            itemMap.remove(goodsid);
        }
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Order() {
        super();
    }
}
