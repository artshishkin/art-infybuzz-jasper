package net.shyshkin.study.jasper.service;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    void generate() throws JRException;
}
