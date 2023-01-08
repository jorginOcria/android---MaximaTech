package br.com.tisistema.maximatech.core.cross.listener

interface RestCallListener<T> {
    fun onSuccess(data: T?, httpStatusCode: Int)
    fun onFailure(throwable: Throwable)
}