package com.petocare.infra.base_class

import com.petocare.infra.rootUtils.NetworkUtils
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit

abstract class BaseRepository<RemoteDataSource, LocalDataSource : BaseLocalService> {

  protected val remoteDataSource: RemoteDataSource
    get() = getApiClient().create(getRemoteDataSourceClass())

  protected val localDataSource: LocalDataSource
    get() = getLocalDataSourceInstance()

  protected abstract fun getRemoteDataSourceClass(): Class<RemoteDataSource>
  protected abstract fun getLocalDataSourceInstance(): LocalDataSource

  protected abstract fun getApiClient(): Retrofit

  fun <T> makeRemoteRequest(observable: Observable<Response<T>>, taskCode: Int): Observable<BaseResponse> {
    if (!NetworkUtils.isNetworkConnected()) {
      val response = BaseResponse(error = NoNetworkException(), statusCode = 503, message = "network_error")
      return Observable.just(response)
    }

    return observable.map {
      if (it.isSuccessful) {
        val response = getResponseValue(it)
        response.statusCode = it.code()
        response.taskcode = taskCode
        onSuccess(response, taskCode)
        return@map response
      } else {
        val response = getResponseValue(it, "Error")
        response.statusCode = it.code()
        response.error = BaseException(it.errorBody()?.string() ?: "")
        response.error?.localizedMessage?.let { it1 -> response.message = it1 }
        response.taskcode = taskCode
        onFailure(response, taskCode)
        return@map response
      }
    }.onErrorReturn {
      it.printStackTrace()
      val response = BaseResponse()
      response.error = it
      response.message = it.localizedMessage
      response.taskcode = taskCode
      onFailure(response, taskCode)
      response
    }
  }

  private fun <T> getResponseValue(it: Response<T>, message: String = "Success"): BaseResponse {
    return when (it.body()) {
      is Array<*> -> BaseResponse(message = message, arrayResponse = it.body() as Array<*>)
      is String -> BaseResponse(message = message, stringResponse = it.body() as String)
      is BaseResponse -> (it.body() as T) as BaseResponse
      is ResponseBody -> BaseResponse(responseBody = (it.body() as? ResponseBody), message = message)
      else -> BaseResponse(anyResponse = it.body(), message = message)
    }
  }

  fun makeLocalResponse(observable: Observable<BaseResponse>, taskcode: Int): Observable<BaseResponse> {
    return observable.map {
      if (it.error != null) {
        it.statusCode = 404
        it.taskcode = taskcode
        onFailure(it, taskcode)
        return@map it
      } else {
        it.statusCode = 200
        it.taskcode = taskcode
        onSuccess(it, taskcode)
        return@map it
      }
    }
  }

  open fun onFailure(response: BaseResponse, taskCode: Int) {
    if (response.error == null) response.error = Exception()
  }

  open fun onSuccess(response: BaseResponse, taskCode: Int) {

  }
}


