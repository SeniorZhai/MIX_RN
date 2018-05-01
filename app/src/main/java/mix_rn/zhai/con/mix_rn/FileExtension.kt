package mix_rn.zhai.con.mix_rn

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.support.v4.os.EnvironmentCompat
import java.io.File

private fun isAvailable(): Boolean {
  val state = Environment.getExternalStorageState()
  if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
    return true
  }
  return false
}

private fun hasWritePermission(): Boolean {
  return ContextCompat.checkSelfPermission(MyApplication.appContext,
      Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
}

private fun Context.getAppPath(): File {
  return if (!hasWritePermission()) {
    getBestAvailableCacheRoot()
  } else if (isAvailable()) {
    File(
        "${Environment.getExternalStorageDirectory()}${File.separator}MIX_RN${File.separator}Media${File.separator}"
    )
  } else {
    var externalFile: Array<File>? = ContextCompat.getExternalFilesDirs(this, null)
    if (externalFile == null) {
      externalFile = arrayOf(this.getExternalFilesDir(null))
    }
    val root = File(
        "${externalFile[0]}${File.separator}MIX_RN${File.separator}Media${File.separator}")
    root.mkdirs()
    return if (root.exists()) {
      root
    } else {
      getBestAvailableCacheRoot()
    }
  }
}

private fun Context.getBestAvailableCacheRoot(): File {
  val roots = ContextCompat.getExternalCacheDirs(this)
  roots.filter { it != null && Environment.MEDIA_MOUNTED == EnvironmentCompat.getStorageState(it) }
      .forEach { return it }
  return this.cacheDir
}

fun Context.getBundlePath(): File {
  val root = getAppPath()
  val file = File("$root${File.separator}Bundle")
  if (!file.exists()) {
    file.mkdirs()
  }
  return file
}