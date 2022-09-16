package com.petocare.infra.base_class

class NoNetworkException : BaseException {

  internal constructor() : super("Internet connection not available.")

  internal constructor(message: String) : super(message)
}
