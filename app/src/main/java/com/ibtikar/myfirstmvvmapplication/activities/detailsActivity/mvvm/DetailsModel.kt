package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm

import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.DataManager
import javax.inject.Inject

class DetailsModel @Inject constructor(var dataManager: DataManager) : DetailsContract.Model {

}