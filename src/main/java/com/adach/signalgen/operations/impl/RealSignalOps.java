package com.adach.signalgen.operations.impl;

import com.adach.signalgen.operations.MathOps;
import com.adach.signalgen.operations.SignalOps;
import com.adach.signalgen.signal.impl.RealSignal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RealSignalOps implements SignalOps<RealSignal> {

  @Override
  public RealSignal addSignals(RealSignal signalA, RealSignal signalB) {
    return operate(signalA, signalB, MathOps.ADD);
  }

  @Override
  public RealSignal subtractSignals(RealSignal signalA, RealSignal signalB) {
    return operate(signalA, signalB, MathOps.SUB);
  }

  @Override
  public RealSignal multiplySignals(RealSignal signalA, RealSignal signalB) {
    return operate(signalA, signalB, MathOps.MUL);
  }

  @Override
  public RealSignal divideSignals(RealSignal signalA, RealSignal signalB) {
    return operate(signalA, signalB, MathOps.DIV);
  }

  private RealSignal operate(RealSignal signalA, RealSignal signalB,
      MathOps operation) {
    if (areCompatible(signalA, signalB)) {
      List<Double> newValues = new ArrayList<>();
      Iterator<Double> iteratorA = signalA.getRealValues().iterator();
      Iterator<Double> iteratorB = signalB.getRealValues().iterator();
      while (iteratorA.hasNext() && iteratorB.hasNext()) {
        switch (operation) {
          case ADD:
            newValues.add(iteratorA.next() + iteratorB.next());
            break;
          case SUB:
            newValues.add(iteratorA.next() - iteratorB.next());
            break;
          case MUL:
            newValues.add(iteratorA.next() * iteratorB.next());
            break;
          case DIV:
            double a = iteratorA.next();
            double b = iteratorB.next();
            if (Math.abs(b) > 0.0001) {
              newValues.add(a / b);
            } else {
              newValues.add(10000D);
            }
            break;
        }
      }
      double newPeriod = signalA.getPeriod() == signalB.getPeriod() ? signalA.getPeriod() : 0;
      return new RealSignal(setNewTitle(operation), signalA.isDiscrete(), newPeriod,
          signalA.getStartTime(), signalA.getSamplingFrequency(), newValues);
    } else {
      throw new UnsupportedOperationException(
          "Can't operate on signals with different parameters.");
    }
  }

  private boolean areCompatible(RealSignal signalA, RealSignal signalB) {
    return signalA.isDiscrete() == signalB.isDiscrete() &&
        signalA.getSamplingFrequency() == signalB.getSamplingFrequency() &&
        signalA.getStartTime() == signalB.getStartTime();
  }

  private String setNewTitle(MathOps operation) {
    switch (operation) {
      case ADD:
        return "A + B";
      case SUB:
        return "A - B";
      case MUL:
        return "A * B";
      case DIV:
        return "A / B";
      default:
        return "";
    }
  }
}
