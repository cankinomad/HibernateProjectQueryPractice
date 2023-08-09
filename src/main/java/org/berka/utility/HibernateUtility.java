package org.berka.utility;

import org.berka.repository.entity.Post;
import org.berka.repository.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private static final SessionFactory SESSION_FACTORY = createSessionFactoryHibernate();

    public static SessionFactory createSessionFactoryHibernate() {
        SessionFactory sessionFactory = null;

        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Post.class);
            sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            return sessionFactory;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
