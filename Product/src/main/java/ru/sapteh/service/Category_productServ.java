package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Category_product;

import java.util.List;

public class Category_productServ implements DAO<Category_product, Integer> {
    private final SessionFactory factory;

    public Category_productServ(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Category_product category_product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(category_product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Category_product category_product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(category_product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Category_product category_product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(category_product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Category_product read(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Category_product.class, id);
        }
    }

    @Override
    public List<Category_product> findByAll() {
        try(Session session = factory.openSession()){
            Query<Category_product> query = session.createQuery("FROM Category_product");
            return query.list();
        }
    }
}
