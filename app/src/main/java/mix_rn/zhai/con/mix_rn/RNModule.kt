package mix_rn.zhai.con.mix_rn

import android.support.annotation.Nullable
import android.util.ArrayMap
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

  @Nullable
  override fun getConstants(): Map<String, Any>? {
    val params = ArrayMap<String,Any>()
    params["Constant"] = "我是常量，传递给RN"
    return params
  }

  companion object {
    val MODULE_NAME = "RN_Module"
  }
}
