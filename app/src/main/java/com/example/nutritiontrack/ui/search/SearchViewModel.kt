package com.example.nutritiontrack.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontrack.database.AppDatabase
import com.example.nutritiontrack.database.getInstance
import com.example.nutritiontrack.domain.DataRepository
import com.example.nutritiontrack.domain.Meal
import kotlinx.coroutines.*

class SearchViewModel(app: Application) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getInstance(app)
    private val repository = DataRepository(database)


    private val _searchedMeals = MutableLiveData<List<Meal>>()
    val searchedMeals: LiveData<List<Meal>>
        get() = _searchedMeals


    //TODO not used yet
    private val _searchString = MutableLiveData<String>()
    val searchString: LiveData<String>
        get() = _searchString


    fun search(searchableString: String) {
        coroutineScope.launch {
            if (searchableString.isEmpty()) {
                _searchedMeals.value = emptyList()
            } else {
                _searchedMeals.value = repository.getSearchableItems()
                // TODO get the data from the repository and assign it to the searchedMeals
                // TODO set the recycler view to view the contents of the list of _searchedMeals
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}


