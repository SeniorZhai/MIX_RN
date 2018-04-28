package mix_rn.zhai.con.mix_rn;

import android.app.Application;
import android.content.Context;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application implements ReactApplication {

  public static Context appContext;
  private static MyApplication instance;
  public static final RNPackage mCommPackage = new RNPackage();

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    appContext = getApplicationContext();
    SoLoader.init(this, false);
  }

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

    @Override public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override protected List<ReactPackage> getPackages() {
      return Arrays.asList(new MainReactPackage(), mCommPackage);
    }

    @Override public ReactInstanceManager getReactInstanceManager() {
      return ReactInstanceManager.builder()
          .setApplication(instance)
          .setBundleAssetName("index.android.bundle")
          .setJSMainModulePath("index")
          .addPackages(getPackages())
          .setUseDeveloperSupport(BuildConfig.DEBUG)
          .setInitialLifecycleState(LifecycleState.RESUMED)
          .build();
    }
  };

  @Override public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  /**
   * 获取Application实例
   */
  public static MyApplication getInstance() {
    return instance;
  }
}
