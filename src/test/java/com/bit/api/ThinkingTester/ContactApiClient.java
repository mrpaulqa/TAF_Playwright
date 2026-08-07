package com.bit.api.ThinkingTester;

import com.bit.api.BaseApi;
import com.bit.objects.api.ThinkingTesterApp.Contact;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.bit.api.specs.RequestSpecFactory.getRequestSpecification;
import static io.restassured.RestAssured.given;

public class ContactApiClient extends BaseApi {
    private static final String CONTACTS_ENDPOINT = "/contacts";
    private final RequestSpecification  requestSpecification =getRequestSpecification(SpectType.AUTH);

    public Response createContact(Contact contact) {
        return given()
                .spec(requestSpecification)
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
                .spec(requestSpecification)
                .get(CONTACTS_ENDPOINT + "/" + userId);
    }

    public Response getAllContacts() {
        return given()
                .spec(requestSpecification)
                .get(CONTACTS_ENDPOINT);
    }


}
