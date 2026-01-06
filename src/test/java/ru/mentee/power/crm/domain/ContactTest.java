package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;


class ContactTest {
  @Test
  void shouldCreateContact_whenValidData() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    assertThat(address).isEqualTo(contact.address());
    assertThat(contact.address().city()).isEqualTo(address.city());
  }
  @Test
  void shouldDelegateToAddress_whenAccessingCity() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    assertThat(contact.address().city()).isEqualTo(address.city());
    assertThat(contact.address().street()).isEqualTo(address.street());
  }

  @Test
  void shouldThrowException_whenAddressIsNull() {
   assertThatThrownBy(() -> new Contact("ohn@example.com", "+7999", null))
     .isInstanceOf(IllegalArgumentException.class);
  }
}