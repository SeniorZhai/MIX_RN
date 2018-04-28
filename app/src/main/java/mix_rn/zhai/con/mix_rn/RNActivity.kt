package mix_rn.zhai.con.mix_rn

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler


class RNActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
  private var mReactRootView: ReactRootView? = null
  private var mReactInstanceManager: ReactInstanceManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mReactRootView = ReactRootView(this)
    mReactInstanceManager = (application as MyApplication).reactNativeHost.reactInstanceManager

    setContentView(mReactRootView)
    mReactRootView?.startReactApplication(
        mReactInstanceManager, "APP", null)

  }

  override fun onBackPressed() {
    if (mReactInstanceManager != null) {
      mReactInstanceManager?.onBackPressed()
    } else {
      super.onBackPressed()
    }
  }

  override fun invokeDefaultOnBackPressed() {
    super.onBackPressed()
  }

  override fun onPause() {
    super.onPause()

    if (mReactInstanceManager != null) {
      mReactInstanceManager!!.onHostPause(this)
    }
  }

  override fun onResume() {
    super.onResume()

    if (mReactInstanceManager != null) {
      mReactInstanceManager!!.onHostResume(this, this)
    }
  }

  override fun onDestroy() {
    super.onDestroy()

    if (mReactInstanceManager != null) {
      mReactInstanceManager!!.onHostDestroy(this)
    }
  }

  override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
    if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
      mReactInstanceManager?.showDevOptionsDialog()
      return true
    }
    return super.onKeyUp(keyCode, event)
  }
}
