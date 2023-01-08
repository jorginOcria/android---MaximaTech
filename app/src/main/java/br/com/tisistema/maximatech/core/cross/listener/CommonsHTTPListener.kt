package br.com.tisistema.maximatech.core.cross.listener

interface CommonsHTTPListener {
    fun onReceiveCommonHTTPStatus(statusCode: Int)
    fun onStartHTTPRequest()
    fun onReceiveHTTPResponse()
}