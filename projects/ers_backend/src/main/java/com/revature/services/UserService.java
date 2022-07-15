package com.revature.services;

import com.revature.exceptions.UserNotCreatedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.ERSReimbursement;
import com.revature.models.ERSUser;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserService {
    public ERSUser createUser(ERSUser u) throws UserNotCreatedException {
        u.setId(-1);
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            int id = (int) s.save(u);
            u.setId(id);
            tx.commit();
        } catch (ConstraintViolationException e) {
            //log it
        }

        if (u.getId() == -1) {
            throw new UserNotCreatedException();
        }
        return u;
    }

//    public ERSUser getUser(int id) throws UserNotFoundException {
//        ERSUser user;
//
//        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
//            user = s.get(ERSUser.class, id);
//        }
//
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        return user;
//    }

    public ERSUser getUser(String username) throws UserNotFoundException {
        ERSUser user;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<ERSUser> cq = cb.createQuery(ERSUser.class);

            Root<ERSUser> root = cq.from(ERSUser.class);
            cq.select(root).where(cb.equal(root.get("username"), username));

            user = s.createQuery(cq).getSingleResult();
        }

        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public List<ERSUser> getUsers() {
        List<ERSUser> users = null;

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            users = s.createQuery("from ERSUser ", ERSUser.class).list();
        }

        return users;
    }

    public void updateUser(ERSUser user) {
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = s.beginTransaction();
            ERSUser u = s.get(ERSUser.class, user.getId());
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            s.update(u);
            tx.commit();
        }
    }
}
