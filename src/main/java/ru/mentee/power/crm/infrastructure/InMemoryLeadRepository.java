package ru.mentee.power.crm.infrastructure;

import ru.mentee.power.crm.domain.repository.LeadRepository;
import ru.mentee.power.crm.model.Lead;
import ru.mentee.power.crm.domain.repository.Repository;

import java.util.*;

public class InMemoryLeadRepository extends LeadRepository implements Repository <Lead> {

  private final Map<UUID, Lead> storage = new HashMap<>();
  private final Map<String, UUID> emailIndex = new HashMap<>();

  @Override
  public Lead save(Lead lead) {
    // Просто сохраняем — никакой бизнес-логики!
    storage.put(UUID.fromString(lead.id()), lead);
    emailIndex.put(lead.contact().email(), UUID.fromString(lead.id()));
    return lead;
  }

  @Override
  public Optional<Lead> findById(UUID id) {
    return Optional.ofNullable(storage.get(id));
  }

  @Override
  public Optional<Lead> findByEmail(String email) {
    UUID id = emailIndex.get(email);
    if (id == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(storage.get(id));
  }

  @Override
  public List<Lead> findAll() {
    return new ArrayList<>(storage.values());
  }

  @Override
  public void delete(UUID id) {
    Lead lead = storage.remove(id);
    if (lead != null) {
      emailIndex.remove(lead.contact().email());
    }
  }

  @Override
  public Lead add(Lead entity) {
    return null;
  }

  @Override
  public void remove(UUID id) {

  }
}
