package br.com.tisistema.maximatech.core.cross

class Constants private constructor() {

    object DB {
        const val DB_NAME = "MaximaTech"
    }

    object API {
        const val BASE_URL = "https://private-anon-9cd2c70709-maximatech.apiary-mock.com"
        const val REST_URL = "$BASE_URL/android/"
    }

    object VERSAO_DO_APLICATIVO {
        const val VERSAO = "1.0.0"
    }

    object SISTEMA {
        const val TEMPO_DE_SPLASH = 3000L
        const val STRING_VAZIO = ""
        const val VALOR_MONETARIO = "R$ 0,00"
    }

    object FRAGMENT_ATUAL {
        const val HISTORICO_DE_PEDIDO = "historico_de_pedido"
        const val DADOS_DO_CLIENTE = "dados_do_cliente"
        const val ALVARAS = "alvaras"

    }

    object CORES_DOS_ITENS_DO_MENU_PRINCIPAL {
        const val ITEM_ATIVO = "#FFFFFF"
        const val ITEM_INATIVO = "#95B6CF"
    }

    object TIMEOUT {
        const val ENDPOINTS_GERAIS = 15L
    }

    object VALIDAR {
        const val PARAMETRO_NULL = "Não informado"
    }

    object FORMATAR {
        const val FORMATO_DE_DATA_RECEBIDO = "yyyy-MM-dd"
        const val FORMATO_DE_DATA_EXIBIDO = "dd/MM/yyyy"
        const val FORMATO_DATA_E_HORA = " dd/MM/yyyy HH:mm"
        const val FORMATO_HORA = "HH:mm"
        const val FORMATO_CELULAR = "(%02d) %04d-%05d"
    }

    object TOAST {
        const val SUCESSO = 1
        const val AVISO = 2
        const val ERRO = 3
        const val INFORMACAO = 4
    }

    object HTTP {
        object STATUS_CODE {
            const val AUTORIZADO = 200
            const val ERRO_INTERNO_NO_SERVIDOR = 500
        }
    }
}
