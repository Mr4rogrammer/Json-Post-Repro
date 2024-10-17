package info.mrprogrammer.corutinetest.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.corutinetest.domain.GetListOfPost
import info.mrprogrammer.corutinetest.domain.model.PostDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val getListOfPost: GetListOfPost) : ViewModel() {

    private val _listOfPost = MutableStateFlow<List<PostDataModel>>(emptyList())
    val listState = _listOfPost.asStateFlow()

    init {
        getListOfPost()
    }


    private fun getListOfPost() {
        viewModelScope.launch {
            val result = getListOfPost.invoke()
            result.onSuccess { results ->
                _listOfPost.value = results
            }

            result.onFailure {
                //Need to handle the failue cases
            }
        }
    }
}