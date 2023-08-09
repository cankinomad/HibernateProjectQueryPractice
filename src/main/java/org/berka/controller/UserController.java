package org.berka.controller;

import org.berka.repository.criteriaquery.UserRepository;
import org.berka.repository.entity.*;
import org.berka.repository.hql.UserHql;
import org.berka.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Tuple;
import java.util.*;

public class UserController {
    public static void main(String[] args) {
       // createUsers();

        UserRepository repository = new UserRepository();

//        List<User> all = repository.findAll();
//        all.forEach(x->{
//            System.out.println(x.getId()+" "+x.getName()+" "+x.getUsername());
//        });

        UserHql userHql = new UserHql();
      //  List<User> all1 = userHql.findAll();

//        all1.forEach(x->{
//            System.out.println(x.getId()+" "+x.getName()+" "+x.getUsername());
//        });


//        User byId = repository.findById(1L);
//        System.out.println(byId);

//        User byId = userHql.findById(1L);
//        System.out.println(byId);

//        Optional<User> zlh = repository.findByUsername("zlh");
//        System.out.println(zlh.get());

//        User zlh = userHql.findByUsername("zlh");
//        System.out.println(zlh);

//        List<Name> allName = repository.findAllName();
//        allName.forEach(System.out::println);

//        List<Name> allName = userHql.findAllName();
//        allName.forEach(System.out::println);


//        List<String> allFirstName = repository.findAllFirstName();
//        System.out.println(allFirstName);

//        List<String> allFirstName = userHql.findAllFirstName();
//        System.out.println(allFirstName);
//
//        System.out.println(repository.findAllFirstNameStartsWith("M"));

//        System.out.println(userHql.findAllFirstNameStartsWith("M"));


//        System.out.println(repository.findAllFirstNameStartWithAndPostCount("M", 8));

//        System.out.println(userHql.findAllFirstNameStartWithAndPostCount("M", 8));

       // System.out.println(repository.findSumOfPostCounts());
       // System.out.println(userHql.findSumOfPostCounts());

       // System.out.println(repository.findMaxPostCountUser());
        //System.out.println(userHql.findMaxPostCountUser());


//        List<Tuple> usernameGenderAndPostCount = repository.getUsernameGenderAndPostCount();
//        for (Tuple tuple : usernameGenderAndPostCount) {
//            System.out.println(tuple.get(0)+" "+tuple.get(1)+" "+tuple.get(2));
//        }

//        List<Tuple> usernameGenderAndPostCount1 = userHql.getUsernameGenderAndPostCount();
//        for (Tuple tuple : usernameGenderAndPostCount1) {
//            System.out.println(tuple.get(0)+" "+tuple.get(1)+" "+tuple.get(2));
//        }

//        List<Tuple> tupleList = repository.postCountByGender();
//        for (Tuple tuple : tupleList) {
//            System.out.println(tuple.get(0)+" "+tuple.get(1));
//        }

        List<Tuple> tupleList = userHql.postCountByGender();
        for (Tuple tuple : tupleList) {
            System.out.println(tuple.get(0)+" "+tuple.get(1));
        }

    }


    public static void createUsers() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        List<String> list1 = Arrays.asList("Astrololi", "Sinema");
        List<String> list2 = Arrays.asList("Dans", "Muzik");
        List<String> list3 = Arrays.asList("Seyahat", "Tiyatro");
        List<String> list4 = Arrays.asList("Kitap", "Pc oyunlari");

        Map<EAddressType, Address> map1 = new HashMap<>();
        map1.put(EAddressType.HOME, new Address("Ankara", "Turkiye"));
        map1.put(EAddressType.WORK, new Address("Ankara", "Turkiye"));

        Map<EAddressType, Address> map2 = new HashMap<>();
        map2.put(EAddressType.HOME, new Address("Ankara", "Turkiye"));
        map2.put(EAddressType.WORK, new Address("Istanbul", "Turkiye"));

        Map<EAddressType, Address> map3 = new HashMap<>();
        map3.put(EAddressType.HOME, new Address("Istanbul", "Turkiye"));
        map3.put(EAddressType.WORK, new Address("Istanbul", "Turkiye"));



        User user1 = User.builder()
                .name(Name.builder().firstName("Zeliha").lastName("Unlu").build())
                .username("zlh")
                .gender(EGender.WOMAN)
                .interests(list1)
                .addresses(map1)
                .postCount(20)
                .password("123456")
                .build();
        User user2 = User.builder()
                .name(Name.builder().firstName("Mustafa").lastName("Ozturk").build())
                .username("musty")
                .gender(EGender.MAN)
                .interests(list2)
                .addresses(map2)
                .postCount(10)
                .password("12345678")
                .build();
        User user3 = User.builder()
                .name(Name.builder().firstName("Merve").lastName("Keskin").build())
                .username("merve")
                .gender(EGender.WOMAN)
                .interests(list2)
                .addresses(map2)
                .postCount(5)
                .password("123")
                .build();
        User user4 = User.builder()
                .name(Name.builder().firstName("Gokhan").lastName("Kaya").build())
                .username("gokhan")
                .gender(EGender.MAN)
                .interests(list3)
                .addresses(map3)
                .postCount(4)
                .password("1234")
                .build();
        User user5= User.builder()
                .name(Name.builder().firstName("Mert").lastName("Gurel").build())
                .username("mert")
                .gender(EGender.MAN)
                .interests(list4)
                .addresses(map3)
                .postCount(9)
                .password("12345")
                .build();


        session.save(user1);
        session.save(user2);
        session.save(user3);
        session.save(user4);
        session.save(user5);



        transaction.commit();
        session.close();
    }
}
