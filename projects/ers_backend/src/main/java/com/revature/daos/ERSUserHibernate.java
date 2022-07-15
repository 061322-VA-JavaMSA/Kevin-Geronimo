package com.revature.daos;

import com.revature.models.ERSUser;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;

public class ERSUserHibernate {
    public ERSUser getUser(int id) {
        ERSUser user;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            user = s.get(ERSUser.class, id);
        }

        return user;
    }
}
