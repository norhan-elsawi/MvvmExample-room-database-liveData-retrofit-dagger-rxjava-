package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(var model: DetailsContract.Model) : ViewModel(), DetailsContract.ViewModel {


}