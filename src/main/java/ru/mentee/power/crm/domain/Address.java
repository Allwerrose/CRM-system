package ru.mentee.power.crm.domain;

public record Address(String city, String street, String zip) {
  public Address {
    if (city == null ) {
      throw new IllegalArgumentException("City cannot be null");
    }
    if (street == null) {
      throw new IllegalArgumentException("Street cannot be null");
    }
    if (zip == null || zip == "") {
      throw new IllegalArgumentException("Street cannot be null");
    }
  }
}
