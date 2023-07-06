package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.Address;

import java.util.List;
import java.util.Optional;

public class AddressDao {
    private static AddressDao instance;
    private final SessionFactory sessionFactory;

    private AddressDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized AddressDao getInstance() {
        if (instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }

    public Optional<Address> findById(Long id) {
        Session session = sessionFactory.openSession();
        Address address = session.find(Address.class, id);
        session.close();
        return Optional.ofNullable(address);
    }

    public List<Address> findAll() {
        Session session = sessionFactory.openSession();
        List<Address> addresses = session.createQuery("from Address", Address.class).list();
        session.close();
        return addresses;
    }

    public String saveOrUpdate(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address merged = session.merge(address);
        transaction.commit();
        session.close();

        return merged.getName();
    }

    public void delete(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(address);
        transaction.commit();
        session.close();
    }
}
