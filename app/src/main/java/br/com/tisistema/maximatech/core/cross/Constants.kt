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
        const val NAVIGATION = 0L
        const val STRING_VAZIO = ""
    }

    object CORES_DOS_ITENS_DO_MENU_PRINCIPAL {
        const val ITEM_ATIVO = "#FFFFFF"
        const val ITEM_INATIVO = "#95B6CF"
    }

    object TIMEOUT {
        const val ENDPOINTS_GERAIS = 15L
    }

    object VALIDACOES {
        const val PARAMETRO_NULL = "Não informado"
    }

    object DATE_FORMAT {
        const val FORMATO_DE_DATA_RECEBIDO = "yyyy-MM-dd"
        const val FORMATO_DE_DATA_EXIBIDO = "dd/MM/yyyy"

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