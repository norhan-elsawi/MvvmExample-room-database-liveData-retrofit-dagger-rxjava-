package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.BR
import com.ibtikar.myfirstmvvmapplication.R
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di.DaggerMainComponent
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di.MainModule
import com.ibtikar.myfirstmvvmapplication.di.Injector
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        resolveDaggerDependency(Injector.INSTANCE.appComponent!!)
         val viewDataBinding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewDataBinding.setVariable(BR.viewModel,mainViewModel)
        callData()
    }

    private fun callData() {

        mainViewModel?.callDataFromApi().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnComplete {
                    Log.e("on complete", "complete") }
                .subscribe()

        mainViewModel?.loadItemsFromDb()?.observe(this, Observer { list ->
            text.append("\n \n in main activity ${list?.size}\n \n ")
            for (i in list!!.listIterator()) {
                text.append("${i.itemName} \n")
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
