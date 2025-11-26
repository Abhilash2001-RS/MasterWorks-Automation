package com.aurigo.masterworks.testframework.utilities.helper;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.util.HashMap;

public class ChartHelper {

    /**
     * Generates a pie chart for Test Run Result
     *
     * @param values - Plot points
     * @param width  - Width of the chart
     * @param height - Height of the chart
     * @return a pie-chart
     */
    public static PieChart generateRunResultPieChart(HashMap<String, Integer> values, int width, int height) {
        // Create Chart
        PieChart chart = new PieChartBuilder().width(width).height(height).theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Percentage);
        chart.getStyler().setAnnotationDistance(1.1);
        chart.getStyler().setPlotContentSize(.6);
        chart.getStyler().setStartAngleInDegrees(90);
        chart.getStyler().setDrawAllAnnotations(true);
        chart.getStyler().setShowWithinAreaPoint(true);
        chart.getStyler().setChartBackgroundColor(Color.white);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setPlotBackgroundColor(Color.white);
        chart.getStyler().setPlotBorderVisible(false);

        //Add Series

        for (var item : values.entrySet()) {
            switch (item.getKey()) {
                case "Passed": {
                    chart.addSeries(item.getKey(), item.getValue()).setFillColor(Color.green);
                    break;
                }
                case "Failed": {
                    chart.addSeries(item.getKey(), item.getValue()).setFillColor(Color.red);
                    break;
                }
                case "Blocked": {
                    chart.addSeries(item.getKey(), item.getValue()).setFillColor(Color.darkGray);
                    break;
                }
                case "Incomplete": {
                    chart.addSeries(item.getKey(), item.getValue()).setFillColor(Color.lightGray);
                    break;
                }
            }
        }
        return chart;
    }
}
