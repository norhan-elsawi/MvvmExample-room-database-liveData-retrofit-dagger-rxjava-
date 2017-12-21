package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.ibtikar.myfirstmvvmapplication.R
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di.DaggerMainComponent
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di.MainModule
import com.ibtikar.myfirstmvvmapplication.di.Injector
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : FragmentActivity() {


    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resolveDaggerDependency(Injector.INSTANCE.appComponent!!)

        //mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        callData()
    }

    private fun callData() {

        mainViewModel?.callDataFromApi()?.observe(this, Observer { boolean ->
            text.append(" \n boolean == $boolean \n")
            if (boolean!!) {
                mainViewModel?.loadItemsFromDb()?.observe(this, Observer { list ->
                    text.append("\n \n in main activity ${list?.size}\n \n ")
                    for (i in list!!.listIterator()) {
                        text.append("${i.itemName} \n")
                    }

                })
            }
        })
    }


    private fun resolveDaggerDependency(appComponent: ApplicationComponent) {
        DaggerMainComponent
                .builder()
                .applicationComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }

}
