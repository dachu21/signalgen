package com.adach.signalgen.serialization;

import com.adach.signalgen.signal.Signal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SignalSerializer<T extends Signal> {

  public void saveSignalToFile(T signal, String path) {
    try (FileOutputStream fos = new FileOutputStream(path)) {
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(signal);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public T loadSignalFromFile(String path) {
    try (FileInputStream fis = new FileInputStream(path)) {
      ObjectInputStream ois = new ObjectInputStream(fis);
      T signal = (T) ois.readObject();
      ois.close();
      return signal;
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      throw new RuntimeException(e);
    }
  }
}
