package ru.mentee.power.crm.domain;

public record Contact(String email, String phone, Address address) {
  public Contact {
    if (email == null || email == "" ) {
      throw new IllegalArgumentException("email не должен быть пуст");
    }
    if (phone == null || phone == "") {
      throw new IllegalArgumentException("phone не должен быть пуст");
    }
    if (address == null ) {
      throw new IllegalArgumentException("address не должен быть null");
    }
  }
}
