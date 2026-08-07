package com.bit.api.ThinkingTester;

import com.bit.api.BaseApi;
import com.bit.objects.api.ThinkingTesterApp.Contact;
import com.bit.utils.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ContactApiClient extends BaseApi {
    private static final String CONTACTS_ENDPOINT = "/contacts";
    private final String  token = ConfigReader.get("api.thinkingToken");
    private static final String BASE_URL = ConfigReader.get("api.thinkingTester");


    public Response createContact(Contact contact) {
        return given()
                .spec(getAuthRequestSpec(BASE_URL,token))
                .body(contact)
                .post(CONTACTS_ENDPOINT);
    }

    public Contact getContactsResponse(Response  response) {
        return response
                .then()
                .extract()
                .as(Contact.class); // Jackson автоматизирует конвертацию!
    }

    public Response getContactsById(String userId) {
        return given()
                .spec(getAuthRequestSpec(BASE_URL,token))
                .get(CONTACTS_ENDPOINT + "/" + userId);
    }

    public Response getAllContacts() {
        return given()
                .spec(getAuthRequestSpec(BASE_URL,token))
                .get(CONTACTS_ENDPOINT);
    }


}
