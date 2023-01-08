package br.com.tisistema.maximatech.core.cross.listener

interface BodyResponse<T>{
    var mensagem: String
    var success: String
    var data: T
}