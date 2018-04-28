package com.adach.signalgen.operations;

import com.adach.signalgen.signal.Signal;

public interface SignalOps<T extends Signal> {

  T addSignals(T signalA, T signalB);

  T subtractSignals(T signalA, T signalB);

  T multiplySignals(T signalA, T signalB);

  T divideSignals(T signalA, T signalB);
}
