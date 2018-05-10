package mix_rn.zhai.con.mix_rn

import android.app.Application
import android.content.Context
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import java.io.File
import java.util.Arrays

class MyApplication : Application(), ReactApplication {

  private val mReactNativeHost = object : ReactNativeHost(this) {

    override fun getUseDeveloperSupport(): Boolean {
      return BuildConfig.DEBUG
    }

    override fun getJSBundleFile(): String? {
      if (jsBundle?.exists() == true) {
        return jsBundle!!.absolutePath
      }
      return super.getJSBundleFile()
    }

    override fun getPackages(): List<ReactPackage> {
      return Arrays.asList(MainReactPackage(), mCommPackage)
    }

  }

  var jsBundle: File? = null

  override fun onCreate() {
    super.onCreate()
    appContext = applicationContext
    SoLoader.init(this, false)
  }

  override fun getReactNativeHost(): ReactNativeHost {
    return mReactNativeHost
  }

  fun getRNPackage(): RNPackage {
    return mCommPackage
  }

  companion object {

    lateinit var appContext: Context

    val mCommPackage = RNPackage()
  }
}
