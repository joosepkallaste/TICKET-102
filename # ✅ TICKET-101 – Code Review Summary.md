# TICKET-101 – Code Review Summary

## Task Summary

TICKET-101 required implementing the MVP of a decision engine that:
- Accepts a personal code, loan amount, and loan period.
- Returns a decision (positive/negative) and the amount we would approve.
- Takes into account mocked segments and debt status.
- Applies a simple credit score formula and finds the maximum valid offer.

---

## What Was Done Well

- **Functional correctness**: The solution meets the task's functional requirements.
- **Clean architecture**: Controller and service responsibilities are clearly separated.
- **Input validation**: Robust error handling for invalid personal codes, amounts, and periods.
- **Constants**: Use of `DecisionEngineConstants` improves maintainability.
- **Testing**: Unit tests exist for both service and controller layers.
- **Fallback logic**: The engine searches for a suitable amount and period when initial input fails.

---

## Areas for Improvement

### 1. **Single Responsibility Violation**
The `DecisionEngine` service handles:
- Credit score calculation
- Loan amount fallback logic
- Debt and segmentation logic

This violates **SRP** and makes the code harder to maintain or extend.

**Suggestion:** Refactor logic into smaller, focused classes:
- `CreditModifierResolver`
- `InputValidator`
- `LoanCalculator`



---


### 2. **Limited Encapsulation**

The `Decision` model is currently just a data carrier (DTO).  
It could be improved by encapsulating its own logic, for example:

- `boolean isApproved()`
- `double calculateScore()`

This would improve clarity and promote better object-oriented design.

---

### 3. **Open/Closed Principle**

Adding new customer segments or changing scoring logic requires modifying the existing `DecisionEngine` class.  
This goes against the **Open/Closed Principle**, which states that classes should be open for extension but closed for modification.

**Suggestion**: Introduce a `ScoringStrategy` interface or apply the Strategy/Rule pattern  
to allow future extensions (e.g. new segments, age-based logic) without modifying core logic.

---

## Most Important Shortcoming

The **tight coupling and bloated responsibilities in `DecisionEngine`** pose the biggest long-term issue.  
This design makes the system harder to extend — especially relevant for adding features like age-based restrictions in **TICKET-102**.


---

## Final Thoughts

The intern delivered a **solid MVP** that meets all core functional goals  
and demonstrates strong understanding of:
- Spring Boot
- RESTful APIs
- Input validation
- Error handling
- Fallback logic for alternative loan options

With some refactoring focused on **SOLID principles**, the code can evolve into a maintainable, scalable foundation for production-level decision logic.

