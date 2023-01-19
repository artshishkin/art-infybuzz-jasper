package net.shyshkin.study.jasper;

import net.shyshkin.study.jasper.service.CoordinateReportService;
import net.shyshkin.study.jasper.service.ReportService;

public class ChartsApplication {

    public static void main(String[] args) {

        try {

            ReportService subjectReportService = new CoordinateReportService();
            subjectReportService.generate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating report");
        }
    }

}
