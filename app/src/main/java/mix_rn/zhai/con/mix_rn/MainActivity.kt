package mix_rn.zhai.con.mix_rn

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (requestCode == 0x01) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.canDrawOverlays(this)) {
          // SYSTEM_ALERT_WINDOW permission not granted...
        }
      }
    }
  }

  fun skip(view: View) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (!Settings.canDrawOverlays(this)) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName"))
        startActivityForResult(intent, 0x01)
      } else {
        val intent = Intent(this, RNActivity::class.java)
        startActivity(intent)
      }
    }
  }
}
