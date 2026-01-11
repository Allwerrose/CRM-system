package ru.mentee.power.crm.domain.repository;

import ru.mentee.power.crm.domain.Lead;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
  Lead add(T entity);
  void remove(UUID id);
  Optional<T> findById(UUID id);
  List<T> findAll();
}
