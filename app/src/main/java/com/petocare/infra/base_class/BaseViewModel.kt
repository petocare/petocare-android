package com.petocare.infra.base_class

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

open class BaseViewModel : ViewModel()

fun Observable<BaseResponse>.toLiveData(strategy: BackpressureStrategy = BackpressureStrategy.BUFFER): LiveData<BaseResponse> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable(strategy))
}
