package helpers;
import config.BrowserstackLoginConfig;
import org.aeonbits.owner.ConfigFactory;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class Browserstack {

    //curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" -X GET "https://api.browserstack.com/app-automate/sessions/<session-id>.json"
    public static String getVideoUrl(String sessionID){

        BrowserstackLoginConfig loginConfig = ConfigFactory.create(BrowserstackLoginConfig.class);
        assertThat(loginConfig.userName()).isEqualTo("hwqa_uomRki");
        assertThat(loginConfig.password()).isEqualTo("u1VhNMZ58D8FuosP4XxR");
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionID);
        return given()
                .log().all()
                .auth().basic(loginConfig.userName(), loginConfig.password())
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("automation_session.video.url");

    }
}
