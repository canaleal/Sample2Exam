package com.canaleal.sample2.ui

import androidx.lifecycle.*
import com.canaleal.sample2.domain.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor() : ViewModel() {
    enum class Status { NEW_DATA, SAVED_DATA }

    var envelopeId: Long? = null

    private val _status = MutableLiveData(Status.NEW_DATA)
    val status: LiveData<Status> = _status

    val selected = MutableLiveData<Pet>()
    fun save(pet: Pet) {
        selected.value = pet
    }


    private val _pet = MutableLiveData<Pet>()
    val petAni: LiveData<Pet>
        get() = _pet

    fun update(pet: Pet){
        viewModelScope.launch(Dispatchers.IO){
            _pet.postValue(pet)
            _status.postValue(Status.SAVED_DATA)
        }
    }

}