package mix_rn.zhai.con.mix_rn

import android.support.annotation.Nullable
import android.util.ArrayMap
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule


/**
 * 通信Module类
 */
class RNModule
(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return MODULE_NAME
  }

  /**
   * RN调用Native的方法
   */
  @ReactMethod
  fun rnCallNative(msg: String) {
    Toast.makeText(reactApplicationContext, msg, Toast.LENGTH_SHORT).show()
  }

  // DeviceEventEmitter 单向消息
  fun callRn(msg: String) {
    reactApplicationContext.getJSModule(
        DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit("EVENT", msg)
  }

  @Nullable
  override fun getConstants(): Map<String, Any>? {
    val params = ArrayMap<String, Any>()
    params["Constant"] = "我是常量，传递给RN"
    return params
  }

  companion object {
    val MODULE_NAME = "RN_Module"
  }
}
