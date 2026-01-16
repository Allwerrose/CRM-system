package ru.mentee.power.crm.service;

import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.domain.LeadStatus;
import ru.mentee.power.crm.core.LeadRepository;
import ru.mentee.power.crm.domain.repository.Repository;

import java.util.*;

public class LeadService {

  private final LeadRepository repository;
  private final Map<UUID, Lead> storage = new HashMap<>();
  private final Map<String, UUID> emailIndex = new HashMap<>();

  // DI через конструктор — не создаём repository внутри!
  public LeadService(LeadRepository repository) {
    this.repository = repository;
  }

  /**
   * Создаёт нового лида с проверкой уникальности email.
   *
   * @throws IllegalStateException если лид с таким email уже существует
   */
  public Lead addLead(String email, String company, LeadStatus status) {
    // Бизнес-правило: проверка уникальности email
    Optional<Lead> existing = repository.findByEmail(email);
    if (existing.isPresent()) {
      throw new IllegalStateException("Lead with email already exists: " + email);
    }

    // Создаём нового лида
    Lead lead = new Lead(UUID.randomUUID().toString(),
      new Contact(email, "+7123", // Используем переданный email!
        new Address("Moscow", "Lenina Avenue", "111")),
      company, // Используем переданную компанию!
      status   // Используем переданный статус!
    );
    // Сохраняем через repository
    return repository.save(lead);
  }


  public List<Lead> findAll() {
   return repository.findAll();
  }

  public Optional<Lead> findById(UUID id) {
    return Optional.ofNullable(repository.findById(String.valueOf((id))));
  }

  public Optional<Lead> findByEmail(String email) {
    for (Lead lead : storage.values()) {
      if (lead.contact() != null &&
        lead.contact().email() != null &&
        lead.contact().email().equals(email)) {
        return Optional.of(lead);
      }
    }
    return Optional.empty();
  }

}