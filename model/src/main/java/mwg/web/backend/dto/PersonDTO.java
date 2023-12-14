package mwg.web.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    private Long personId;

    private String identification;

    private String name;

    private int age;

    private String phoneNumber;

    private Boolean isHead;

    private String relationship;

    private Long householdId;

}
