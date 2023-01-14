package net.shyshkin.study.jasper;

import java.io.InputStream;
import java.util.Map;

public class FirstReport {

    public static void main(String[] args) {

        try {

//            String reportPath = "C:\\Users\\Admin\\IdeaProjects\\Study\\Infybuzz\\art-infybuzz-jasper\\first-report\\src\\main\\resources\\FirstReport.jrxml";
            InputStream resourceAsStream = FirstReport.class.getClassLoader().getResourceAsStream("FirstReport.jrxml");

            Map<String,Object> parameters = Map.of("studentName","Art");

        } catch(Exception e) {
            System.out.println("Exception while creating report");
        }
    }

}
