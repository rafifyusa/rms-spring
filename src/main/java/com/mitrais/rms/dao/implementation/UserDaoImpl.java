package com.mitrais.rms.dao.implementation;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    //private SessionFactory sessionFactory;
    @PersistenceContext(name = "user-context")
    private EntityManager entityManager;

/*    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    @Override
    public Optional<User> findByUserName(String userName) {
        try {
            User user = (User)entityManager.createQuery("select user from User user where user.userName = :name").setParameter("name", userName).getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            //return Optional.of(sessionFactory.getCurrentSession().get(User.class, id));
            User user = (User)entityManager.createQuery("select user from User user where user.id = :id").setParameter("id", id).getSingleResult();
            return Optional.of(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        try {
            Query query = entityManager.createQuery("select user from User user");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean save(User user) {
        try {
            entityManager.persist(user);
            //sessionFactory.getCurrentSession().save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        try {
            User existing = entityManager.find(User.class, user.getId());
            existing.setUserName(user.getUserName());
            existing.setPassword(user.getPassword());
            entityManager.persist(existing);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            entityManager.remove(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
