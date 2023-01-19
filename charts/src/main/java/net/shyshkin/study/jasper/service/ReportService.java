package net.shyshkin.study.jasper.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

public interface ReportService {

    void generate() throws JRException;

    default JasperReport getReport() throws JRException {
        throw new RuntimeException("Not implemented");
    }

    default Map<String, Object> getParameters() {
        throw new RuntimeException("Not implemented");
    }

    default JRBeanCollectionDataSource getDataSource() {
        throw new RuntimeException("Not implemented");
    }

}
