package net.shyshkin.study.jasper.service;

import com.github.javafaker.Faker;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.shyshkin.study.jasper.model.Coordinate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CoordinateReportService implements ReportService {

    private static final Faker FAKER = Faker.instance(Locale.ENGLISH);

    @Override
    public void generate() throws JRException {

        InputStream resourceAsStream = ReportService.class.getClassLoader().getResourceAsStream("Charts.jrxml");

        Map<String, Object> parameters = new HashMap<>(Map.of("studentName", "Arina"));

        String seriesName = "ArtPlot";

        var tableData = LongStream.rangeClosed(1, 20)
                .boxed()
                .map(i -> Coordinate.builder()
                        .xVal(1.0 * i)
                        .yVal(1.0 * FAKER.random().nextLong(100))
                        .seriesName(seriesName)
                        .build()
                )
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tableData, false);
        parameters.put("tableData", dataSource);

//        JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(subjects);

        JasperReport report = JasperCompileManager.compileReport(resourceAsStream);

//        JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        String destinationFile = "output/coordinates.pdf";
        JasperExportManager.exportReportToPdfFile(print, destinationFile);

        System.out.println("Subjects Report Created...");

    }

}
