package mix_rn.zhai.con.mix_rn;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

  public static Context appContext;

  @Override public void onCreate() {
    super.onCreate();
    appContext = getApplicationContext();
  }
}