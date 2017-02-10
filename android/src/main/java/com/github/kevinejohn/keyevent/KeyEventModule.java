package com.github.kevinejohn.keyevent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.view.KeyEvent; 

/**
 * Created by Kevin Johnson on 8/15/16.
 * Added unicodeChar by semiautomatix on 10/02/17
 */
public class KeyEventModule extends ReactContextBaseJavaModule {
    private ReactContext mReactContext;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mJSModule = null;

    private static KeyEventModule instance = null;

    public static KeyEventModule initKeyEventModule(ReactApplicationContext reactContext) {
        instance = new KeyEventModule(reactContext);
        return instance;
    }

    public static KeyEventModule getInstance() {
        return instance;
    }

    public void onKeyDownEvent(int keyCode, KeyEvent event) {
        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        WritableMap params = Arguments.createMap();
        params.putInt("keyCode", keyCode);
        params.putInt("unicodeChar", event.getUnicodeChar());
        mJSModule.emit("onKeyDown", params);
    };

    public void onKeyUpEvent(int keyCode, KeyEvent event) {
        if (mJSModule == null) {
            mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        WritableMap params = Arguments.createMap();
        params.putInt("keyCode", keyCode);
        params.putInt("unicodeChar", event.getUnicodeChar());
        mJSModule.emit("onKeyUp", params);
    };

    protected KeyEventModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "KeyEventModule";
    }
}
