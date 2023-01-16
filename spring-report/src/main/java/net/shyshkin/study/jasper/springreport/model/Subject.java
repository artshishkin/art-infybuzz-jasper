package net.shyshkin.study.jasper.springreport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

    private String subjectName;
    private long marksObtained;

}
