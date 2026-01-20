
# Сравнение: new внутри vs DI через конструктор

## ❌ BAD: `new InMemoryLeadRepository()` внутри класса 

```java
public class LeadService {
    // Тесная связанность!
    private final LeadRepository repository = 
        new InMemoryLeadRepository();

    public LeadService() {
        // Конструктор пустой - зависимость скрыта
    }
    
    public Lead addLead(String email, String company, LeadStatus status) {
        // Используем жестко заданную зависимость
        return repository.save(createLead(email, company, status));
    }
}