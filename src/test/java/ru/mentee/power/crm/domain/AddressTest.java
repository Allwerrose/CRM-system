package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class AddressTest {

  @Test
  void shouldCreateAddress_whenValidData() {
     Address address = new Address("San Francisco", "123 Main St", "94105");
    assertThat(address).isEqualTo(address);
  }

  @Test
  void shouldBeEqual_whenSameData() {
    Address firstAddress = new Address("San Francisco", "123 Main St", "94105");
    Address secondAddress = new Address("San Francisco", "123 Main St", "94105");
    assertThat(firstAddress).isEqualTo(secondAddress);
    assertThat(secondAddress.hashCode()).isEqualTo(firstAddress.hashCode());
  }
  @Test
  void shouldThrowException_whenCityIsNull() {
    assertThatThrownBy(() -> new Address(null, "123 Main St", "94105"))
      .isInstanceOf(IllegalArgumentException.class);
  }
  @Test
  void shouldThrowException_whenZipIsBlank() {
    assertThatThrownBy(() -> new Address("San Francisco", "123 Main St", ""))
      .isInstanceOf(IllegalArgumentException.class);
  }

}