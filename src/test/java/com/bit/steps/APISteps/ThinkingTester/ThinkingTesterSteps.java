package com.bit.steps.APISteps.ThinkingTester;

import com.bit.api.ThinkingTester.ContactApiClient;
import com.bit.context.TestContext;
import com.bit.objects.api.ThinkingTesterApp.Contact;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ThinkingTesterSteps {
    private TestContext testContext ;
    private final ContactApiClient contactApiClient=new ContactApiClient();

    private Response response;
    private Contact expectedContact;

    public ThinkingTesterSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @When("User get all contacts")
    public void iSendGetRequestToContacts() {
        response = contactApiClient.getAllContacts();
        testContext.setResponse(response);
    }

    @Then("User get contact by ID {string}")
    public void iSendGetRequestToContactById(String id) {
        response = contactApiClient.getContactsById(id);
        testContext.setResponse(response);
    }
    @When("User create a new contact with firstName {string}, lastName {string}, birthdate {string}, email {string}, phone {string}, street1 {string}, city {string}, owner {string}")
    public void iSendPostRequestToCreateContact(String firstName, String lastName, String birthdate, String email, String phone, String street1, String city, String owner) {
        expectedContact = Contact.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthdate(birthdate)
                .email(email)
                .phone(phone)
                .street1(street1)
                .city(city)
                .owner(owner)
                .build();
        response = contactApiClient.createContact(expectedContact);
        testContext.setResponse(response);
    }


}
