package org.berka.repository.hql;

import org.berka.repository.entity.Name;
import org.berka.repository.entity.User;
import org.berka.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserHql {

    EntityManager entityManager;

    public UserHql() {
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
    }

    public List<User> findAll() {
        String hql = "Select u from User u";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        List<User> resultList = typedQuery.getResultList();

        return resultList;

    }

    public User findById(Long id) {
        String hql = "Select u from User u where  u.id="+id;
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        User singleResult = null;

        try {
             singleResult= typedQuery.getSingleResult();
            return singleResult;
        } catch (Exception e) {
            return singleResult;
        }


    }

    public User findByUsername(String username) {
        String hql = "Select u from User u where u.username = :username";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);

        User singleResult=null;
        typedQuery.setParameter("username", username);

        try {
            singleResult= typedQuery.getSingleResult();
            return singleResult;
        } catch (Exception e) {
            return singleResult;
        }
    }

    public List<Name> findAllName() {
        String Hql = "Select u.name from User u";
        TypedQuery<Name> typedQuery = entityManager.createQuery(Hql, Name.class);

        List<Name> resultList;

        try {
            resultList= typedQuery.getResultList();
            return resultList;
        } catch (Exception e) {
            return null;
        }

    }

    public List<String> findAllFirstName() {
        String HQL = "Select u.name.firstName from User u";
        TypedQuery<String> typedQuery = entityManager.createQuery(HQL, String.class);
        return typedQuery.getResultList();
    }

    public List<User> findAllFirstNameStartsWith(String harf) {
        String HQl = "Select u from User u where u.name.firstName like :parametre";
        TypedQuery<User> typedQuery = entityManager.createQuery(HQl, User.class);
        typedQuery.setParameter("parametre", harf+"%");
        return typedQuery.getResultList();

    }

    public List<User> findAllFirstNameStartWithAndPostCount(String harf,Integer postCount) {
        String HQL = "Select u from User u where u.name.firstName like:parametre1 and u.postCount>: parametre2";
        TypedQuery<User> typedQuery = entityManager.createQuery(HQL, User.class);
        typedQuery.setParameter("parametre1", harf+"%");
        typedQuery.setParameter("parametre2", postCount);
        return typedQuery.getResultList();
    }

    public Long findSumOfPostCounts() {
        String HQL="select sum(u.postCount) from User u";
        TypedQuery<Long> typedQuery = entityManager.createQuery(HQL, Long.class);
        return typedQuery.getSingleResult();
    }

    public User findMaxPostCountUser() {
        String HQL = "Select u from User u where postCount=(Select max(u.postCount) from User u) ";
        TypedQuery<User> typedQuery = entityManager.createQuery(HQL, User.class);
        return typedQuery.getSingleResult();
    }

    public List<Tuple> getUsernameGenderAndPostCount() {
        String HQL = "select u.username,u.gender,u.postCount from User u";
        TypedQuery<Tuple> query = entityManager.createQuery(HQL, Tuple.class);
        return query.getResultList();
    }

    public List<Tuple> postCountByGender() {
        String HQL = "Select u.gender, count(u.postCount) from User u group by u.gender";
        TypedQuery<Tuple> query = entityManager.createQuery(HQL, Tuple.class);
        return query.getResultList();
    }

}
