package com.revature.services;

import com.revature.exceptions.ReimbNotCreatedException;
import com.revature.exceptions.UserNotCreatedException;
import com.revature.models.ERSReimbursement;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class ReimbService {
    public ERSReimbursement createReimb(ERSReimbursement reimbursement) throws ReimbNotCreatedException {
        reimbursement.setId(-1);
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = s.beginTransaction();
            int id = (int) s.save(reimbursement);
            reimbursement.setId(id);
            tx.commit();
        } catch (ConstraintViolationException e) {
            //log it
        }

        if (reimbursement.getId() == -1) {
            throw new ReimbNotCreatedException();
        }
        return reimbursement;
    }
}
