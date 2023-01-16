package net.shyshkin.study.jasper.springreport.controller;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.shyshkin.study.jasper.springreport.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/api/v1/report")
    public ResponseEntity<byte[]> getReport() throws JRException, IOException {

        byte[] content = reportService.generate();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .headers(httpHeaders -> httpHeaders.setContentDispositionFormData("filename", "subjects.pdf"))
                .body(content);
    }

}
