package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Product;
import ru.sapteh.model.Warehouse;

import java.util.List;

public class WarehouseServ implements DAO<Warehouse, Integer> {
    private final SessionFactory factory;

    public WarehouseServ(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Warehouse warehouse) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(warehouse);
            session.getTransaction().commit();
        }
    }

    @Override
    public Warehouse read(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(Warehouse.class, id);
        }
    }

    @Override
    public List<Warehouse> findByAll() {
        try(Session session = factory.openSession()){
            Query<Warehouse> query = session.createQuery("FROM Warehouse");
            return query.list();
        }
    }
}
