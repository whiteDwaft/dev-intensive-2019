package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository
import ru.skillbranch.devintensive.utils.Utils

class ProfileViewModel : ViewModel() {

    private val repository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repCheck = MutableLiveData<Boolean>()
    private val repositoryError = MutableLiveData<Boolean>()


    fun getProfileData(): LiveData<Profile> = profileData
    fun getTheme(): LiveData<Int> = appTheme
    fun getRep(): LiveData<Boolean> = repCheck
    fun getRepError():LiveData<Boolean> = repositoryError

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }



    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

    fun onRepoEditCompleted(errorEnabled: Boolean) {
        repositoryError.value = errorEnabled

    }
    fun onRepositoryChanged(repository: String) {
        repCheck.value = !isValidateRepository(repository)
    }
    private fun isValidateRepository(repo: String): Boolean {
        return Utils.repValidartion(repo)
    }
}
