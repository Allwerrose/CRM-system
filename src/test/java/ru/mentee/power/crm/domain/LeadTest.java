package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LeadTest {
  @Test
  void shouldReturnIdWhenGetIdLead() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String id = lead.getId();
    Assertions.assertEquals("L1", id);
  }

  @Test
  void shouldReturnIdWhenGetEmailLead() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String email = lead.getEmail();
    Assertions.assertEquals("test@example.com", email);
  }
  @Test
  void shouldReturnIdWhenGetPhoneLead() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String phone = lead.getPhone();
    Assertions.assertEquals("+71234567890", phone);
  }
  @Test
  void shouldReturnIdWhenGetCompanyLead() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String company = lead.getCompany();
    Assertions.assertEquals("TestCorp", company);
  }
  @Test
  void shouldReturnIdWhenGetStatusLead() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String status = lead.getStatus();
    Assertions.assertEquals("NEW", status);
  }
  @Test
  void shouldReturnStringWithValues() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");
    String toString = lead.toString();
    Assertions.assertEquals("Lead {id= L1, email= test@example.com, phone= +71234567890, company= " +
      "TestCorp, status= NEW}", toString);


  }
}