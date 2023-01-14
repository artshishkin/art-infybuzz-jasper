package net.shyshkin.study.jasper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;

}
