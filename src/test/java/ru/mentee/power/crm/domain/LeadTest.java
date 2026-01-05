package ru.mentee.power.crm.domain;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LeadTest {
  @Test
  void shouldReturnIdWhenGetIdLead() {
    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    UUID id = lead.id();
    Assertions.assertEquals(lead.id(), id);
  }

  @Test
  void shouldReturnIdWhenGetEmailLead() {
    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    String email = lead.email();
    Assertions.assertEquals("test@example.com", email);
  }
  @Test
  void shouldReturnIdWhenGetPhoneLead() {
    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    String phone = lead.phone();
    Assertions.assertEquals("+71234567890", phone);
  }
  @Test
  void shouldReturnIdWhenGetCompanyLead() {
    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    String company = lead.company();
    Assertions.assertEquals("TestCorp", company);
  }
  @Test
  void shouldReturnIdWhenGetStatusLead() {
    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    String status = lead.status();
    Assertions.assertEquals("NEW", status);
  }
//  @Test
//  void shouldReturnStringWithValues() {
//    Lead lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
//    String toString = lead.toString();
//    Assertions.assertEquals("Lead {id= , email= test@example.com, phone= +71234567890, company= " +
//      "TestCorp, status= NEW}", toString);
//  }
}