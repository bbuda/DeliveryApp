package ru.bbuda.service;

import ru.bbuda.DeliveryApp;
import ru.bbuda.dao.AddressDao;
import ru.bbuda.dao.ClientDao;
import ru.bbuda.dao.CourierDao;
import ru.bbuda.dao.PersonCredentialsDao;
import ru.bbuda.model.Client;
import ru.bbuda.model.Courier;
import ru.bbuda.model.PersonCredentials;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public class AuthService {
    private static AuthService instance;
    private PersonCredentialsDao personCredentialsDao;
    private AddressDao addressDao;
    private ClientDao clientDao;
    private CourierDao courierDao;

    private AuthService() {
        this.personCredentialsDao = PersonCredentialsDao.getInstance();
        this.addressDao = AddressDao.getInstance();
        this.clientDao = ClientDao.getInstance();
        this.courierDao = CourierDao.getInstance();
    }

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean auth(String username, String password) {
        Optional<PersonCredentials> optionalCredentials = personCredentialsDao.findByUsername(username);
        if (optionalCredentials.isEmpty()) {
            return false;
        }

        PersonCredentials credentials = optionalCredentials.get();

        if (!encode(password).equals(credentials.getPassword())) {
            return false;
        }

        DeliveryApp.setCurrentPersonCredentials(credentials);
        return true;
    }

    public boolean registration(Client client) {
        Optional<PersonCredentials> optionalCredentials = personCredentialsDao.findByUsername(client.getUsername());
        if (optionalCredentials.isPresent()) {
            return false;
        }

        addressDao.saveOrUpdate(client.getAddress());

        client.setPassword(encode(client.getPassword()));
        clientDao.saveOrUpdate(client);

        return true;
    }

    public boolean registration(Courier courier) {
        Optional<PersonCredentials> optionalCredentials = personCredentialsDao.findByUsername(courier.getUsername());
        if (optionalCredentials.isPresent()) {
            return false;
        }

        courier.setPassword(encode(courier.getPassword()));
        courierDao.saveOrUpdate(courier);

        return true;
    }

    public String encode(String toEncode) {
        return Base64.getEncoder().encodeToString(toEncode.getBytes(StandardCharsets.UTF_8));
    }

    public String decode(String toDecode) {
        return new String(Base64.getDecoder().decode(toDecode), StandardCharsets.UTF_8);
    }
}
