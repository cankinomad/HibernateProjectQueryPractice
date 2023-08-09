package org.berka.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbluser")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Name name;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false, length = 32)
    private String password;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> interests;
    @Transient //bu ozellik databasede olusturuulmayacak.
    private int age;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<EAddressType, Address> addresses;

    private Integer postCount;
}
