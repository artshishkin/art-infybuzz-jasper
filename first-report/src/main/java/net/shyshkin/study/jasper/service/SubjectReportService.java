package net.shyshkin.study.jasper.service;

import com.github.javafaker.Faker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
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

    private final ReportService subReportService = new FirstReportService();

    @Override
    public void generate() throws JRException {
        InputStream resourceAsStream = FirstReport.class.getClassLoader().getResourceAsStream("Student.jrxml");

        Map<String, Object> parameters = new HashMap<>(Map.of("studentName", "Arina"));

        var subjects = LongStream.rangeClosed(1, 20)
                .boxed()
                .map(i -> Subject.builder()
                        .subjectName(FAKER.programmingLanguage().name() + i)
                        .marksObtained(FAKER.random().nextLong(100))
                        .build()
                )
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subjects);
        parameters.put("tableData", dataSource);

        JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(subjects);

        JasperReport report = JasperCompileManager.compileReport(resourceAsStream);

        parameters.put("subReport", subReportService.getReport());
        parameters.put("subDataSource", subReportService.getDataSource());
        parameters.put("subParameters", subReportService.getParameters());

        JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
        String destinationFile = "output/subjects.pdf";
        JasperExportManager.exportReportToPdfFile(print, destinationFile);

        destinationFile = "output/subjects.html";
        JasperExportManager.exportReportToHtmlFile(print, destinationFile);

        destinationFile = "output/subjects.xlsx";
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destinationFile));
        exporter.exportReport();

        System.out.println("Subjects Report Created...");

    }

}
