package ru.mentee.power.crm.domain;

import java.util.Objects;

public record Lead (String id, Contact contact, String company,LeadStatus status) {
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Lead lead = (Lead) o;
    return Objects.equals(id, lead.id) && Objects.equals(company, lead.company) && Objects.equals(contact, lead.contact) && status == lead.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contact, company, status);
  }

  public Lead {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (contact == null) {
      throw new IllegalArgumentException("Contact cannot be null");
    }
    if (contact.phone() == null) {
      throw new IllegalArgumentException("Phone cannot be null");
    }

  }
}
