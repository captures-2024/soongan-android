package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeGalleryListViewModel
@Inject
constructor(

) : ViewModel() {


    companion object {
        private const val TAG = "HomeGalleryListVM"
    }
}