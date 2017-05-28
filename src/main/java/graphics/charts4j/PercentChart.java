package graphics.charts4j;

/**
 * pdf-creator
 * Created on 28.05.17.
 */

import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Plots;

@lombok.Data
public class PercentChart {
    private Double maxProgress = 100.0;
    private Double highPercent = 20.0;
    private Double mediumPercent = 50.0;
    private Color backgroundColor = Color.BLUEVIOLET;

    public String getChartUrl(Double now) {
        Double delta = maxProgress - now;
        Color color = null;
        if (delta <= highPercent)
            color = Color.GREEN;
        else if (delta <= mediumPercent)
            color = Color.YELLOW;
        else
            color = Color.RED;
        BarChartPlot currentCoverage = Plots.newBarChartPlot(
                Data.newData(now), color);
        BarChartPlot freeCoverage = Plots.newBarChartPlot(
                Data.newData(delta), backgroundColor);
        BarChart barChart = GCharts.newBarChart(currentCoverage, freeCoverage);
        barChart.setSize(200, 30);
        barChart.setTransparency(100);
        barChart.setDataStacked(true);
        barChart.setHorizontal(true);
        return barChart.toURLString();
    }
}
