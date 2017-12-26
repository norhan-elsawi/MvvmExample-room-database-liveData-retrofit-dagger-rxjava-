package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ibtikar.myfirstmvvmapplication.R
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.di.DaggerDetailsComponent
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.di.DetailsModule
import com.ibtikar.myfirstmvvmapplication.di.Injector
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var detailsViewModel: DetailsContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        resolveDaggerDependency(Injector.INSTANCE.appComponent!!)
    }

    private fun resolveDaggerDependency(appComponent: ApplicationComponent) {
        DaggerDetailsComponent
                .builder()
                .applicationComponent(appComponent)
                .detailsModule(DetailsModule(this))
                .build()
                .inject(this)
    }

}
