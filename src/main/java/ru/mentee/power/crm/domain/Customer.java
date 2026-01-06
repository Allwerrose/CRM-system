package ru.mentee.power.crm.domain;

import java.util.Objects;
import java.util.UUID;

public record Customer (UUID id, Contact contact, Address billingAddress, String loyaltyTier) {
  public Customer {
    Objects.requireNonNull(id, "id cannot be null");
    Objects.requireNonNull(contact, "contact cannot be null");
    Objects.requireNonNull(billingAddress, "billingAddress cannot be null");
    if (loyaltyTier == null || !isValidLoyaltyTier(loyaltyTier)) {
      throw new IllegalArgumentException(
        "loyaltyTier must be one of: BRONZE, SILVER, GOLD (got: " + loyaltyTier + ")"
      );
    }
  }

  private boolean isValidLoyaltyTier(String loyaltyTier) {
    return "BRONZE".equals(loyaltyTier) ||
      "SILVER".equals(loyaltyTier) ||
      "GOLD".equals(loyaltyTier);
  }
}
