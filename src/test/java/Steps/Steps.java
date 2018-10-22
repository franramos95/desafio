package Steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;


public class Steps {

    private String token;
    private Response response;
    private String url;
    private String message;
    private String idPost;

    @Dado("^que o usuario tenha o token de acesso$")
    public void queOUsuarioTenhaOTokenDeAcesso() throws Throwable {

        this.token = "EAADnjyDqdaIBAC3AvvhmFH6nFQWHdugmap72rn2672mwAqmWHqD0h9ORqJJGgmYz6iQScShAzZAYjeuZA8P2OJ8rYAXx1Y2c97D6dVwSDyMub96rdoOg6RlpLVPJXAT3EbquGucw1SnPheqiWCMzlZBDaUZCrW452YTyrll75AZDZD";
    }

    @Quando("^o usuario faz uma requisicao em \"(.*)\"$")
    public void oUsuarioFazUmaRequisicaoEm(String url) throws Throwable {

        this.url = url + "/?access_token=" + token;
    }

    @Quando("^o usuario manda uma requisicao get com seu token$")
    public void oUsuarioMandaUmaRequisicaoGetComSeuToken() throws Throwable {
        //  response = RestAssured.get(this.url);
        response = given()
                .contentType(ContentType.JSON)
                .header("Token de Acesso", token)
                .when()
                .get(url);
    }


    @Entao("^a Api deve retornar status code (\\d+)$")
    public void aApiDeveRetornarStatusCode(int code) throws Throwable {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Dado("^que o usuario tenha o token de acesso incorreto$")
    public void queOUsuarioTenhaOTokenDeAcessoIncorreto() throws Throwable {
        this.token = "iAADnjyDqdaIBAC3AvvhmFH6nFQWHdugmap72rn2672mwAqmWHqD0h9ORqJJGgmYz6iQScShAzZAYjeuZA8P2OJ8rYAXx1Y2c97D6dVwSDyMub96rdoOg6RlpLVPJXAT3EbquGucw1SnPheqiWCMzlZBDaUZCrW452YTyrll75AZDZD";
    }

    @Quando("^o usuario realiza um post pela url \"([^\"]*)\"$")
    public void oUsuarioRealizaUmPostPelaUrl(String url) throws Throwable {

        this.url = url + "/?access_token=" + token;

    }

    @Quando("^com a mensagem \"(.*)\"$")
    public void comAMensagemMessage(String mensagem) throws Throwable {

        message = mensagem;

    }

    @Entao("^Api deve retornar status code (\\d+)$")
    public void apiDeveRetornarStatusCode(int code) throws Throwable {
        response = given()
                .contentType(ContentType.JSON)
                .header("Token de Acesso", token)
                .body(message)
                .when()
                .post(url);

        Assert.assertEquals(code, response.getStatusCode());
    }

    @Entao("^salvar o id do post$")
    public void salvarOIdDoPost() throws Throwable {
        idPost = response.getBody().path("id");
    }

    @Dado("^que o usuario nao tenha a autenticao do token$")
    public void queOUsuarioNOTenhaAAutenticaODoToken() throws Throwable {
        this.token = null;
    }

    @Quando("^o usuario altera o post pela url \"(.*)\"$")
    public void oUsuarioAlteraOPostPelaUrl(String url1) throws Throwable {
        response = given()
                .contentType(ContentType.JSON)
                .header("Token de Acesso", token)
                .body(message)
                .when()
                .post(url1);
    }

    @Entao("^Api deve retornar codigo de erro (\\d+)$")
    public void apiDeveRetornarCodigoDeErro(int code) throws Throwable {
        Assert.assertEquals(code, response.getBody().path("code"));

    }

}
