package com.kylin.jpa.helloworld.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.kylin.jpa.helloworld.po.User;

public class UserDaoManagedBean implements UserDao {

   @Inject
   private EntityManager em;

   @Inject
   private UserTransaction utx;

   public User getForUsername(String username) {
      try {
         try {
            utx.begin();
            try {
               Query query = em.createQuery("select u from User u where u.username = ?");
               query.setParameter(1, username);
               return (User) query.getSingleResult();
            } catch (NoResultException e) {
               return null;
            }
         } finally {
            utx.commit();
         }
      } catch (Exception e) {
         try {
            utx.rollback();
         } catch (SystemException se) {
            throw new RuntimeException(se);
         }
         throw new RuntimeException(e);
      }
   }

   public void createUser(User user) {
      try {
         try {
            utx.begin();
            em.persist(user);
         } finally {
            utx.commit();
         }
      } catch (Exception e) {
         try {
            utx.rollback();
         } catch (SystemException se) {
            throw new RuntimeException(se);
         }
         throw new RuntimeException(e);
      }
   }
}
