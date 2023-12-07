package mobile.app.webcatalog.activityes

import mobile.app.webcatalog.extensions.beGone
import mobile.app.webcatalog.extensions.beVisible
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityButtonBinding

class ButtonActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityButtonBinding
    private lateinit var activity: Activity
    private var isAndroid: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_button)
        activity = this@ButtonActivity
        initViews()
    }

    private fun initViews() {
        binding.btnTopLeft.setOnClickListener(this)
        binding.btnTopRight.setOnClickListener(this)
        binding.btnBottomLeft.setOnClickListener(this)
        binding.btnBottomRight.setOnClickListener(this)
        binding.ivAndroidBtn.setOnClickListener(this)
    }

    private fun visibleMessage(isAndroid: Boolean = false) {
        if (isAndroid) {
            if (binding.tvClickMessage.isVisible) binding.tvClickMessage.beGone()
            if (binding.tvAndroidMessage.isGone) binding.tvAndroidMessage.beVisible()
        } else {
            if (binding.tvClickMessage.isGone) binding.tvClickMessage.beVisible()
            if (binding.tvAndroidMessage.isVisible) binding.tvAndroidMessage.beGone()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTopLeft -> {
                visibleMessage()
                binding.tvClickMessage.text = activity.getString(
                    R.string.click_message,
                    activity.getString(R.string.jacob)
                )
            }

            R.id.btnTopRight -> {
                visibleMessage()
                binding.tvClickMessage.text = activity.getString(
                    R.string.click_message,
                    activity.getString(R.string.alfie)
                )
            }

            R.id.btnBottomLeft -> {
                visibleMessage()
                binding.tvClickMessage.text = activity.getString(
                    R.string.click_message,
                    activity.getString(R.string.arthur)
                )
            }

            R.id.btnBottomRight -> {
                visibleMessage()
                binding.tvClickMessage.text = activity.getString(
                    R.string.click_message,
                    activity.getString(R.string.harry)
                )
            }

            R.id.ivAndroidBtn -> {
                visibleMessage(isAndroid)
                binding.tvAndroidMessage.text = activity.getString(R.string.android_click_message)
            }
        }
    }
}