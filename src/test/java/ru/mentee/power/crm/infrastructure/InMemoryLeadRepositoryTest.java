package ru.mentee.power.crm.infrastructure;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Lead;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryLeadRepositoryTest {
  private InMemoryLeadRepository repository;
  private UUID existingId;

  @Test
  void shouldReturnEmptyOptionalWhenLeadNotFound() {
    repository = new InMemoryLeadRepository();
    UUID nonExistingId =UUID.randomUUID();
    Optional<Lead> found = repository.findById(nonExistingId);
    assertFalse(found.isPresent());
  }

}