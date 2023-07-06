package ru.bbuda.configuration;

import lombok.Getter;

import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bbuda.model.Client;
import ru.bbuda.model.Courier;
import ru.bbuda.model.Parcel;
import ru.bbuda.model.PersonDetails;

import java.util.Properties;

public class DatabaseConfig {

    private static DatabaseConfig instance;
    private final SessionFactory sessionFactory;

    private DatabaseConfig() {
        try {
            sessionFactory = new Configuration().setProperties(getHibernateProperties())
                                                .addAnnotatedClass(Parcel.class)
                                                .addAnnotatedClass(Client.class)
                                                .addAnnotatedClass(Courier.class)
                                                .addAnnotatedClass(PersonDetails.class)
                                                .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("DB.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки данных конфигурации базы данных", e);
        }
        return properties;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
