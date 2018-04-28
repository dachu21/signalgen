package com.adach.signalgen.app;

import com.adach.signalgen.app.ui.CLI;
import com.adach.signalgen.app.ui.UserInterface;
import com.adach.signalgen.charts.ChartGenerator;
import com.adach.signalgen.signal.impl.RealSignal;
import com.adach.signalgen.utils.RealSignalUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartCanvas;

public class Main extends Application {

  private UserInterface ui;
  private final ChartGenerator chartGenerator = new ChartGenerator();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    ui = new CLI(this.getParameters().getRaw());

    RealSignal signalToDraw = ui.getSignalToDraw();
    RealSignal reducedSignal = RealSignalUtils.reduceIncompletePeriods(signalToDraw);

    ui.saveSignalToFile(signalToDraw);
    ui.showSignalCalculations(reducedSignal);

    JFreeChart graph = chartGenerator.generateRealSignalGraph(signalToDraw);
    JFreeChart histogram = chartGenerator
        .generateRealSignalHistogram(reducedSignal, ui.getHistogramBinsNumber());

    Stage histogramStage = new Stage();
    configureStage(histogramStage);
    fillStage(histogramStage, histogram);
    histogramStage.show();

    configureStage(primaryStage);
    fillStage(primaryStage, graph);
    primaryStage.show();
  }

  private void configureStage(Stage stage) {
    stage.setTitle("Signal Generator");
    stage.setWidth(1280);
    stage.setHeight(720);
    Image icon = new Image(this.getClass().getClassLoader().getResourceAsStream("icon.png"));
    stage.getIcons().add(icon);
  }

  private void fillStage(Stage stage, JFreeChart chart) {
    ChartCanvas chartCanvas = new ChartCanvas(chart);
    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(chartCanvas);
    chartCanvas.widthProperty().bind(stackPane.widthProperty());
    chartCanvas.heightProperty().bind(stackPane.heightProperty());
    stage.setScene(new Scene(stackPane));
  }
}
