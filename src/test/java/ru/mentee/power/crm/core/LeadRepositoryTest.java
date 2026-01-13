package ru.mentee.power.crm.core;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.*;

import java.util.*;

import static java.beans.Beans.isInstanceOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LeadRepositoryTest {
  @Test
  void shouldDeduplicateLeadsById() {
    LeadRepository leadRepository = new LeadRepository();
    Contact sharedContact = new Contact("alice@example.com", "+7888",
      new Address("Saint Petersburg", "Nevsky Prospect", "789"));
    Lead lead = new Lead(UUID.randomUUID(), sharedContact, "TechCorp", LeadStatus.NEW);
    leadRepository.add(lead);
    boolean added = leadRepository.add(lead);
    assertThat(added).isFalse();
    assertThat(leadRepository.size()).isEqualTo(1);

  }

  @Test
  void shouldAllowDifferentLeads() {
    LeadRepository leadRepository = new LeadRepository();
    Contact sharedContact = new Contact("alice@example.com", "+7888",
      new Address("Saint Petersburg", "Nevsky Prospect", "789"));
    Lead lead = new Lead(UUID.randomUUID(), sharedContact, "TechCorp", LeadStatus.NEW);
    Lead lead1 = new Lead(UUID.randomUUID(), sharedContact, "TechCorp", LeadStatus.NEW);
    leadRepository.add(lead);
    boolean added = leadRepository.add(lead1);
    assertThat(added).isTrue();
    assertThat(leadRepository.size()).isEqualTo(2);

  }

  @Test
  void shouldFindExistingLead() {
    // TODO: Given - добавить лид в репозиторий
    // TODO: When - вызвать contains с тем же лидом
    // TODO: Then - проверить что contains вернул true
    LeadRepository leadRepository = new LeadRepository();
    Lead existingLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    leadRepository.add(existingLead);
    boolean added = leadRepository.contains(existingLead);
    assertThat(added).isTrue();
  }

  @Test
  void shouldReturnUnmodifiableSet() {
    // TODO: Given - добавить лид в репозиторий
    // TODO: When - вызвать findAll и попытаться изменить результат
    // TODO: Then - проверить что выбрасывается UnsupportedOperationException
    LeadRepository leadRepository = new LeadRepository();
    Lead existingLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    leadRepository.add(existingLead);
    assertThatThrownBy(() -> {
      leadRepository.findAll().add(new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW));
    })
      .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void shouldPerformFasterThanArrayList() {
    int collectionSize = 10000;
    int iterations = 1000;

    Set<Lead> hashSet = new HashSet<>();
    List<Lead> arrayList = new ArrayList<>();

    for (int i = 0; i < collectionSize; i++) {
      Lead lead = new Lead(UUID.randomUUID(),  new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
      hashSet.add(lead);
      arrayList.add(lead);
    }

    Lead target = arrayList.get(collectionSize / 2);

    long hashSetTotal = 0;
    long arrayListTotal = 0;
    int warmup = 100;
    for (int i = 0; i < warmup; i++) {
      hashSet.contains(target);
      arrayList.contains(target);
    }
    for (int i = 0; i < iterations; i++) {
      long start = System.nanoTime();
      hashSet.contains(target);
      hashSetTotal += System.nanoTime() - start;
      start = System.nanoTime();
      arrayList.contains(target);
      arrayListTotal += System.nanoTime() - start;
    }

    double hashSetAvg = hashSetTotal * 1.0 / iterations;
    double arrayListAvg = arrayListTotal * 1.0 / iterations;
    double ratio = arrayListAvg / hashSetAvg;

    System.out.printf("HashSet average: %.2f ns%n", hashSetAvg);
    System.out.printf("ArrayList average: %.2f ns%n", arrayListAvg);
    System.out.printf("Performance ratio: %.2fx%n", ratio);

  }
}