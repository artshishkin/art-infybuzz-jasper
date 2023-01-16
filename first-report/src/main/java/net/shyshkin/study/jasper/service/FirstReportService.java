package net.shyshkin.study.jasper.service;

import com.github.javafaker.Faker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.FirstReport;
import net.shyshkin.study.jasper.model.Student;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FirstReportService implements ReportService {

    private static final Faker FAKER = Faker.instance(Locale.ENGLISH);

    private Map<String, Object> parameters;
    private JasperReport report;
    private JRBeanCollectionDataSource dataSource;

    @Override
    public void generate() throws JRException {

        Map<String, Object> parameters = getParameters();

        JRBeanCollectionDataSource dataSource = getDataSource();

        JasperReport report = getReport();

        JRBaseTextField stName = (JRBaseTextField) report.getTitle().getElementByKey("stName");
        stName.setFontSize(20f);
        stName.setForecolor(Color.RED);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        String destinationFile = "output/students.pdf";
        JasperExportManager.exportReportToPdfFile(print, destinationFile);

        System.out.println("First Report Created...");

    }

    @Override
    public JasperReport getReport() throws JRException {
        if (report == null) {
            InputStream resourceAsStream = FirstReport.class.getClassLoader().getResourceAsStream("FirstReport.jrxml");
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
                            .firstName(FAKER.name().firstName())
                            .lastName(FAKER.name().lastName())
                            .street(FAKER.address().streetAddress())
                            .city(FAKER.address().city())
                            .build()
                    )
                    .collect(Collectors.toList());
            dataSource = new JRBeanCollectionDataSource(students);
        }
        return dataSource;
    }
}
