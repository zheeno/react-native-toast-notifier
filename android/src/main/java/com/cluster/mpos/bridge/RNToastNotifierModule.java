
package com.cluster.mpos.bridge;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.widget.Toast;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import java.util.Map;
import java.util.HashMap;

public class RNToastNotifierModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  private final ReactApplicationContext reactContext;

  public RNToastNotifierModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNToastNotifier";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }

  /**
   * PUBLIC REACT API
   *
   * isAvailable() Returns true if the fingerprint reader can be used
   */

  @ReactMethod
  public void show(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  @ReactMethod
  public void isAvailable(final Promise promise) {
    try {
      FingerprintManager manager = getFingerprintManager();
      boolean v = (manager != null && manager.isHardwareDetected() && manager.hasEnrolledFingerprints());
      promise.resolve(v);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  /**
   * Returns fingerprint manager or null
   * 
   * @see https://stackoverflow.com/questions/34409969/how-to-check-device-compatibility-for-finger-print-authentication-in-android
   */
  private FingerprintManager getFingerprintManager() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return (FingerprintManager) reactContext.getSystemService(reactContext.FINGERPRINT_SERVICE);
    } else {
      return null;
    }
  }

}