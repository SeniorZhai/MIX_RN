package mix_rn.zhai.con.mix_rn

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule

/**
 * 通信Module类
 */
class RNModule
(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return MODULE_NAME
  }

  companion object {
    val MODULE_NAME = "RN_Module"
  }
}
