package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.model.LeadStatus;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

  class CustomerTest {

    @Test
    void shouldReuseContact_whenCreatingCustomer() {
      Contact contact = new Contact("john@example.com", "+7999",
        new Address("Yekaterinburg", "Lenina Avenue", "iii"));
      Address billingAddress = new Address("Moscow", "Tverskaya Street", "456");
      Customer customer = new Customer(UUID.randomUUID(), contact, billingAddress, "SILVER");
      assertThat(customer.contact().address())
        .isNotEqualTo(customer.billingAddress());
      System.out.println("Contact address: " + customer.contact().address());
      System.out.println("Billing address: " + customer.billingAddress());
    }

    @Test
    void shouldDemonstrateContactReuse_acrossLeadAndCustomer() {
      Contact sharedContact = new Contact("alice@example.com", "+7888",
        new Address("Saint Petersburg", "Nevsky Prospect", "789"));
      Lead lead = new Lead(UUID.randomUUID().toString(), sharedContact, "TechCorp", LeadStatus.NEW);
      Customer customer = new Customer(UUID.randomUUID(), sharedContact, new Address("Kazan", "Bauman Street", "101"), "GOLD");
      assertThat(lead.contact()).isSameAs(sharedContact);
      assertThat(customer.contact()).isSameAs(sharedContact);
      assertThat(lead.contact() == customer.contact()).isTrue();
      assertThat(lead.contact().email()).isEqualTo("alice@example.com");
      assertThat(customer.contact().phone()).isEqualTo("+7888");
    }
  }
