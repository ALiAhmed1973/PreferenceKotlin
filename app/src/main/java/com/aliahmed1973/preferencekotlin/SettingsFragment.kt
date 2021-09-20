package com.aliahmed1973.preferencekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        val accountSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accountSettingsPref?.setOnPreferenceClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToAccountSettingsFragment())
            true
        }

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))

        statusPref?.setOnPreferenceChangeListener{pref,newValue->
            val newText= newValue.toString()
            if(newText.isNotEmpty())
            {
                Toast.makeText(requireContext(),newText,Toast.LENGTH_SHORT).show()
                true
            }else
            {
                false
            }
        }

        val notificationValue=sharedPreference.getBoolean(getString(R.string.key_notification),false)
        Toast.makeText(requireContext(),notificationValue.toString(),Toast.LENGTH_SHORT).show()
        val refreshDataValue = sharedPreference.getString(getString(R.string.key_refersh_data_time),"")
        Toast.makeText(requireContext(),refreshDataValue,Toast.LENGTH_SHORT).show()

       val autoPlayValue =sharedPreference.getBoolean(getString(R.string.key_auto_play),false)
        Toast.makeText(requireContext(),autoPlayValue.toString(),Toast.LENGTH_SHORT).show()
    }

}