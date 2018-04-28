package mix_rn.zhai.con.mix_rn

import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

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

  companion object {
    val MODULE_NAME = "RN_Module"
  }
}
