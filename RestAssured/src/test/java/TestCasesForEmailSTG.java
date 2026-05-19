import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestCasesForEmailSTG {
    String token = "Y2xvdWRjb21haUBnbWFpbC5jb206NTI5OjM5O";

    @Test (priority = 1)
    public void TokenWithStatus(){
        String BODY = """
                {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
                given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                                + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                        .extract().response().then().time(lessThan(13000L));
    }

    @Test (priority = 2)
    public void InvalidTokenWithStatus(){
        String token2 = "Y2xvdWRjb21haUBnbWFpbC5jb206NTI5OjM5O";
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                                }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token2).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(401)
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 3)
    public void ValidEmail(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                          }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(10000L));
    }

    @Test (priority = 4)
    public void InvalidEmail_Format_To(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail."
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                          }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }
    @Test (priority = 5)
    public void InvalidEmail_Format_Sender(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpupl",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 6)
    public void MissingEmail(){
        String BODY = """
         {
                              "channel": 3,
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 7)
    public void ValidChannel_Format(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(5000L));
    }

    @Test (priority = 8)
    public void InvalidChannel_Format(){
        String BODY = """
         {
                              "channel": 5,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 9)
    public void MissingChannel_Format(){
        String BODY = """
         {
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 10)
    public void SendRequestWithoutCampaign(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(10000L));
    }

    @Test (priority = 11)
    public void SendRequestWithoutSenderSenderTemplateMessageInfo(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400).body("responseMessage",equalTo("No Emails Sent"))
                .extract().response().then().time(lessThan(3000L));
    }

    @Test (priority = 12)
    public void SendRequestWithoutFailover(){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(13000L));
    }

    @Test (priority = 13)
    public void AssertThatAPIWorkingWithoutLanguageANDMetaData (){
        String BODY = """
         {
                              "channel": 3,
                              "to": [
                                  "muhamed.gamal2017@gmail.com"
                              ],
                              "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                              "message": {
                                  "subject": "Your Order Confirmation #12345",
                                  "body": "Thank you for your order!"
                              },
                              "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                              }
                          }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(10000L));
    }

    @Test (priority = 14)
    public void AssertThatChannelNotHaveSameChannelForFailOver (){
        String BODY = """
                 {
                                               "channel": 3,
                                               "to": [
                                                   "muhamed.gamal2017@gmail.com"
                                               ],
                                               "campaignId": null,
                              "campaignName": null,
                              "customerId": "98765",
                              "sender": "bulkalerts@arpuplus.net",
                              "serviceProviderId": "117",
                                               "message": {
                                                   "subject": "Your Order Confirmation #12345",
                                                   "body": "Thank you for your order!"
                                               },
                                               "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "+201281559085",
                                                    "templateId": "1882",
                                                    "message": {
                                                    "text": "Your order #12345 has been shipped!",
                                                    "media_url": "https://www.facebook.com",
                                                    "interactive": null
                                                       },
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "muhamed.gamal@cloudcom.io",
                                                    "serviceProviderId": "117",
                                                    "subject": "Test Subject",
                                                    "body": "Test Email Body",
                                                    "to": [
                                                        "muhamed.gamal2017@gmail.com"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                                               }
                                           }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(3000L));
    }

}
