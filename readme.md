# API-IBGE

## O que é essa API ?

#### Essa é uma simples API que retorna dados sobre estados, cidades e regiões do Brasil.

## Como faço para rodar o projeto ?
      docker-compose up -d

## Quais são os endpoints disponíveis ?
   * http://localhost:8080/v1/ibge/json
        
        **retorno:**
            
            [
              {
                "idEstado": 11,
                "siglaEstado": "RO",
                "regiaoNome": "Norte",
                "nomeCidade": "Alta Floresta D'Oeste",
                "nomeMesorregiao": "Leste Rondoniense",
                "nomeFormatado": "Alta Floresta D'Oeste/RO"
              }
              ...
            ]
    
    
   * http://localhost:8080/v1/ibge/csv
        
        **retorno:**
            
            Arquivo csv com os dados acima;
            
    
    
    
   * http://localhost:8080/v1/ibge/city/{nomeCidade}
        
        **retorno:**
            
            {"id":1100023}    
        
## Tem driagrama de classe ?

![alt text](https://github.com/PauloGustavo72/private-ibge/blob/master/uml-foto.jpeg)
    
