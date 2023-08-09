package org.berka.controller;

import org.berka.repository.entity.Post;
import org.berka.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostController {

    public static void main(String[] args) {
       // createPost();
    }

    public static void createPost() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post1=Post.builder().content("icerik1").userId(1L).build();
        Post post2=Post.builder().content("icerik2").userId(2L).build();
        Post post3=Post.builder().content("icerik3").userId(3L).build();
        Post post4=Post.builder().content("icerik4").userId(4L).build();
        Post post5=Post.builder().content("icerik5").userId(5L).build();
        Post post6=Post.builder().content("icerik6").userId(4L).build();
        Post post7=Post.builder().content("icerik7").userId(4L).build();
        Post post8=Post.builder().content("icerik8").userId(4L).build();
        Post post9=Post.builder().content("icerik9").userId(5L).build();
        Post post10=Post.builder().content("icerik10").userId(5L).build();
        Post post11=Post.builder().content("icerik11").userId(3L).build();
        Post post12=Post.builder().content("icerik12").userId(2L).build();
        Post post13= Post.builder().content("icerik13").userId(1L).build();

        session.save(post1);
        session.save(post2);
        session.save(post3);
        session.save(post4);
        session.save(post5);
        session.save(post6);
        session.save(post7);
        session.save(post8);
        session.save(post9);
        session.save(post10);
        session.save(post11);
        session.save(post12);
        session.save(post13);

        transaction.commit();
        session.close();
    }
}
