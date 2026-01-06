import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.domain.LeadStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LeadEqualsHashCodeTest {
  @Test
  void shouldBeReflexiveWhenEqualsCalledOnSameObject() {
    Lead lead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(lead).isEqualTo(lead);
  }
  @Test
  void shouldBeSymmetric_whenEqualsCalledOnTwoObjects() {
    Lead firstLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead secondLead = new Lead(firstLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(firstLead);
  }

  @Test
  void shouldBeTransitive_whenEqualsChainOfThreeObjects() {
    Lead firstLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead secondLead = new Lead(firstLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead thirdLead = new Lead(secondLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(thirdLead);
    assertThat(firstLead).isEqualTo(thirdLead);
  }

  @Test
  void shouldBeConsistent_whenEqualsCalledMultipleTimes() {
    Lead firstLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead secondLead = new Lead(firstLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
  }

  @Test
  void shouldReturnFalse_whenEqualsComparedWithNull() {
    Lead lead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(lead).isNotEqualTo(null);
  }

  @Test
  void shouldHaveSameHashCode_whenObjectsAreEqual() {
    Lead firstLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead secondLead = new Lead(firstLead.id(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead.hashCode()).isEqualTo(secondLead.hashCode());
  }

  @Test
  void shouldWorkInHashMap_whenLeadUsedAsKey() {
    Lead keyLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead lookupLead = new Lead(keyLead.id(),new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Map<Lead, String> map = new HashMap<>();
    map.put(keyLead, "CONTACTED");
    String status = map.get(lookupLead);
    assertThat(status).isEqualTo("CONTACTED");
  }

  @Test
  void shouldNotBeEqual_whenIdsAreDifferent() {
    Lead firstLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    Lead differentLead = new Lead(UUID.randomUUID(), new Contact("ivan@mail.ru", "+7123", new Address("Moscow", "Lenina Avenue", "111")), "TechCorp", LeadStatus.NEW);
    assertThat(firstLead).isNotEqualTo(differentLead);
  }
}
