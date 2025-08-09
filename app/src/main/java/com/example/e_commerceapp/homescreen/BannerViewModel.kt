package com.example.e_commerceapp.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.BannerRepository
import com.example.domain.slidermodel.SliderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BannerViewModel @Inject constructor(
    private val bannerRepository: BannerRepository
) : ViewModel() {
    fun loadBanner(): LiveData<MutableList<SliderModel>> {
        return bannerRepository.loadBanner()
    }
}