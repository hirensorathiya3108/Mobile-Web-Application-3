package mobile.app.webcatalog.activityes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import mobile.app.webcatalog.AppPersistence
import mobile.app.webcatalog.AppPreference
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityLoginBinding
import mobile.app.webcatalog.extensions.beGone
import mobile.app.webcatalog.extensions.beVisible

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var activity: Activity
    private var isPasswordVisible = false
    private var isAttemptLeft = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        activity = this@LoginActivity
        initViews()
    }

    private fun initViews() {
        binding.tvWrongAttempt.text = activity.getString(R.string.Attempts_left, isAttemptLeft.toString())
        binding.togglePasswordButton.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        val maxLength = 20
        val mustLength = 4
        binding.usernameTextInputLayout.isCounterEnabled = false
        binding.passwordTextInputLayout.isCounterEnabled = false
        binding.usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentLength = s?.length ?: 0
                binding.usernameCharacterCountTextView.text = "$currentLength/$maxLength"
                if (currentLength >= mustLength || currentLength == 0){
                    binding.tvUsernameError.beGone()
                    binding.usernameTextInputLayout.boxStrokeColor = ContextCompat.getColor(activity,R.color.black)
                }else{
                    binding.tvUsernameError.beVisible()
                    binding.usernameTextInputLayout.boxStrokeColor = ContextCompat.getColor(activity,R.color.red_text_color)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentLength = s?.length ?: 0
                binding.passwordCharacterCountTextView.text = "$currentLength/$maxLength"
                if (currentLength >= mustLength || currentLength == 0){
                    binding.tvPasswordError.beGone()
                    binding.passwordTextInputLayout.boxStrokeColor = ContextCompat.getColor(activity,R.color.black)
                }else{
                    binding.tvPasswordError.beVisible()
                    binding.passwordTextInputLayout.boxStrokeColor = ContextCompat.getColor(activity,R.color.red_text_color)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun togglePasswordVisibility(isVisible: Boolean) {
        val passwordEditText: TextInputEditText = findViewById(R.id.passwordEditText)
        val togglePasswordButton: ImageButton = findViewById(R.id.togglePasswordButton)

        if (isVisible) {
            // Show password
            passwordEditText.transformationMethod = null
            togglePasswordButton.setImageResource(R.drawable.ic_visibility)
        } else {
            // Hide password
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            togglePasswordButton.setImageResource(R.drawable.ic_visibility_off)
        }

        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.text?.length ?: 0)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.togglePasswordButton ->{
                isPasswordVisible = !isPasswordVisible
                togglePasswordVisibility(isPasswordVisible)
            }

            R.id.btnLogin ->{
                val username = binding.usernameEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                val storeUsername = AppPreference(activity).getPreference(AppPersistence.keys.USERNAME) as String
                val storePassword = AppPreference(activity).getPreference(AppPersistence.keys.PASSWORD) as String


                if (username.isNotEmpty() && password.isNotEmpty()) {
                    // Perform login logic here (e.g., validate credentials)
                    if (storeUsername == username && storePassword == password){
                        // For demonstration purposes, show a toast message
                        startActivity(Intent(activity,WelcomeActivity::class.java))
                        finish()
//                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    }else{
                        // For demonstration purposes, show a toast message
                        Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_SHORT).show()
                        val totalAttempt = isAttemptLeft - 1
                        binding.tvWrongAttempt.text = activity.getString(R.string.Attempts_left, totalAttempt.toString())
                        isAttemptLeft = totalAttempt
                        if (totalAttempt == 0){
                            super.onBackPressed()
                        }
                    }
                } else {
                    Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnClear ->{
                binding.usernameEditText.text?.clear()
                binding.passwordEditText.text?.clear()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}