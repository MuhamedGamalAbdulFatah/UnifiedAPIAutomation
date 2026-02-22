import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FailOverCases {
    String token = "c2hvcGlmeUBhcnB1cGx1cy5jb206MTI2OjIyOA==";


    /// Send To SMS
    @Test (priority = 1)
    public void failoverEnable(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287583179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                            "media_url": null,
               
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
                assertThat().statusCode(200).body("responseMessage",equalTo("SMS Sent Successfully"))
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 2)
    public void failoverDisable1(){
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

    @Test (priority = 3)
    public void failoverDisable2(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287385179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
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

    @Test (priority = 4)
    public void failoverEnableValidation_Channel(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287385179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
                        "interactive": null
                    },
                     "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "sender": "ARPUPLUS",
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
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
                assertThat().statusCode(400)
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 5)
    public void failoverEnableValidation_Sender(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287385179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
                        "interactive": null
                    },
                     "failover": {
                                            "enabled": true,
                                            "channels": [
                                                {
                                                    "channel": 2,
                                                    "templateId": null,
                                                    "serviceProviderId": "71",
                                                    "text": "Test SMS from API",
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 3,
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

    @Test (priority = 6)
    public void failoverEnableValidation_TextSubjectBody(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287385179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
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
                                                    "to": [
                                                        "201063783757"
                                                    ]
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
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

    @Test (priority = 7)
    public void failoverEnable_to(){
        String BODY = """
                {
                    "channel": 1,
                    "to": [
                        "201287385179"
                    ],
                    "campaignId": null ,
                    "campaignName": null,
                    "customerId": "98765",
                    "sender": "+201281559085",
                    "templateId": "1871",
                    "message": {
                        "text": "Your order #12345 has been shipped!",
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
                                                    "text": "Test SMS from API"
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
                                                    "subject": "Test Subject",
                                                    "body": "Test Email Body"
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

    @Test(priority = 8)
    public void AssertThatFailoverEnableToSendForMultiUsers(){
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
                                                        "201063783757","201287385179"
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

    @Test (priority = 9)
    public void failoverEnableValidationOnNumberReceiver(){
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
                                                        "2010637837"
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
                assertThat().statusCode(200).body("responseMessage",equalTo(null))
                .extract().response().then().time(lessThan(32000L));
    }

    @Test (priority = 10)
    public void failoverEnableValidationOnMailReceiver(){
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
                                                    "text": "Test SMS from API"
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
                                                    "subject": "Test Subject",
                                                    "body": "Test Email Body",
                                                    "to": [
                                                        "muhamed.gamal2017@"
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

    @Test (priority = 11)
    public void failoverEnableValidationOnMailSender(){
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
                                                    "text": "Test SMS from API"
                                                },
                                                {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arp",
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
    /// Send To Email
    @Test (priority = 12)
    public void failoverEnableSendToEmailArrange(){
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
                        "interactive": null
                    },
                     "failover": {
                                            "enabled": true,
                                            "channels": [
                                            {
                                                    "channel": 3,
                                                    "sender": "bulkalerts@arpuplus.net",
                                                    "serviceProviderId": "117",
                                                     "subject": "Test Subject",
                                                    "body": "Test Email Body",
                                                    "to": [
                                                        "muhamed.gamal2017@gmail.com"
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
                assertThat().statusCode(200).body("responseMessage",equalTo("Email Sent Successfully"))
                .extract().response().then().time(lessThan(32000L));
    }

}
