package com.adach.signalgen.charts;

import com.adach.signalgen.signal.impl.RealSignal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartGenerator {

  public JFreeChart generateRealSignalGraph(RealSignal realSignal) {

    XYSeries series = new XYSeries("XYGraph");
    double t = realSignal.getStartTime();
    double timeStep = 1 / realSignal.getSamplingFrequency();
    for (Double realValue : realSignal.getRealValues()) {
      series.add(t, realValue);
      t += timeStep;
    }

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(series);

    JFreeChart chart;
    if (realSignal.isDiscrete()) {
      chart = ChartFactory.createScatterPlot(
          realSignal.getTitle(),
          "t [s]",
          "A",
          dataset,
          PlotOrientation.VERTICAL,
          false,
          false,
          false
      );
    } else {
      chart = ChartFactory.createXYLineChart(
          realSignal.getTitle(),
          "t [s]",
          "A",
          dataset,
          PlotOrientation.VERTICAL,
          false,
          false,
          false
      );
    }

    configureChart(chart);
    setAxesMargins(chart, series);
    return chart;
  }

  public JFreeChart generateRealSignalHistogram(RealSignal realSignal, int binsNumber) {
    double[] values = realSignal.getRealValues().stream().mapToDouble(Double::doubleValue)
        .toArray();
    HistogramDataset dataset = new HistogramDataset();
    dataset.setType(HistogramType.FREQUENCY);
    dataset.addSeries("Series", values, binsNumber);
    JFreeChart chart = ChartFactory
        .createHistogram("Histogram", "A", "n", dataset, PlotOrientation.VERTICAL, false, false,
            false);
    configureChart(chart);
    setBarsStyle(chart);
    return chart;
  }

  public void saveChartToFile(JFreeChart chart, String path) {
    try {
      ChartUtils.saveChartAsPNG(new File(path), chart, 1280, 720);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void configureChart(JFreeChart chart) {
    // Get plot object
    XYPlot plot = (XYPlot) chart.getPlot();

    // Set background color
    plot.setBackgroundPaint(new Color(240, 240, 240));

    // Disable gridlines
    plot.setDomainGridlinesVisible(false);
    plot.setRangeGridlinesVisible(false);

    // Enable zero baselines
    plot.setDomainZeroBaselineVisible(true);
    plot.setRangeZeroBaselineVisible(true);

    // Color zero baselines
    Color linesColor = new Color(120, 120, 120);
    plot.setDomainZeroBaselinePaint(linesColor);
    plot.setRangeZeroBaselinePaint(linesColor);

    // Set zero baselines stroke
    Stroke stroke = new BasicStroke(
        0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
        0.0f, new float[]{10.0f, 10.0f}, 0.0f);
    plot.setDomainZeroBaselineStroke(stroke);
    plot.setRangeZeroBaselineStroke(stroke);

    // Set Y-axis label angle
    ValueAxis yAxis = plot.getRangeAxis();
    yAxis.setLabelAngle(Math.PI / 2);

  }

  private void setAxesMargins(JFreeChart chart, XYSeries series) {
    // Get plot object
    XYPlot plot = (XYPlot) chart.getPlot();

    // Get axes objects
    ValueAxis xAxis = plot.getDomainAxis();
    ValueAxis yAxis = plot.getRangeAxis();

    // Set axes margins
    double xMargin = (series.getMaxX() - series.getMinX()) * 0.02;
    if (xMargin == 0) {
      xMargin = 1;
    }
    double yMargin = (series.getMaxY() - series.getMinY()) * 0.02;
    if (yMargin == 0) {
      yMargin = 1;
    }
    xAxis.setRange(series.getMinX() - xMargin, series.getMaxX() + xMargin);
    yAxis.setRange(series.getMinY() - yMargin, series.getMaxY() + yMargin);
  }

  private void setBarsStyle(JFreeChart chart) {
    // Get plot object
    XYPlot plot = (XYPlot) chart.getPlot();

    // Get bar renderer object
    XYBarRenderer barRenderer = (XYBarRenderer) plot.getRenderer();

    // Disable gradient
    barRenderer.setBarPainter(new StandardXYBarPainter());

    // Enable outlines
    barRenderer.setDrawBarOutline(true);

    // Color outlines
    barRenderer.setSeriesOutlinePaint(0, new Color(120, 120, 120));

    // Set outlines stroke
    Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    barRenderer.setSeriesOutlineStroke(0, stroke);
  }
}
