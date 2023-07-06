package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.DeliveryCenter;

import java.util.List;
import java.util.Optional;

public class DeliveryCenterDao {
    private static DeliveryCenterDao instance;
    private final SessionFactory sessionFactory;

    private DeliveryCenterDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized DeliveryCenterDao getInstance() {
        if (instance == null) {
            instance = new DeliveryCenterDao();
        }
        return instance;
    }

    public Optional<DeliveryCenter> findById(Long id) {
        Session session = sessionFactory.openSession();
        DeliveryCenter center = session.find(DeliveryCenter.class, id);
        session.close();
        return Optional.ofNullable(center);
    }

    public List<DeliveryCenter> findAll() {
        Session session = sessionFactory.openSession();
        List<DeliveryCenter> centers = session.createQuery("from DeliveryCenter", DeliveryCenter.class).list();
        session.close();
        return centers;
    }

    public String saveOrUpdate(DeliveryCenter center) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DeliveryCenter merged = session.merge(center);
        transaction.commit();
        session.close();

        return merged.getName();
    }

    public void delete(DeliveryCenter center) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(center);
        transaction.commit();
        session.close();
    }
}
