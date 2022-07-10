package com.revature.services;

import com.revature.models.ERSReimbStatus;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

public class UserService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public List<ERSReimbStatus> getALL() {

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            return s.createQuery("FROM ERSReimbStatus", ERSReimbStatus.class).list();
        }

    }
}
