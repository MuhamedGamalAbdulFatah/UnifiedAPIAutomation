
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class TestCase {
    @Test
    public void testSTATUSCODE(){
                given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                        assertThat().statusCode(200);

    }
    @Test
    public void testEqualTo(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("[1].name",equalTo("Hazel Stark"));
    }
    @Test
    public void testHasItem(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",hasItem("Hazel Stark"));
    }
    @Test
    public void testHasItems(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",hasItems("Hazel Stark","Steven Bosco"));
    }
    @Test
    public void testNot(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",not(hasItem("Elmaghraby")));
    }
    @Test
    public void testContains(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",contains("Muhamed Elmaghraby","Hazel Stark","Steven Bosco"));
        //must be arranged
    }
    @Test
    public void testContainsInAnyOrder(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",containsInAnyOrder("Hazel Stark","Muhamed Elmaghraby","Steven Bosco"));
        //not must be arranged
    }
    @Test
    public void testEmpty(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                  .then().log().all();
//                .
//                assertThat().body("name",empty());
        //must be fail if it happy case
    }
    @Test
    public void testNotEmpty(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",is(not(empty())));
        //must be success if it happy case
    }
    @Test
    public void testSize(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("name",hasSize(3));
        //must be success if it happy case
    }
    @Test
    public void testTime(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().body("createdAt",everyItem(startsWith("2025")));
        //must be success if it happy case
    }
    @Test
    public void extract(){
        Response res = given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().extract().response();

        System.out.println(res.toString());
    }
    @Test
    public void extractObject(){
        Response res = given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().extract().response();
                String name = res.path("[0].name");
        System.out.println(name);
    }
    @Test
    public void getLogs(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby").log().all()
                .when().get("tasks")
                .then();


    }
    @Test
    public void IfError(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().ifError();
        //to get error
    }
    @Test
    public void assertOnResponseBody(){
        given().baseUri("https://685116798612b47a2c088d2c.mockapi.io/api/maghraby")
                .when().get("tasks")
                .then().log().all().
                assertThat().statusCode(200)
                .body("[2].name",equalTo("Steven Bosco"));
    }
}
