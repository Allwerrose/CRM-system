package ru.mentee.power.crm.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.repository.LeadRepository;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.model.LeadStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class LeadRepositoryTest {
  private LeadRepository repository;

  @BeforeEach
  void setUp() {
    repository = new LeadRepository();
  }

  @Test
  void shouldSaveAndFindLeadById_whenLeadSaved() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    repository.save(lead);
    assertThat( repository.findById(lead.id())).isNotNull();
  }

  @Test
  void shouldReturnNull_whenLeadNotFound() {
    assertThat(repository.findById("unknow-id")).isNull();
  }

  @Test
  void shouldReturnAllLeads_whenMultipleLeadsSaved() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    Lead lead1 = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    Lead lead2 = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    repository.save(lead);
    repository.save(lead1);
    repository.save(lead2);
    assertThat(repository.findAll()).hasSize(3);
  }

  @Test
  void shouldDeleteLead_whenLeadExists() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    repository.save(lead);
    repository.delete(lead.id());
    assertThat(repository.findById(lead.id())).isNull();
    assertThat(repository.size() == 0).isEqualTo(true);
  }

  @Test
  void shouldOverwriteLead_whenSaveWithSameId() {
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead("lead-1", contact, "Company", LeadStatus.NEW);
    Contact contact1 = new Contact("ohn@example.bk", "+7999", address);
    Lead lead1 = new Lead("lead-1", contact1, "Company", LeadStatus.NEW);
    repository.save(lead);
    repository.save(lead1);
    assertThat(repository.findById("lead-1")).isEqualTo(lead1);
    assertThat(repository.size()).isEqualTo(1);
  }
  @Test
  void shouldFindFasterWithMap_thanWithListFilter() {
    // Given: Создать 1000 лидов
    List<Lead> leadList = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      String id = UUID.randomUUID().toString();
      Contact contact = new Contact(
        "email" + i + "@test.com",
        "+7" + i,
        new Address("City" + i, "Street" + i, "ZIP" + i)
      );
      Lead lead = new Lead(id, contact, "Company" + i, LeadStatus.NEW);
      repository.save(lead);
      leadList.add(lead);
    }

    String targetId = "lead-500";  // Средний элемент

    // When: Поиск через Map
    long mapStart = System.nanoTime();
    Lead foundInMap = repository.findById(targetId);
    long mapDuration = System.nanoTime() - mapStart;

    // When: Поиск через List.stream().filter()
    long listStart = System.nanoTime();
    Lead foundInList = leadList.stream()
      .filter(lead -> lead.id().equals(targetId))
      .findFirst()
      .orElse(null);
    long listDuration = System.nanoTime() - listStart;

    // Then: Map должен быть минимум в 10 раз быстрее
    assertThat(foundInMap).isEqualTo(foundInList);
    assertThat(listDuration).isGreaterThan(mapDuration * 10);

    System.out.println("Map поиск: " + mapDuration + " ns");
    System.out.println("List поиск: " + listDuration + " ns");
    System.out.println("Ускорение: " + (listDuration / mapDuration) + "x");
  }
  @Test
  void shouldSaveBothLeads_evenWithSameEmailAndPhone_becauseRepositoryDoesNotCheckBusinessRules() {
    Contact sharedContact = new Contact("ivan@mail.ru", "+79001234567",
      new Address("Moscow", "Tverskaya 1", "101000"));
    Lead originalLead = new Lead(UUID.randomUUID().toString(), sharedContact, "Acme Corp", LeadStatus.NEW);
    Lead duplicateLead = new Lead(UUID.randomUUID().toString(), sharedContact, "TechCorp", LeadStatus.NEW);
    repository.save(originalLead);
    repository.save(duplicateLead);
    assertThat(repository.size()).isEqualTo(2);
  }
}
