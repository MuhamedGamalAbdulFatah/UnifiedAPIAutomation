import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestCasesForWhatsappSTG {
    String token = "c2hvcGlmeUBhcnB1cGx1cy5jb206MTI2OjIyO";

    @Test (priority = 1)
    public void TokenWithStatus(){
        String BODY = """
        {
                    "channel": 1,
                    "to": [
                        "+201019349247"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
                        "media_url": "https://www.facebook.com",
                        "interactive": null
                    },
                     "failover": {
                                            "enabled": false,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
                                                    "subject": "Test Subject",
                                                    "body": "Test Email Body",
                                                    "to": [
                                                        "muhamed.gamal2017@gmail.com"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
                    },
                    "metadata": {
                        "tags": [
                            "shipping",
                            "automated"
                        ],
                        "language": "en",
                        "custom_fields": {
                            "order_id": "12345"
                        }
                    }
                }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                                + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 2)
    public void InvalidTokenWithStatus(){
        String token2 = "Y2xvdWRjb21haUBnbWFpbC5jb206NTI5OjM5O";
        String BODY = """
        {
            "channel": 1,
            "to": [
                "+201063783757"
            ],
             "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
           "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token2).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(401)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 3)
    public void ValidPhoneNumber(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "+201287385179"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
             "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 4)
    public void InvalidPhoneNumber_Format(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "2010637837"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 5)
    public void MissingPhoneNumber(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                ""
            ],
             "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "+201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
                                                    "subject": "Test Subject",
                                                    "body": "Test Email Body",
                                                    "to": [
                                                        "muhamed.gamal2017@gmail.com"
                                                    ]
                                                }
                                            ],
                                            "timeout": 0
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 6)
    public void ValidChannel_Format(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "+201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

//handling for madinet masr
    @Test (priority = 7)
    public void InvalidChannel_Format(){
        String BODY = """
        {
                    "channel": 5,
            "to": [
                "+201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                "enabled": true,
               "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(32000L));
    }

    //handling for madinet masr
    @Test (priority = 8)
    public void MissingChannel_Format(){
        String BODY = """
        {
            "to": [
                "201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 9)
    public void SendRequestWithoutCampaign(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "201063783757"
            ],
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 10)
    public void SendRequestWithoutSenderSenderTemplateMessageInfo(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400).body("responseMessage",equalTo("Please provide template ID or message text"))
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 11)
    public void SendRequestWithoutFailover(){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",
                "interactive": null
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(200)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 12)
    public void AssertThatAPIWorkingWithoutLanguageANDMetaData (){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                               "media_url": "https://www.facebook.com",

                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 13)
    public void AssertThatChannelNotHaveSameChannelForFailOver (){
        String BODY = """
        {
            "channel": 1,
            "to": [
                "201063783757"
            ],
            "campaignId": null ,
            "campaignName": null,
            "customerId": "98765",
            "sender": "+201281559085",
            "templateId": "1871",
            "message": {
                "text": "Your order #12345 has been shipped!",
                "media_url": "https://www.facebook.com",

                "interactive": null
            },
            "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 1,
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
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
            },
            "metadata": {
                "tags": [
                    "shipping",
                    "automated"
                ],
                "language": "en",
                "custom_fields": {
                    "order_id": "12345"
                }
            }
        }
        """;
        given().baseUri("https://stagingapi.cloudcom.me").body(BODY).header("Authorization", "Basic "
                        + token).contentType("application/json")
                .when().post("/api/v1/MultiChannel/sendmessage")
                .then().log().all().
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(32000L));
    }

}
