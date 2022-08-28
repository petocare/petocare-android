package com.petocare.infra.retrofit.remote

import com.petocare.infra.retrofit.api_model.MobileTechResponse.MobileTechResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterfaces {

  @Headers("Content-Type: application/json")
  @GET("testing")
  fun GetAllData(): Observable<MobileTechResponse>
}