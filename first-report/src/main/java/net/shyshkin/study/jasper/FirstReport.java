package net.shyshkin.study.jasper;

import net.shyshkin.study.jasper.service.FirstReportService;
import net.shyshkin.study.jasper.service.ReportService;

public class FirstReport {

    public static void main(String[] args) {

        try {

            ReportService firstReportService = new FirstReportService();
            firstReportService.generate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating report");
        }
    }

}
