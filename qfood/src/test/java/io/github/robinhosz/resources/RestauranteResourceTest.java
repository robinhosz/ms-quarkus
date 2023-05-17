package io.github.robinhosz.resources;

import io.github.robinhosz.CadastroTestLifecycleManager;
import io.github.robinhosz.resources.util.TokenUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;


import static io.restassured.RestAssured.given;



@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {

    public RestauranteResourceTest() throws NoSuchAlgorithmException {
    }

    @Test
    void testBuscarRestaurante() {

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // gerar um token JWT válido com as mesmas informações de payload e assinatura que o Keycloak usa
        String token = Jwts.builder()
                .setSubject("userid")
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(secretKey)
                .compact();

        String resultado = given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token))
                .when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
    }
}
