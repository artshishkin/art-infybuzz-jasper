package net.shyshkin.study.jasper.service;

import com.github.javafaker.Faker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.FirstReport;
import net.shyshkin.study.jasper.model.Subject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SubjectReportService implements ReportService {

    private static final Faker FAKER = Faker.instance(Locale.ENGLISH);

    @Override
    public void generate() throws JRException {
        InputStream resourceAsStream = FirstReport.class.getClassLoader().getResourceAsStream("Student.jrxml");

        Map<String, Object> parameters = new HashMap<>(Map.of("studentName", "Arina"));

        var subjects = LongStream.rangeClosed(1, 30)
                .boxed()
                .map(i -> Subject.builder()
                        .subjectName(FAKER.programmingLanguage().name())
                        .marksObtained(FAKER.random().nextLong(100))
                        .build()
                )
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subjects);
        parameters.put("tableData", dataSource);

        JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(subjects);

        JasperReport report = JasperCompileManager.compileReport(resourceAsStream);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
        String destinationFile = "output/subjects.pdf";
        JasperExportManager.exportReportToPdfFile(print, destinationFile);

        System.out.println("Subjects Report Created...");

    }

}
