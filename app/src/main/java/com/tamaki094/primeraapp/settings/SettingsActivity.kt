package com.tamaki094.primeraapp.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tamaki094.primeraapp.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object{
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VOLUME = "key_volume"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_MUTE = "key_mute"
        const val ETIQUETA_LOG = "mensaje tamaki094"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime:Boolean= true


    override fun onCreate(savedInstanceState: Bundle?) {

        try
        {
            super.onCreate(savedInstanceState)
            binding = ActivitySettingsBinding.inflate(layoutInflater)
            setContentView(binding.root)



            CoroutineScope(Dispatchers.IO).launch {
                getSettings().filter { firstTime}.collect { settings->
                    if(settings != null){
                        runOnUiThread {
                            binding.switchMute.isChecked = settings.mute
                            binding.switchBlue.isChecked = settings.bluetooth
                            binding.switchDarkMode.isChecked = settings.darkmode
                            binding.rgVolume.setValues(settings.volume.toFloat())

                            firstTime = !firstTime
                        }
                    }
                }
                Log.i(ETIQUETA_LOG, "se ejecuta corutina")
            }

            Log.i(ETIQUETA_LOG, "fuera corutina")

            initUI()
        }
        catch (ex:Exception)
        {
            Log.e("mensaje de error", ex.toString())
        }

    }

    private fun initUI() {
        binding.rgVolume.addOnChangeListener{ _, value, _ ->
            Log.i("mensaje", "el valor es $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        binding.switchBlue.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->

            if(value){
                enableDarkMode()
            }
            else{
                disableDarkMode()
            }

            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }

        }

        binding.switchMute.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VOLUME, value)
            }
        }
    }

    private suspend fun saveVolume(value: Int){
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean){
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {

        val configuraciones :Flow<SettingsModel>;
        try
        {
            configuraciones = dataStore.data.map { preferences->
                SettingsModel(
                    volume = preferences[intPreferencesKey(VOLUME_LVL)]?:50,
                    bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)]?:true,
                    darkmode = preferences[booleanPreferencesKey(KEY_DARK_MODE)]?:false,
                    mute = preferences[booleanPreferencesKey(KEY_MUTE)]?:true
                )

            }
            Log.i(ETIQUETA_LOG, configuraciones.toString())
            return configuraciones;

        }
        catch (ex: Exception) {
            Log.e("mensaje de error", ex.toString())
            return dataStore.data.map { preferences ->
                SettingsModel(
                    volume = 50,
                    bluetooth = true,
                    darkmode = true,
                    mute = true
                );
            }
        }

    }


    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}