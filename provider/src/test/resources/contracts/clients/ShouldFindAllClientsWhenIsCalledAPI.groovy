package clients

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Representa escenario exitoso de consulta de todos los clientes
            
    ```
            
            given: 
                    El usuario quiere conocer los clientes existentes
            when: 
                    Consulta por medio de buscar todos los clientes
            then
                    deberia ver los registros existentes de clientes
    ```
    
    """)

    request {
        method 'GET'
        url '/api/server/clients'
        headers { contentType applicationJson() }
    }
    response {
        status 200
        headers { contentType(applicationJson()) }
    }
}

