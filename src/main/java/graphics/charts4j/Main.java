package graphics.charts4j;

/**
 * pdf-creator
 * Created on 28.05.17.
 */
public class Main {
    public static void main(String[] args) {
        PercentChart percentChart = new PercentChart();

        final String chartUrl = percentChart.getChartUrl(45.0);

        System.out.println(chartUrl);
    }
}
