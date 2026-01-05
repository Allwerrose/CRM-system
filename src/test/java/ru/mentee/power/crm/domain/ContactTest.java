package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class ContactTest {
  @Test
  void shouldBeReflexiveWhenEqualsCalledOnSameObject() {
    Contact contact = new Contact("John", "Doe", "ohn@example.com");
    assertThat(contact).isEqualTo(contact);
  }
  @Test
  void shouldBeSymmetricWhenEqualsCalledOnTwoObjects() {
    Contact firstLead = new Contact("John", "Doe", "ohn@example.com");
    Contact secondLead = new Contact("John", "Doe", "ohn@example.com");
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(firstLead);
  }

  @Test
  void shouldBeTransitiveWhenEqualsChainOfThreeObjects() {
    Contact firstLead = new Contact("John", "Doe", "ohn@example.com");
    Contact secondLead = new Contact("John", "Doe", "ohn@example.com");
    Contact thirdLead = new Contact("John", "Doe", "ohn@example.com");
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(thirdLead);
    assertThat(firstLead).isEqualTo(thirdLead);
  }

  @Test
  void shouldBeConsistentWhenEqualsCalledMultipleTimes() {
    Contact firstLead = new Contact("John", "Doe", "ohn@example.com");
    Contact secondLead = new Contact("John", "Doe", "ohn@example.com");
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
  }

  @Test
  void shouldReturnFalseWhenEqualsComparedWithNull() {
    Contact lead = new Contact("John", "Doe", "ohn@example.com");
    assertThat(lead).isNotEqualTo(null);
  }

  @Test
  void shouldHaveSameHashCodeWhenObjectsAreEqual() {
    Contact firstLead = new Contact("John", "Doe", "ohn@example.com");
    Contact secondLead = new Contact("John", "Doe", "ohn@example.com");
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead.hashCode()).isEqualTo(secondLead.hashCode());
  }

  @Test
  void shouldWorkInHashMapWhenLeadUsedAsKey() {
    Contact keyLead = new Contact("John", "Doe", "ohn@example.com");
    Contact lookupLead = new Contact("John", "Doe", "ohn@example.com");
    Map<Contact, String> map = new HashMap<>();
    map.put(keyLead, "CONTACTED");
    String status = map.get(lookupLead);
    assertThat(status).isEqualTo("CONTACTED");
  }

  @Test
  void shouldNotBeEqualWhenIdsAreDifferent() {
    Contact firstLead = new Contact("John", "Doe", "ohn@example.com");
    Contact differentLead = new Contact("Joh", "Doe", "ohn@example.com");
    assertThat(firstLead).isNotEqualTo(differentLead);
  }

}