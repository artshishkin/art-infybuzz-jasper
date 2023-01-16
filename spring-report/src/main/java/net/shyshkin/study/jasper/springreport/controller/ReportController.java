package net.shyshkin.study.jasper.springreport.controller;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.shyshkin.study.jasper.springreport.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/api/v1/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getReport() throws JRException, IOException {
        return reportService.generate();
    }

}
