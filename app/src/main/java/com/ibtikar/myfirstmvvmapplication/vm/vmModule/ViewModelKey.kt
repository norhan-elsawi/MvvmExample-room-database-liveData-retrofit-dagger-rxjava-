package com.ibtikar.myfirstmvvmapplication.vm.vmModule

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target
import kotlin.reflect.KClass

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)