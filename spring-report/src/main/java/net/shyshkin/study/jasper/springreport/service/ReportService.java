package net.shyshkin.study.jasper.springreport.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.util.Map;

public interface ReportService {

    byte[] generate() throws JRException, IOException;

    default JasperReport getReport() throws JRException, IOException {
        throw new RuntimeException("Not implemented");
    }

    default Map<String, Object> getParameters() {
        throw new RuntimeException("Not implemented");
    }

    default JRBeanCollectionDataSource getDataSource() {
        throw new RuntimeException("Not implemented");
    }

}
