package com.petocare.infra.retrofit.api_model.MobileTechResponse

data class MobileTechResponse(
    val message: String,
    val mylist: List<Mylist>,
    val status: Boolean
)