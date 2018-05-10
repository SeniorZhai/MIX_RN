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
import android.os.Environment
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.text
import java.io.File
import java.io.IOException
import java.util.Random


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

  fun down(view: View) {
    val index = random.nextInt(urlArray.size) + 1
    text.text = "目标 version.$index"
    val file = File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
        "${File.separatorChar}$index${File.separatorChar}index.android.bundle")

    if (file.exists()) {
      (application as MyApplication).jsBundle = file
    } else {
      Toast.makeText(this, "不存在，需要下载", Toast.LENGTH_SHORT).show()
      download(file, index)
    }
  }

  private val random = Random()

  fun download(file: File, index: Int) {
    val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val request = DownloadManager.Request(Uri.parse(urlArray[index - 1]))
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
    request.allowScanningByMediaScanner()
    request.setAllowedNetworkTypes(
        DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
    request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS,
        "index$index.zip")
    downloadManager.enqueue(request)
    registerReceiver(object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
          try {
            FileUtil.unzip(
                File(this@MainActivity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                    "index$index.zip"),
                File(file.parent))
            (application as MyApplication).jsBundle = file
            Log.d("---", file.absolutePath)
          } catch (e: IOException) {
            Log.d("---", e.localizedMessage)
          }
        }
      }
    }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

  }

  val urlArray = arrayListOf(
      "https://raw.githubusercontent.com/SeniorZhai/MIX_RN/master/static/1/index.zip",
      "https://raw.githubusercontent.com/SeniorZhai/MIX_RN/master/static/2/index.zip",
      "https://raw.githubusercontent.com/SeniorZhai/MIX_RN/master/static/3/index.zip")
}
