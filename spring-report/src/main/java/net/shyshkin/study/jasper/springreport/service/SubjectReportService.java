package net.shyshkin.study.jasper.springreport.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.springreport.model.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
@Primary
public class SubjectReportService implements ReportService {

    private final ReportService firstReportService;
    private final Faker faker;

    @Value("jasper/Student.jrxml")
    ClassPathResource reportTemplate;

    @Override
    public byte[] generate() throws JRException, IOException {

        InputStream resourceAsStream = reportTemplate.getInputStream();

        Map<String, Object> parameters = new HashMap<>(Map.of("studentName", "Arina"));

        var subjects = LongStream.rangeClosed(1, 20)
                .boxed()
                .map(i -> Subject.builder()
                        .subjectName(faker.programmingLanguage().name() + i)
                        .marksObtained(faker.random().nextLong(100))
                        .build()
                )
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subjects);
        parameters.put("tableData", dataSource);

        JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(subjects);

        JasperReport report = JasperCompileManager.compileReport(resourceAsStream);

        parameters.put("subReport", firstReportService.getReport());
        parameters.put("subDataSource", firstReportService.getDataSource());
        parameters.put("subParameters", firstReportService.getParameters());

        JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
        return JasperExportManager.exportReportToPdf(print);
    }

}
