package ru.mentee.power.crm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.model.LeadStatus;
import ru.mentee.power.crm.domain.repository.LeadRepository;
import ru.mentee.power.crm.infrastructure.InMemoryLeadRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class LeadServiceTest {

  private LeadService service;
  private LeadRepository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemoryLeadRepository();
    service = new LeadService(repository);
  }

  @Test
  void shouldCreateLead_whenEmailIsUnique() {
    // Given
    String email = "ivan@mail.ru";
    String company = "TechCorp";
    LeadStatus status = LeadStatus.NEW;

    // When
    Lead result = service.addLead(email, company, status);

    // Then
    assertThat(result).isNotNull();
    assertThat(result.contact().email()).isEqualTo(email);
    assertThat(result.company()).isEqualTo(company);
    assertThat(result.status()).isEqualTo(status);
    assertThat(result.id()).isNotNull();
  }

  @Test
  void shouldThrowException_whenEmailAlreadyExists() {
    // Given
    String email = "duplicate@example.com";
    service.addLead(email, "First Company", LeadStatus.NEW);
    // When/Then
    assertThatThrownBy(() -> service.addLead(email, "Second Company", LeadStatus.NEW))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void shouldFindAllLeads() {
    // Given
    service.addLead("one@example.com", "Company 1", LeadStatus.NEW);
    service.addLead("two@example.com", "Company 2", LeadStatus.CONVERTED);

    // When
    List<Lead> result = service.findAll();

    // Then
    assertThat(result).hasSize(2);
  }


  @Test
  void shouldReturnEmpty_whenLeadNotFound() {
    // Given/When
    Optional<Lead> result = service.findByEmail("nonexistent@example.com");

    // Then
    assertThat(result).isEmpty();
  }
}