package mwg.web.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "person_identification", length = 11)
    private String identification;

    @Column(name = "full_name", length = 50)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "is_head")
    private Boolean isHead;

    @Column(name = "relationship")
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @OneToMany(mappedBy = "head")
    private List<Receipt> receipts = new ArrayList<>();

}
