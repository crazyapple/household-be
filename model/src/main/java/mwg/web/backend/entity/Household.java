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
@Table(name = "household")
public class Household {

    @Id
    @Column(name = "household_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long householdId;

    @OneToMany(mappedBy = "household")
    private List<Person> members = new ArrayList<>();

    @Column(name = "address", length = 200)
    private String address;

}
