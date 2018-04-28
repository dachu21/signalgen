package com.adach.signalgen.app.ui;

import com.adach.signalgen.signal.impl.RealSignal;

public interface UserInterface {

  void saveSignalToFile(RealSignal signal);

  RealSignal getSignalToDraw();

  int getHistogramBinsNumber();

  void showSignalCalculations(RealSignal signal);
}
