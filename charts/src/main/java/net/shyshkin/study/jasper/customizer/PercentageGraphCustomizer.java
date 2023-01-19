package net.shyshkin.study.jasper.customizer;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

public class PercentageGraphCustomizer implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart chart, JRChart jasperChart) {
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        renderer.setMaximumBarWidth(.60);  //Set maximum width of barchart to 30 percent
        renderer.setItemMargin(-2);//as much margin decrease that much bar width increase//
        //for adding value ob bar//

    }
}
