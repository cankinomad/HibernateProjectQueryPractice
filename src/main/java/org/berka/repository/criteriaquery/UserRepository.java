package org.berka.repository.criteriaquery;

import org.berka.repository.entity.EGender;
import org.berka.repository.entity.Name;
import org.berka.repository.entity.User;
import org.berka.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public UserRepository() {
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<User> findAll() {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        List<User> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return resultList;
    }

    public User findById(Long id) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        User singleResult=null;
        try {
            singleResult = entityManager.createQuery(criteriaQuery).getSingleResult();
            return singleResult;
        } catch (Exception e) {
            return singleResult;
        }

    }

    public Optional<User> findByUsername(String username) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));

        User singleResult = null;

        try {
            singleResult= entityManager.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(singleResult);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Name> findAllName() {
        CriteriaQuery<Name> criteriaQuery = criteriaBuilder.createQuery(Name.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root.get("name"));

        List<Name> resultList = null;

        try {
            resultList = entityManager.createQuery(criteriaQuery).getResultList();
            return resultList;
        } catch (Exception e) {
            System.out.println("Herhangi isme sahip bir kullanici bulunamadi");
            e.printStackTrace();
            return null;
        }
    }

    public List<String> findAllFirstName() {
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root.get("name").get("firstName"));
        List<String> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return resultList;

    }

    public List<User> findAllFirstNameStartsWith(String harf) {
        String kesinHarf = harf + "%";
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
//        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name").get("firstName"), kesinHarf));
        criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.like(root.get("name").get("firstName"), kesinHarf);
        criteriaQuery.where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<User> findAllFirstNameStartWithAndPostCount(String harf,Integer postCount) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        Predicate predicateFirstName = criteriaBuilder.like(root.get("name").get("firstName"), harf + "%");
        Predicate postCount1 = criteriaBuilder.greaterThan(root.get("postCount"), postCount);

        criteriaQuery.where(criteriaBuilder.and(predicateFirstName, postCount1));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Integer findSumOfPostCounts() {
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.sum(root.get("postCount")));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public User findMaxPostCountUser() {
        CriteriaQuery<Number> criteriaQuery = criteriaBuilder.createQuery(Number.class);
        Root<User> root = criteriaQuery.from(User.class);

        Expression<Number> postCount = criteriaBuilder.max(root.get("postCount"));
        criteriaQuery.select(postCount);
        Number singleResult = entityManager.createQuery(criteriaQuery).getSingleResult();

        CriteriaQuery<User> criteriaQuery1 = criteriaBuilder.createQuery(User.class);
        Root<User> root1 = criteriaQuery1.from(User.class);
        Predicate postCount1 = criteriaBuilder.equal(root1.get("postCount"), singleResult);
        criteriaQuery1.select(root1).where(postCount1);
        return entityManager.createQuery(criteriaQuery1).getSingleResult();
    }

    public List<Tuple> getUsernameGenderAndPostCount() {
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<User> root = criteriaQuery.from(User.class);
        Path<String> username = root.get("username");
        Path<EGender> gender = root.get("gender");
        Path<Number> postCount = root.get("postCount");
        criteriaQuery.multiselect(username, gender, postCount);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Tuple> postCountByGender() {
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.multiselect(root.get("gender"),criteriaBuilder.count(root.get("postCount"))).groupBy(root.get("gender"));

        return  entityManager.createQuery(criteriaQuery).getResultList();
    }
}
