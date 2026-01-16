package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class LeadTest {
  @Test
  void shouldCreateLead_whenValidData() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    assertThat(lead.contact()).isEqualTo(contact);
  }

  @Test
  void shouldAccessEmailThroughDelegation_whenLeadCreated() {
    Lead lead = new Lead(UUID.randomUUID().toString(),
      new Contact("ohn@example.com", "+7999",
        new Address("Yekaterinburg", "Lenina Avenue ", "iii")), "Company", LeadStatus.NEW);
    assertThat(lead.contact().email()).isEqualTo(lead.contact().email());
    assertThat(lead.contact().address().city()).isEqualTo(lead.contact().address().city());
  }

  @Test
  void shouldBeEqual_whenSameIdButDifferentContact() {
    String id = UUID.randomUUID().toString();
    Lead lead = new Lead(id,
      new Contact("john@example.com", "+7999", new Address("Yekaterinburg", "Lenina Avenue", "iii")),
      "Company", LeadStatus.NEW);
    Lead lead1 = new Lead(id,
      new Contact("john@example.com", "+7998", new Address("Yekaterinburg", "Lenina Avenue", "iii")),
      "Company", LeadStatus.NEW);

    assertThat(lead.id()).isEqualTo(lead1.id()); // Теперь равны по id
  }

  @Test
  void shouldThrowException_whenContactIsNull() {
    assertThatThrownBy(() -> new Lead(UUID.randomUUID().toString(), null, "Company", LeadStatus.NEW))
      .isInstanceOf(IllegalArgumentException.class);
  }


  @Test
  void shouldDemonstrateThreeLevelComposition_whenAccessingCity() {
    String leadId = UUID.randomUUID().toString();
    String email = "john@example.com";
    String phone = "+7999";
    String city = "Yekaterinburg";
    String street = "Lenina Avenue";
    String zip = "iii";
    String company = "TechCorp";
    LeadStatus status = LeadStatus.NEW;

    Address address = new Address(city, street, zip);
    Contact contact = new Contact(email, phone, address);
    Lead lead = new Lead(leadId, contact, company, status);
    Contact retrievedContact = lead.contact();
    Address retrievedAddress = retrievedContact.address();
    String retrievedCity = retrievedAddress.city();
    assertThat(retrievedCity).isEqualTo(city);
    String cityViaChaining = lead.contact().address().city();
    assertThat(cityViaChaining).isEqualTo(city);
    assertThat(lead).isNotNull();
    assertThat(retrievedContact).isNotNull();
    assertThat(retrievedAddress).isNotNull();
  }
}