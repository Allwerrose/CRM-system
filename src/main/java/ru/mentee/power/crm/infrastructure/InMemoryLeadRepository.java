package ru.mentee.power.crm.infrastructure;

import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.domain.repository.Repository;

import java.util.*;

class InMemoryLeadRepository implements Repository <Lead> {
 private final ArrayList<Lead> storage =  new ArrayList<>();

  @Override
  public Lead add(Lead entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Lead cannot be null");
    }
    if (!storage.contains(entity)) {
      storage.add(entity);
    }
    return entity;
  }

  @Override
   public void remove(UUID id) {
     if (id == null) {
       throw new IllegalArgumentException("ID cannot be null");
     }

     storage.removeIf(lead -> lead.id().equals(id));
   }

   @Override
   public Optional<Lead> findById(UUID id) {
     if (id == null) {
       return Optional.empty();
     }

     return storage.stream()
       .filter(lead -> lead.id().equals(id))
       .findFirst();
   }

   @Override
   public List<Lead> findAll() {
     return List.copyOf(storage);
   }
 }
