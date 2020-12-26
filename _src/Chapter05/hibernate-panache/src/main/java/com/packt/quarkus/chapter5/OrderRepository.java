package com.packt.quarkus.chapter5;

import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class OrderRepository {


    @Inject
    EntityManager entityManager;


    public List<Orders> findAll(Long customerId) {

        List l = Orders.list("customer.id", Sort.by("item"), customerId);
        return l;
    }

    public Orders findOrderById(Long id) {

        Orders order = Orders.findById(id);

        if (order == null) {
            throw new WebApplicationException("Order with id of " + id + " does not exist.", 404);
        }
        return order;
    }
    @Transactional
    public void updateOrder(Orders order) {

        Orders orderToUpdate = findOrderById(order.id);
        orderToUpdate.item = order.item;
        orderToUpdate.price = order.price;
    }
    @Transactional
    public void createOrder(Orders order, Customer c) {

        order.customer = c;
        order.persist();

    }
    @Transactional
    public void deleteOrder(Long orderId) {

        Orders order = findOrderById(orderId);
        order.delete();


    }
}