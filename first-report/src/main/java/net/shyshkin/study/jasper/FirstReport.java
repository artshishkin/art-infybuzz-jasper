package net.shyshkin.study.jasper;

import net.shyshkin.study.jasper.service.FirstReportService;
import net.shyshkin.study.jasper.service.ReportService;
import net.shyshkin.study.jasper.service.SubjectReportService;

public class FirstReport {

    public static void main(String[] args) {

        try {

            ReportService firstReportService = new FirstReportService();
            firstReportService.generate();

            ReportService subjectReportService = new SubjectReportService();
            subjectReportService.generate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating report");
        }
    }

}
