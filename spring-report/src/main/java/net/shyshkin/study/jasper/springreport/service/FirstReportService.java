package net.shyshkin.study.jasper.springreport.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.springreport.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class FirstReportService implements ReportService {

    private final Faker faker;

    @Value("jasper/FirstReport.jrxml")
    ClassPathResource reportTemplate;

    private Map<String, Object> parameters;
    private JasperReport report;
    private JRBeanCollectionDataSource dataSource;

    @Override
    public byte[] generate() throws JRException, IOException {

        Map<String, Object> parameters = getParameters();

        JRBeanCollectionDataSource dataSource = getDataSource();

        JasperReport report = getReport();

        JRBaseTextField stName = (JRBaseTextField) report.getTitle().getElementByKey("stName");
        stName.setFontSize(20f);
        stName.setForecolor(Color.RED);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }

    @Override
    public JasperReport getReport() throws JRException, IOException {
        if (report == null) {
            InputStream resourceAsStream = reportTemplate.getInputStream();
            report = JasperCompileManager.compileReport(resourceAsStream);
        }
        return report;
    }

    @Override
    public Map<String, Object> getParameters() {
        if (parameters == null) {
            parameters = new HashMap<>(Map.of("studentName", "Art"));
        }
        return parameters;
    }

    @Override
    public JRBeanCollectionDataSource getDataSource() {
        if (dataSource == null) {
            List<Student> students = LongStream.rangeClosed(1, 10)
                    .boxed()
                    .map(i -> Student.builder()
                            .id(i)
                            .firstName(faker.name().firstName())
                            .lastName(faker.name().lastName())
                            .street(faker.address().streetAddress())
                            .city(faker.address().city())
                            .build()
                    )
                    .collect(Collectors.toList());
            dataSource = new JRBeanCollectionDataSource(students);
        }
        return dataSource;
    }
}
