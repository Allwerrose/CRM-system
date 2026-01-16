package ru.mentee.power.crm.infrastructure;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.domain.LeadStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryLeadRepositoryTest {
  private InMemoryLeadRepository repository;
  private UUID existingId;

  @Test
  void shouldAddLeadAndReturnInFindAll() {
    repository = new InMemoryLeadRepository();
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead firstLead = new Lead("***", contact, "Company", LeadStatus.NEW);
    Lead addedLead = repository.add(firstLead);
    List<Lead> allLeads = repository.findAll();
    assertEquals(1, allLeads.size());
    assertTrue(allLeads.contains(firstLead));
    assertEquals(firstLead, addedLead);
  }

  @Test
  void shouldNotAddDuplicateLeads() {
    repository = new InMemoryLeadRepository();
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead firstLead = new Lead("***", contact, "Company", LeadStatus.NEW);
    repository.add(firstLead);
    repository.add(firstLead);
    assertEquals(1, repository.findAll().size());
  }

  @Test
  void shouldFindLeadByIdWhenExists() {
    repository = new InMemoryLeadRepository();
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead firstLead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    existingId = UUID.fromString(firstLead.id());
    repository.add(firstLead);
    Optional<Lead> found = repository.findById(existingId);
    assertTrue(found.isPresent());
    assertEquals(firstLead, found.get());
  }

  @Test
  void shouldReturnEmptyOptionalWhenLeadNotFound() {
    repository = new InMemoryLeadRepository();
    UUID nonExistingId =UUID.randomUUID();
    Optional<Lead> found = repository.findById(nonExistingId);
    assertFalse(found.isPresent());
  }

  @Test
  void shouldRemoveLeadById() {
    repository = new InMemoryLeadRepository();
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead firstLead = new Lead(UUID.randomUUID().toString(), contact, "Company", LeadStatus.NEW);
    existingId = UUID.fromString(firstLead.id());
    repository.add(firstLead);
    assertTrue(repository.findById(UUID.fromString(firstLead.id())).isPresent());
    repository.remove(UUID.fromString(firstLead.id()));
    Optional<Lead> foundAfterRemoval = repository.findById(existingId);
    assertFalse(foundAfterRemoval.isPresent());
    assertEquals(0, repository.findAll().size());
  }

  @Test
  void integrationTest() {
    repository = new InMemoryLeadRepository();
    UUID lead1Id = UUID.randomUUID();
    UUID lead2Id = UUID.randomUUID();
    Address address = new Address("Yekaterinburg", "Lenina Avenue ", "iii");
    Contact contact = new Contact("ohn@example.com", "+7999", address);
    Lead lead = new Lead(lead1Id.toString(), contact, "Company", LeadStatus.NEW);
    assertThat(lead.contact()).isEqualTo(contact);
    Lead lead2 = new Lead(lead2Id.toString(), contact, "CompanyTech", LeadStatus.NEW);
    assertThat(lead.contact()).isEqualTo(contact);
    repository.add(lead);
    repository.add(lead2);
    assertEquals(2, repository.findAll().size());
    Optional<Lead> foundLead1 = repository.findById(lead1Id);
    assertTrue(foundLead1.isPresent());
  }

}