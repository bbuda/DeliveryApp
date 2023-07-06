package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Parcel;

import java.util.List;
import java.util.Optional;

public class ParcelDao {
    private static ParcelDao instance;
    private final SessionFactory sessionFactory;

    private ParcelDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized ParcelDao getInstance() {
        if (instance == null) {
            instance = new ParcelDao();
        }
        return instance;
    }

    public Optional<Parcel> findById(Long id) {
        Session session = sessionFactory.openSession();
        Parcel parcel = session.find(Parcel.class, id);
        session.close();
        return Optional.ofNullable(parcel);
    }

    public List<Parcel> findAll() {
        Session session = sessionFactory.openSession();
        List<Parcel> parcels = session.createQuery("from parcel", Parcel.class).list();
        session.close();
        return parcels;
    }

    public Long saveOrUpdate(Parcel parcel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Parcel merged = session.merge(parcel);
        transaction.commit();
        session.close();
        
        return merged.getId();
    }

    public void delete(Parcel parcel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(parcel);
        transaction.commit();
        session.close();
    }
}
