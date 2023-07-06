package ru.bbuda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bbuda.configuration.DatabaseConfig;
import ru.bbuda.model.PersonCredentials;

import java.util.Optional;

public class PersonCredentialsDao {
    private static PersonCredentialsDao instance;
    private final SessionFactory sessionFactory;

    private PersonCredentialsDao() {
        sessionFactory = DatabaseConfig.getInstance().getSessionFactory();
    }

    public static synchronized PersonCredentialsDao getInstance() {
        if (instance == null) {
            instance = new PersonCredentialsDao();
        }
        return instance;
    }

    public Optional<PersonCredentials> findByUsername(String username) {
        Session session = sessionFactory.openSession();
        PersonCredentials credentials = session.createQuery("from Client where username = :udername", PersonCredentials.class).getSingleResult();
        session.close();
        return Optional.ofNullable(credentials);
    }
}
