package mobile.app.webcatalog

import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson
import android.content.Context as Context1
import mobile.app.webcatalog.AppPersistence as AppPersistence1

class AppPersistence(context: Context1) {

    private val KEY = "mobile_web_prefs"

    enum class keys {
        USERNAME,PASSWORD
    }

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(KEY, Context1.MODE_PRIVATE)
    }

    companion object {
        private var mAppPersistance: AppPersistence1? = null

        fun start(context: Context1): AppPersistence1 {
            if (mAppPersistance == null) {
                mAppPersistance = AppPersistence1(context)
            }
            return mAppPersistance as AppPersistence1
        }
    }

    operator fun get(key: Enum<*>): Any? {
        val all = sharedPreferences!!.all
        return all[key.toString()]
    }

    fun save(key: Enum<*>, `val`: Any) {
        val editor = sharedPreferences!!.edit()
        if (`val` is Int) {
            editor.putInt(key.toString(), `val`)
        } else if (`val` is String) {
            editor.putString(key.toString(), `val`.toString())
        } else if (`val` is Float) {
            editor.putFloat(key.toString(), `val`)
        } else if (`val` is Long) {
            editor.putLong(key.toString(), `val`)
        } else if (`val` is Boolean) {
            editor.putBoolean(key.toString(), `val`)
        } else if (`val` is Parcelable) {
            editor.putString(key.toString(), getGson().toJson(`val`)).apply()
        }
        editor.apply()
    }

    fun getGson(): Gson {
        return Gson()
    }

    fun remove(key: Enum<*>) {
        val editor = sharedPreferences!!.edit()
        editor.remove(key.toString())
        editor.apply()
    }

    fun removeAll() {
        val editor = sharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }
}