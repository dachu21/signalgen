package com.adach.signalgen.utils;

import com.adach.signalgen.signal.impl.RealSignal;
import java.util.List;

public class RealSignalUtils {

  private RealSignalUtils() {
  }

  public static RealSignal reduceIncompletePeriods(RealSignal signal) {
    double period = signal.getPeriod();
    if (period == 0) {
      return signal;
    } else {
      double timeStep = 1 / signal.getSamplingFrequency();
      double duration = signal.getRealValues().size() * timeStep;
      int periodsNumber = (int) (duration / period);
      int newValuesNumber = (int) (periodsNumber * period / timeStep);
      List<Double> newValues = signal.getRealValues().subList(0, newValuesNumber);
      return new RealSignal(signal.getTitle(), signal.isDiscrete(), signal.getPeriod(),
          signal.getStartTime(), signal.getSamplingFrequency(), newValues);
    }
  }
}
