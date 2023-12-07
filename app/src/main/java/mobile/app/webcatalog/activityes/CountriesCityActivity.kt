package mobile.app.webcatalog.activityes

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityCountriesCityBinding
import mobile.app.webcatalog.extensions.beGone
import mobile.app.webcatalog.extensions.beVisible

class CountriesCityActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCountriesCityBinding
    private lateinit var activity: Activity
    private var selectedCountry: String = ""
    private var selectedCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_countries_city)
        activity = this@CountriesCityActivity
        initViews()
    }

    private fun initViews() {
        cityDropDownMenu()
        binding.rbSpain.isClickable = false
        binding.rbItaly.isClickable = false
        binding.rbFrance.isClickable = false
        binding.llSpain.setOnClickListener(this)
        binding.llItaly.setOnClickListener(this)
        binding.llFrance.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun cityDropDownMenu() {
        val lesothoCity = arrayOf("Maseru", "Teyateyaneng", "Mafeteng", "Hlotse", "Quthing")
        val botswanaCity = arrayOf("Gaborone", "Francistown", "Molepolole", "Maun", "Mogoditshane")
        val namibiaCity = arrayOf("Windhoek", "Walvis Bay", "Swakopmund", "Henties Bay", "Omaruru")
        val finalCity = when (selectedCountry) {
            activity.getString(R.string.lesotho) -> {
               lesothoCity
            }
            activity.getString(R.string.botswana) -> {
               botswanaCity
            }
            else -> {
               namibiaCity
            }
        }
        // Create a custom ArrayAdapter using the custom spinner item layout
        val adapter = ArrayAdapter(this, R.layout.list_item,finalCity)

        // Apply the adapter to the spinner
        binding.autoComplete.setAdapter(adapter)

        // Set a listener to perform actions based on the selected item
        binding.autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
                selectedCity = itemSelected.toString()
                Toast.makeText(activity, "Selected city: $itemSelected", Toast.LENGTH_SHORT).show()
            }
    }

    private fun selectCountry(city: String) {
        when (city) {
            activity.getString(R.string.lesotho) -> {
                allSelectClear()
                binding.rbSpain.isChecked = true
                selectedCountry = activity.getString(R.string.lesotho)
            }

            activity.getString(R.string.botswana) -> {
                allSelectClear()
                binding.rbItaly.isChecked = true
                selectedCountry = activity.getString(R.string.botswana)
            }

            activity.getString(R.string.namibia) -> {
                allSelectClear()
                binding.rbFrance.isChecked = true
                selectedCountry = activity.getString(R.string.namibia)
            }
        }
        cityDropDownMenu()
    }

    private fun allSelectClear() {
        binding.rbSpain.isChecked = false
        binding.rbItaly.isChecked = false
        binding.rbFrance.isChecked = false
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.llSpain -> {
                selectCountry(activity.getString(R.string.lesotho))
            }

            R.id.llItaly -> {
                selectCountry(activity.getString(R.string.botswana))
            }

            R.id.llFrance -> {
                selectCountry(activity.getString(R.string.namibia))
            }

            R.id.btnSubmit -> {
                if (selectedCountry != "" && selectedCity != "") {
                    binding.llResponse.beVisible()
                    binding.tvResponse.text =
                        activity.getString(R.string.selected_country, selectedCountry)
                    binding.tvResponse1.text =
                        activity.getString(R.string.selected_city, selectedCity)
                } else {
                    binding.llResponse.beGone()
                    Toast.makeText(
                        activity,
                        getString(R.string.not_selected_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

}