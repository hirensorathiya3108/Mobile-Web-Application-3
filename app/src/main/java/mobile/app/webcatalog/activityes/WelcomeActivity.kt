package mobile.app.webcatalog.activityes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import mobile.app.webcatalog.AppPersistence
import mobile.app.webcatalog.AppPreference
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)
        activity = this@WelcomeActivity
        initViews()
    }

    private fun initViews() {
        binding.tvUserName.text = AppPreference(activity).getPreference(AppPersistence.keys.USERNAME) as String
        binding.btnLogOut.setOnClickListener {
            super.onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}