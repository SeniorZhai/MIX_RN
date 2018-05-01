package mix_rn.zhai.con.mix_rn

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
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
        }
      }
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
      grantResults: IntArray) {
    if (requestCode == 0x02) {
      if (grantResults.find { it != PackageManager.PERMISSION_GRANTED } == null) {
      }
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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

  fun check(view: View) {
//    if (ActivityCompat.checkSelfPermission(this,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//      ActivityCompat.requestPermissions(this,
//          arrayOf(Manifest.permission.READ_CONTACTS),
//          0x02)
//    } else {
//      down()
//    }
  }

  fun down(view: View) {
    val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    //
    val request = DownloadManager.Request(Uri.parse(
        "https://raw.githubusercontent.com/SeniorZhai/MIX_RN/master/static/1/index.bundle"))
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
    request.allowScanningByMediaScanner()
    request.setAllowedNetworkTypes(
        DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
    request.setDestinationUri(Uri.fromFile(getBundlePath()))
    downloadManager.enqueue(request)
    registerReceiver(object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        //
        if (intent?.action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

        }
      }
    }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

  }
}
