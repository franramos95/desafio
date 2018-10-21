package Steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.junit.Assert;



public class Steps {

    private String authToken;
    Response reqResp;
    String useURL;
    String theMessage;
    String idPost;

    @Dado("^que o usuario tenha o token de acesso$")
    public void queOUsuarioTenhaOTokenDeAcesso() throws Throwable {

        this.authToken = "EAADnjyDqdaIBAC3AvvhmFH6nFQWHdugmap72rn2672mwAqmWHqD0h9ORqJJGgmYz6iQScShAzZAYjeuZA8P2OJ8rYAXx1Y2c97D6dVwSDyMub96rdoOg6RlpLVPJXAT3EbquGucw1SnPheqiWCMzlZBDaUZCrW452YTyrll75AZDZD";
    }

    @Quando("^o usuario faz uma requisicao em \"(.*)\"$")
    public void oUsuarioFazUmaRequisicaoEm(String url) throws Throwable {

        this.useURL = url + "/?access_token=" + authToken;
    }

    @Quando("^o usuario manda uma requisicao get com seu token$")
    public void oUsuarioMandaUmaRequisicaoGetComSeuToken() throws Throwable {
        reqResp = RestAssured.get(this.useURL);
    }

    @Entao("^a Api deve retornar status code (\\d+)$")
    public void aApiDeveRetornarStatusCode(int code) throws Throwable {
            Assert.assertEquals(code, reqResp.getStatusCode());
    }

    @Dado("^que o usuario tenha o token de acesso incorreto$")
    public void queOUsuarioTenhaOTokenDeAcessoIncorreto() throws Throwable {
        this.authToken = "iAADnjyDqdaIBAC3AvvhmFH6nFQWHdugmap72rn2672mwAqmWHqD0h9ORqJJGgmYz6iQScShAzZAYjeuZA8P2OJ8rYAXx1Y2c97D6dVwSDyMub96rdoOg6RlpLVPJXAT3EbquGucw1SnPheqiWCMzlZBDaUZCrW452YTyrll75AZDZD";
    }

    @Quando("^o usuario realiza um post pela url \"([^\"]*)\"$")
    public void oUsuarioRealizaUmPostPelaUrl(String url) throws Throwable {

        this.useURL = url + "/?access_token=" + authToken;

   }

    @Quando("^com a mensagem \"(.*)\"$")
    public void comAMensagemMessageOlPessoal(String message) throws Throwable {

        theMessage = message;

    }

    @Entao("^Api deve retornar status code (\\d+)$")
    public void apiDeveRetornarStatusCode(int code) throws Throwable {
        reqResp = given()
                .contentType(ContentType.JSON)
                .body(theMessage)
                .when()
                .post(useURL);

        Assert.assertEquals(code, reqResp.getStatusCode());
    }

    @Entao("^salvar o id do post$")
    public void salvarOIdDoPost() throws Throwable {
        this.idPost = reqResp.
                then().
                contentType(ContentType.JSON).extract().path("id");

    }

    @Dado("^que o usuario n?o tenha a autentica??o do token$")
    public void queOUsuarioNOTenhaAAutenticaODoToken() throws Throwable {
        this.authToken = null;
    }
}
