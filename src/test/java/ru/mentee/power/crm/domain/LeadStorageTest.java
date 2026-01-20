package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.model.LeadStatus;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LeadStorageTest {
  @Test
  void shouldAddLeadWhenLeadIsUnique() {
    LeadStorage storage = new LeadStorage();
    Lead uniqueLead = new Lead(UUID.randomUUID().toString(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    boolean added = storage.add(uniqueLead);
    assertThat(added).isTrue();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(uniqueLead);
  }

  @Test
  void shouldRejectDuplicateWhenEmailAlreadyExists() {
    LeadStorage storage = new LeadStorage();
    Lead existingLead = new Lead(UUID.randomUUID().toString(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead duplicateLead = new Lead(existingLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    storage.add(existingLead);
    boolean added = storage.add(duplicateLead);
    assertThat(added).isFalse();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(existingLead);
  }


  @Test
  void shouldReturnOnlyAddedLeadsWhenFindAllCalled() {
    LeadStorage storage = new LeadStorage();
    Lead firstLead = new Lead(UUID.randomUUID().toString(),new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead secondLead = new Lead(firstLead.id(), new Contact("ivan@mail.bk", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    storage.add(firstLead);
    storage.add(secondLead);
    Lead[] result = storage.findAll();
    assertThat(result).hasSize(2);
    assertThat(result).containsExactly(firstLead, secondLead);
  }
}