package ru.mentee.power.crm.domain.repository;
import ru.mentee.power.crm.model.Lead;

import java.util.*;

public class LeadRepository {
  private final Map<String, Lead> storage = new HashMap<>();
  private final Map<String, UUID> emailIndex = new HashMap<>();

  public Lead save(Lead lead) {
     storage.put(lead.id(), lead);
    return lead;
  }

  public Lead findById(String id) {
    if(id == null) {
      return null;
    }
        return storage.get(id);
  }

  public List<Lead> findAll() {
    if (storage.isEmpty()) {
      return Collections.emptyList();
    }
    return new ArrayList<>(storage.values());
  }

  public void delete(String id) {
     storage.remove(id);
  }

  public int size() {
    return storage.size();
  }
  public Optional<Lead> findByEmail(String email){
    UUID id = emailIndex.get(email);
    if (id == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(storage.get(id));
  }

}