package mobile.app.webcatalog.activityes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import mobile.app.webcatalog.AppPersistence
import mobile.app.webcatalog.AppPreference
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activity: Activity
    private lateinit var appPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activity = this@MainActivity
        appPreference = AppPreference(activity)
        initViews()
    }

    private fun initViews() {
        checkNull()
        binding.btnMulti.setOnClickListener(this)
        binding.btnCity.setOnClickListener(this)
        binding.btnScrolling.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btnCalculator.setOnClickListener(this)
    }

    private fun checkNull() {
        if (appPreference.getPreference(AppPersistence.keys.USERNAME) == null) {
            appPreference.setPreference(AppPersistence.keys.USERNAME, "Admin")
        }
        if (appPreference.getPreference(AppPersistence.keys.PASSWORD) == null) {
            appPreference.setPreference(AppPersistence.keys.PASSWORD, "Admin")
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMulti -> {
                sendIntent(ButtonActivity::class.java)
            }

            R.id.btnCity -> {
                sendIntent(CountriesCityActivity::class.java)
            }

            R.id.btnScrolling -> {
                sendIntent(CatalogActivity::class.java)
            }

            R.id.btnLogin -> {
                sendIntent(LoginActivity::class.java)
            }

            R.id.btnCalculator -> {
                sendIntent(CalculatorActivity::class.java)
            }
        }
    }

    private fun sendIntent(targetActivity: Class<*>) {
        val intent = Intent(activity, targetActivity)
        startActivity(intent)
    }
}