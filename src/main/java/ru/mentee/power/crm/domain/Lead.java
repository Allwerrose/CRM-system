package ru.mentee.power.crm.domain;

import java.util.UUID;

public record Lead (UUID id, String email, String phone, String company, String status) {
  public Lead {
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be blank");
    }
    if (status == null) {
      throw new IllegalArgumentException("Status cannot be null");
    }
  }
}
