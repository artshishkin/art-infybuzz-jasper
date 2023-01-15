package net.shyshkin.study.jasper;

import com.github.javafaker.Faker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.model.Student;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FirstReport {

    public static final Faker FAKER = Faker.instance(Locale.ENGLISH);

    public static void main(String[] args) {

        try {

//            String reportPath = "C:\\Users\\Admin\\IdeaProjects\\Study\\Infybuzz\\art-infybuzz-jasper\\first-report\\src\\main\\resources\\FirstReport.jrxml";
            InputStream resourceAsStream = FirstReport.class.getClassLoader().getResourceAsStream("FirstReport.jrxml");

            Map<String, Object> parameters = new HashMap<>(Map.of("studentName", "Art"));

            List<Student> students = LongStream.rangeClosed(1, 10)
                    .boxed()
                    .map(i -> Student.builder()
                            .id(i)
                            .firstName(FAKER.name().firstName())
                            .lastName(FAKER.name().lastName())
                            .street(FAKER.address().streetAddress())
                            .city(FAKER.address().city())
                            .build()
                    )
                    .collect(Collectors.toList());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);

            JasperReport report = JasperCompileManager.compileReport(resourceAsStream);

            JRBaseTextField stName = (JRBaseTextField) report.getTitle().getElementByKey("stName");
            stName.setFontSize(20f);
            stName.setForecolor(Color.RED);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            String destinationFile = "output/students.pdf";
            JasperExportManager.exportReportToPdfFile(print, destinationFile);

            System.out.println("Report Created...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating report");
        }
    }

}
