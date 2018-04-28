package com.adach.signalgen.app.ui;

import com.adach.signalgen.calculator.RealSignalCalculator;
import com.adach.signalgen.calculator.SignalCalculator;
import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.generator.impl.FullWaveRectifiedSine;
import com.adach.signalgen.generator.impl.GaussianNoise;
import com.adach.signalgen.generator.impl.HalfWaveRectifiedSine;
import com.adach.signalgen.generator.impl.ImpulseNoise;
import com.adach.signalgen.generator.impl.KroneckerDelta;
import com.adach.signalgen.generator.impl.PulseWave;
import com.adach.signalgen.generator.impl.SineWave;
import com.adach.signalgen.generator.impl.SymmetricalPulseWave;
import com.adach.signalgen.generator.impl.TriangleWave;
import com.adach.signalgen.generator.impl.UniformNoise;
import com.adach.signalgen.generator.impl.UnitStep;
import com.adach.signalgen.operations.SignalOps;
import com.adach.signalgen.operations.impl.RealSignalOps;
import com.adach.signalgen.parameters.SignalParameters;
import com.adach.signalgen.serialization.SignalSerializer;
import com.adach.signalgen.signal.impl.RealSignal;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class CLI implements UserInterface {

  private static final String HISTOGRAM_PROPS = "config/histogram.properties";
  private static final String FIRST_PROPS = "config/first.properties";
  private static final String SECOND_PROPS = "config/second.properties";

  private final SignalSerializer<RealSignal> serializer = new SignalSerializer<>();
  private final SignalOps<RealSignal> signalOps = new RealSignalOps();
  private final SignalCalculator<RealSignal> calculator = new RealSignalCalculator();

  private final List<String> args;

  public CLI(List<String> args) {
    if (!(args.size() == 3 || args.size() == 6)) {
      throw new RuntimeException("Invalid arguments number");
    }
    this.args = args;
  }

  @Override
  public void saveSignalToFile(RealSignal signal) {
    serializer.saveSignalToFile(signal, args.get(args.size() - 1));
  }

  @Override
  public RealSignal getSignalToDraw() {
    RealSignal firstSignal, secondSignal, signalToDraw;

    // First signal configuration
    switch (args.get(0)) {
      case "g":
        firstSignal = getSignalFromGenerator(Integer.parseInt(args.get(1)),
            loadProperties(FIRST_PROPS));
        break;
      case "f":
        firstSignal = serializer.loadSignalFromFile(args.get(1));
        break;
      default:
        throw new RuntimeException("Invalid first signal argument (choose 'g' or 'f').");
    }

    // Second signal configuration (if present)
    if (args.size() == 6) {
      switch (args.get(2)) {
        case "g":
          secondSignal = getSignalFromGenerator(Integer.parseInt(args.get(3)),
              loadProperties(SECOND_PROPS));
          break;
        case "f":
          secondSignal = serializer.loadSignalFromFile(args.get(3));
          break;
        default:
          throw new RuntimeException("Invalid second signal argument (choose 'g' or 'f').");
      }

      switch (args.get(4)) {
        case "ADD":
          signalToDraw = signalOps.addSignals(firstSignal, secondSignal);
          break;
        case "SUB":
          signalToDraw = signalOps.subtractSignals(firstSignal, secondSignal);
          break;
        case "MUL":
          signalToDraw = signalOps.multiplySignals(firstSignal, secondSignal);
          break;
        case "DIV":
          signalToDraw = signalOps.divideSignals(firstSignal, secondSignal);
          break;
        default:
          throw new RuntimeException(
              "Invalid operation argument (choose 'ADD', 'SUB', 'MUL' or 'DIV').");
      }
    } else {
      signalToDraw = firstSignal;
    }

    return signalToDraw;
  }

  @Override
  public int getHistogramBinsNumber() {
    Properties properties = loadProperties(HISTOGRAM_PROPS);
    return Integer.parseInt(properties.getProperty("bins"));
  }

  @Override
  public void showSignalCalculations(RealSignal signal) {

    System.out
        .println("Average: "
            + Math.round(calculator.average(signal) * 100.0) / 100.0);
    System.out
        .println("Magnitude: "
            + Math.round(calculator.magnitude(signal) * 100.0) / 100.0);
    System.out
        .println("Power: "
            + Math.round(calculator.power(signal) * 100.0) / 100.0);
    System.out
        .println("Variance: "
            + Math.round(calculator.variance(signal) * 100.0) / 100.0);
    System.out
        .println("Effective value: "
            + Math.round(calculator.effectiveValue(signal) * 100.0) / 100.0);
  }

  private RealSignal getSignalFromGenerator(Integer generatorNumber, Properties properties) {
    SignalGenerator generator;
    SignalParameters params = new SignalParameters(properties);
    switch (generatorNumber) {
      case 1:
        generator = new UniformNoise();
        return generator.generateRealSignal(params, "Uniform Noise");
      case 2:
        generator = new GaussianNoise();
        return generator.generateRealSignal(params, "Gaussian Noise");
      case 3:
        generator = new SineWave();
        return generator.generateRealSignal(params, "Sine Wave");
      case 4:
        generator = new HalfWaveRectifiedSine();
        return generator
            .generateRealSignal(params, "Half Wave Rectified Sine");
      case 5:
        generator = new FullWaveRectifiedSine();
        return generator
            .generateRealSignal(params, "Full Wave Rectified Sine");
      case 6:
        generator = new PulseWave();
        return generator.generateRealSignal(params, "Pulse Wave");
      case 7:
        generator = new SymmetricalPulseWave();
        return generator.generateRealSignal(params, "Symmetrical Pulse Wave");
      case 8:
        generator = new TriangleWave();
        return generator.generateRealSignal(params, "Triangle Wave");
      case 9:
        generator = new UnitStep();
        return generator.generateRealSignal(params, "Unit Step");
      case 10:
        generator = new KroneckerDelta();
        return generator.generateRealSignal(params, "Kronecker Delta");
      case 11:
        generator = new ImpulseNoise();
        return generator.generateRealSignal(params, "Impulse Noise");
      default:
        throw new RuntimeException("Invalid signal generator number (choose 1-11).");
    }
  }

  private Properties loadProperties(String path) {
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream(path));
    } catch (IOException e) {
      throw new RuntimeException("Properties file not found or not accessible");
    }
    return properties;
  }
}
