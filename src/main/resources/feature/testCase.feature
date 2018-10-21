# language: pt
@esse
Funcionalidade: Autenticação e login no facebook

  Cenario: Autenticacao realizada com sucesso
    Dado que o usuario tenha o token de acesso
    Quando o usuario faz uma requisicao em "https://graph.facebook.com/me"
    E o usuario manda uma requisicao get com seu token
    Entao a Api deve retornar status code 200

  Cenario: Autenticacao realizada com token invalido
    Dado que o usuario tenha o token de acesso incorreto
    Quando o usuario faz uma requisicao em "https://graph.facebook.com/me"
    E o usuario manda uma requisicao get com seu token
    Entao a Api deve retornar status code 190

  Cenario: Realizando postagem do usuario
    Dado que o usuario tenha o token de acesso
    Quando o usuario realiza um post pela url "https://graph.facebook.com/me/feed"
    E com a mensagem "{\"message\":\"Olá Pessoal\"}"
    Entao  Api deve retornar status code 200
    E salvar o id do post

  Cenario: Não realizar postagem do usuario por falta de token
    Dado que o usuario não tenha a autenticação do token
    Quando o usuario realiza um post pela url "https://graph.facebook.com/me/feed"
    E com a mensagem "{\"message\":\"Olá Pessoal\"}"
    Entao  Api deve retornar status code 2500

  Cenario: Não realizar postagem do usuario por token invalido
    Dado que o usuario tenha o token de acesso incorreto
    Quando o usuario realiza um post pela url "https://graph.facebook.com/me/feed"
    E com a mensagem "{\"message\":\"Olá Pessoal\"}"
    Entao  Api deve retornar status code 190

 Cenario: Alterando o Post
    Dado que o usuario tenha o token de acesso
    Quando o usuario realiza um post pela url "https://graph.facebook.com/"
    E com a mensagem "{\"message\":\"Olá Pessoal do Facebook\"}"
    Entao  Api deve retornar status code 200


