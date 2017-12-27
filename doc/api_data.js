define({ "api": [
  {
    "type": "delete",
    "url": "/banco/delete/:id",
    "title": "Delete",
    "group": "Banco",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 204 No Content",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um banco que possua o identificador passado como parâmetro.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>O banco selecionado possui agências e, portanto, não pode ser excluído.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "HTTP/1.1 404 Not Found\n{\n    \"timestamp\": \"09/06/2018 09:34:35\",\n    \"status\": 404,\n    \"reason\": \"Not Found\",\n    \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n    \"message\": \"Banco não encontrado\",\n    \"path\": \"/banco/delete/0\"\n}",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 Unprocessable Entity\n{\n    \"timestamp\": \"09/06/2018 09:37:36\",\n    \"status\": 422,\n    \"reason\": \"Unprocessable Entity\",\n    \"exception\": \"aplicacaofinanceira.exception.NotEmptyCollectionException\",\n    \"message\": \"O banco selecionado possui pelo menos uma agência e, portanto, não pode ser excluído\",\n    \"path\": \"/banco/delete/1\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/BancoController.java",
    "groupTitle": "Banco",
    "name": "DeleteBancoDeleteId"
  },
  {
    "type": "get",
    "url": "/banco/list",
    "title": "List",
    "group": "Banco",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "numero",
            "description": "<p>Numero do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "cnpj",
            "description": "<p>CNPJ do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do banco.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n[\n  {\n    \"id\": 1,\n    \"numero\": 1,\n    \"cnpj\": \"00000000000191\",\n    \"nome\": \"Banco do Brasil\"\n  },\n  {\n    \"id\": 2,\n    \"numero\": 2,\n    \"cnpj\": \"00360305000104\",\n    \"nome\": \"Caixa Econômica Federal\"\n  },\n  {\n    \"id\": 3,\n    \"numero\": 3,\n    \"cnpj\": \"60872504000123\",\n    \"nome\": \"Itaú\"\n  }\n]",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/BancoController.java",
    "groupTitle": "Banco",
    "name": "GetBancoList"
  },
  {
    "type": "get",
    "url": "/banco/show/:id",
    "title": "Show",
    "group": "Banco",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "numero",
            "description": "<p>Numero do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "cnpj",
            "description": "<p>CNPJ do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do banco.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n{\n  \"id\": 1,\n  \"numero\": 1,\n  \"cnpj\": \"00000000000191\",\n  \"nome\": \"Banco do Brasil\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um banco que possua o identificador passado como parâmetro.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"timestamp\": \"09/06/2018 10:02:47\",\n  \"status\": 404,\n  \"reason\": \"Not Found\",\n  \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n  \"message\": \"Banco não encontrado\",\n  \"path\": \"/banco/show/0\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/BancoController.java",
    "groupTitle": "Banco",
    "name": "GetBancoShowId"
  },
  {
    "type": "post",
    "url": "/banco/save",
    "title": "Save",
    "group": "Banco",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>Tipo do conteúdo da requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"Content-Type\":  application/json\n\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Exemplo de requisição",
        "content": "{\n  \"numero\": 1, \n  \"cnpj\": \"00000000000191\",   \n  \"nome\": \"Banco do Brasil\"   \n}",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "numero",
            "description": "<p>Numero do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "cnpj",
            "description": "<p>CNPJ do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do banco.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 201 Created\n{\n  \"id\": 1,\n  \"numero\": 1,\n  \"cnpj\": \"00000000000191\",\n  \"nome\": \"Banco do Brasil\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>Erros de validação de dados ou violação de regras de negócio.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 UnprocessableEntity - \"Campos obrigatórios não foram fornecidos.\"\n\n{\n  \"timestamp\": \"09/06/2018 09:48:18\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O CNPJ fornecido não pode ser nulo\",\n    \"O CNPJ fornecido é inválido\",\n    \"O nome fornecido não pode ser nulo\",\n    \"O número fornecido deve ser maior do que zero\"\n  ],\n  \"path\": \"/banco/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O número fornecido é menor do que zero.\"\n\n{\n  \"timestamp\": \"09/06/2018 09:53:33\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O número fornecido deve ser maior do que zero\"\n  ],\n  \"path\": \"/banco/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O número fornecido já pertence a outro banco.\"\n\n{\n  \"timestamp\": \"09/06/2018 09:55:00\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.NotUniqueException\",\n  \"message\": \"O número fornecido já pertence a outro banco\",\n  \"path\": \"/banco/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O CNPJ fornecido é inválido.\"\n\n{\n  \"timestamp\": \"09/06/2018 09:55:56\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O CNPJ fornecido é inválido\"\n  ],\n  \"path\": \"/banco/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome do banco deve ter entre 2 e 255 caracteres.\"\n\n{\n  \"timestamp\": \"09/06/2018 09:57:25\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome do banco deve ter entre 2 e 255 caracteres\"\n  ],\n  \"path\": \"/banco/save\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/BancoController.java",
    "groupTitle": "Banco",
    "name": "PostBancoSave"
  },
  {
    "type": "put",
    "url": "/banco/update/:id",
    "title": "Update",
    "group": "Banco",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>Tipo do conteúdo da requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"Content-Type\":  application/json\n\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição",
          "content": "{\n  \"numero\": 1, \n  \"cnpj\": \"00000000000191\",   \n  \"nome\": \"Banco do Brasil\"   \n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "numero",
            "description": "<p>Numero do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "cnpj",
            "description": "<p>CNPJ do banco.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do banco.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n{\n  \"id\": 1,\n  \"numero\": 1,\n  \"cnpj\": \"00000000000191\",\n  \"nome\": \"Banco do Brasil\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um banco que possua o identificador passado como parâmetro.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>Erros de validação de dados ou violação de regras de negócio.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"timestamp\": \"09/06/2018 10:07:04\",\n  \"status\": 404,\n  \"reason\": \"Not Found\",\n  \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n  \"message\": \"Banco não encontrado\",\n  \"path\": \"/banco/update/0\"\n}",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 UnprocessableEntity - \"Campos obrigatórios não foram fornecidos.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:07:47\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O CNPJ fornecido não pode ser nulo\",\n    \"O CNPJ fornecido é inválido\",\n    \"O nome fornecido não pode ser nulo\",\n    \"O número fornecido deve ser maior do que zero\"\n  ],\n  \"path\": \"/banco/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O número fornecido é menor do que zero.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:09:06\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O número fornecido deve ser maior do que zero\"\n  ],\n  \"path\": \"/banco/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O número fornecido já pertence a outro banco.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:10:02\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.NotUniqueException\",\n  \"message\": \"O número fornecido já pertence a outro banco\",\n  \"path\": \"/banco/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O CNPJ fornecido é inválido.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:11:03\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O CNPJ fornecido é inválido\"\n  ],\n  \"path\": \"/banco/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome do banco deve ter entre 2 e 255 caracteres.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:12:11\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome do banco deve ter entre 2 e 255 caracteres\"\n  ],\n  \"path\": \"/banco/update/1\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/BancoController.java",
    "groupTitle": "Banco",
    "name": "PutBancoUpdateId"
  },
  {
    "type": "delete",
    "url": "/estado/delete/:id",
    "title": "Delete",
    "group": "Estado",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 204 No Content",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um estado que possua o identificador passado como parâmetro.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>O estado selecionado possui cidades e, portanto, não pode ser excluído.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "{\n  \"timestamp\": \"09/06/2018 10:17:58\",\n  \"status\": 404,\n  \"reason\": \"Not Found\",\n  \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n  \"message\": \"Estado não encontrado\",\n  \"path\": \"/estado/delete/0\"\n}",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 Unprocessable Entity\n{\n    \"timestamp\": \"09/06/2018 09:37:36\",\n    \"status\": 422,\n    \"reason\": \"Unprocessable Entity\",\n    \"exception\": \"aplicacaofinanceira.exception.NotEmptyCollectionException\",\n    \"message\": \"O estado selecionado possui pelo menos uma cidade e, portanto, não pode ser excluído\",\n    \"path\": \"/banco/delete/1\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/EstadoController.java",
    "groupTitle": "Estado",
    "name": "DeleteEstadoDeleteId"
  },
  {
    "type": "get",
    "url": "/estado/list",
    "title": "List",
    "group": "Estado",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do estado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n[\n  {\n    \"id\": 3,\n    \"nome\": \"Espirito Santo\"\n  },\n  {\n    \"id\": 2,\n    \"nome\": \"Rio de Janeiro\"\n  },\n  {\n    \"id\": 1,\n    \"nome\": \"Sao Paulo\"\n  }\n]",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/EstadoController.java",
    "groupTitle": "Estado",
    "name": "GetEstadoList"
  },
  {
    "type": "get",
    "url": "/estado/show/:id",
    "title": "Show",
    "group": "Estado",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do estado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n{\n  \"id\": 1,\n  \"nome\": \"Sao Paulo\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um estado que possua o identificador passado como parâmetro.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"timestamp\": \"09/06/2018 10:29:21\",\n  \"status\": 404,\n  \"reason\": \"Not Found\",\n  \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n  \"message\": \"Estado não encontrado\",\n  \"path\": \"/estado/show/0\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/EstadoController.java",
    "groupTitle": "Estado",
    "name": "GetEstadoShowId"
  },
  {
    "type": "post",
    "url": "/estado/save",
    "title": "Save",
    "group": "Estado",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>Tipo do conteúdo da requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"Content-Type\":  application/json\n\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "examples": [
      {
        "title": "Exemplo de requisição",
        "content": "{\n  \"nome\": \"Sao Paulo\"\n}",
        "type": "json"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do estado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 201 Created\n{\n  \"id\": 1,\n  \"nome\": \"Sao Paulo\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>Erros de validação de dados ou violação de regras de negócio.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 UnprocessableEntity - \"Campos obrigatórios não foram fornecidos.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:23:34\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome fornecido não pode ser nulo\"\n  ],\n  \"path\": \"/estado/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome fornecido já pertence a outro estado.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:24:35\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.NotUniqueException\",\n  \"message\": \"O nome fornecido já pertence a outro estado\",\n  \"path\": \"/estado/save\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome do estado deve ter entre 2 e 255 caracteres.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:25:27\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome do estado deve ter entre 2 e 255 caracteres\"\n  ],\n  \"path\": \"/estado/save\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/EstadoController.java",
    "groupTitle": "Estado",
    "name": "PostEstadoSave"
  },
  {
    "type": "put",
    "url": "/estado/update/:id",
    "title": "Update",
    "group": "Estado",
    "version": "1.0.0",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>Tipo do conteúdo da requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "nomeDeUsuario",
            "description": "<p>Nome do usuário que está realizando a requisição.</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>Token de acesso.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de cabeçalho:  ",
          "content": "\"Content-Type\":  application/json\n\"nomeDeUsuario\": admin\n\"token\":         PXO9PmSpQwukqfjqg5ukbG3O1Hgl1yH0",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Exemplo de requisição",
          "content": "{\n  \"nome\": \"Sao Paulo\"   \n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Identificador do estado.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nome",
            "description": "<p>Nome do estado.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Resposta de sucesso",
          "content": "HTTP/1.1 200 OK\n{\n  \"id\": 1,\n  \"nome\": \"Sao Paulo\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>Usuário não autorizado a acessar o recurso solicitado.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Não existe um estado que possua o identificador passado como parâmetro.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UnprocessableEntity",
            "description": "<p>Erros de validação de dados ou violação de regras de negócio.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Unauthorized",
          "content": "HTTP/1.1 401 Unauthorized",
          "type": "json"
        },
        {
          "title": "NotFound",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"timestamp\": \"09/06/2018 10:32:21\",\n  \"status\": 404,\n  \"reason\": \"Not Found\",\n  \"exception\": \"aplicacaofinanceira.exception.NotFoundException\",\n  \"message\": \"Estado não encontrado\",\n  \"path\": \"/estado/update/0\"\n}",
          "type": "json"
        },
        {
          "title": "UnprocessableEntity",
          "content": "HTTP/1.1 422 UnprocessableEntity - \"Campos obrigatórios não foram fornecidos.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:33:16\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome fornecido não pode ser nulo\"\n  ],\n  \"path\": \"/estado/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome fornecido já pertence a outro estado.\"\n\n{\n  \"timestamp\": \"09/06/2018 10:10:02\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.NotUniqueException\",\n  \"message\": \"O nome fornecido já pertence a outro estado\",\n  \"path\": \"/estado/update/1\"\n}\n\nHTTP/1.1 422 UnprocessableEntity - \"O nome do estado deve ter entre 2 e 255 caracteres.\"     \n\n{\n  \"timestamp\": \"09/06/2018 10:35:41\",\n  \"status\": 422,\n  \"reason\": \"Unprocessable Entity\",\n  \"exception\": \"aplicacaofinanceira.exception.ValidationException\",\n  \"messages\": [\n    \"O nome do estado deve ter entre 2 e 255 caracteres\"\n  ],\n  \"path\": \"/estado/update/1\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/aplicacaofinanceira/controller/EstadoController.java",
    "groupTitle": "Estado",
    "name": "PutEstadoUpdateId"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./doc/main.js",
    "group": "_home_ivan_NetBeansProjects_aplicacaofinanceira_doc_main_js",
    "groupTitle": "_home_ivan_NetBeansProjects_aplicacaofinanceira_doc_main_js",
    "name": ""
  }
] });
