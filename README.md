This is the a project about Java for flight simulation in 42

Agenda
1. What is Java and why. 
2. Program flow
3. Design Pattern
4. UML diagram relationship
5. SOLID principle

## Java and Program Flow
![Program flow](resources/avaj-launcher.svg)

## Design Pattern
## Overview of Implemented Patterns

| Pattern | Implementation | Why Used | Cons |
|---------|----------------|----------|------|
| **Singleton** | `WeatherProvider` class with private constructor and static `getProvider()` method | • **Global Access**: Weather data needed application-wide<br>• **Single Source of Truth**: Consistent weather information<br>• **Resource Management**: Avoids multiple calculation instances | • **Global State**: Difficult testing due to shared state<br>• **Tight Coupling**: Classes become dependent on singleton<br>• **Hidden Dependencies**: Hard to track usage |
| **Observer** | `Tower` (Subject) with `Flyable` observers and `updateConditions()` notifications | • **Loose Coupling**: Tower doesn't know specific aircraft types<br>• **Dynamic Relationships**: Register/unregister at runtime<br>• **Event-Driven**: Automatic weather change notifications | • **Memory Leaks**: Must properly unregister observers<br>• **Performance**: Slow with many observers<br>• **Unexpected Updates**: Hard-to-debug chain reactions |
| **Factory** | `AircraftFactory` with `newAircraft()` method creating different aircraft types | • **Encapsulation**: Hides complex object creation<br>• **Flexibility**: Easy to add new aircraft types<br>• **Centralized Creation**: All creation in one place | • **Complexity**: Adds abstraction layer<br>• **God Object**: Factory can become too large<br>• **Violates OCP**: Must modify factory for new types |

## UML Relationships in Avaj-Launcher

This project uses several key UML relationships to define the interactions and dependencies between its classes.

| Symbol | Relationship Type | Description | Example in Avaj-Launcher |
|--------|-------------------|-------------|--------------------------|
| `◁━━━━` | **Inheritance**<br/>(Generalization) | An "is-a" relationship where a child class inherits from a parent class. | `Helicopter` ◁━━ `Aircraft`<br/>(A Helicopter *is a* specific type of Aircraft) |
| `┅┅┅▻` | **Realization**<br/>(Implementation) | A class implements the operations defined in an interface. | `Aircraft` ┅┅┅▻ `Flyable`<br/>(The Aircraft class *realizes* the Flyable interface) |
| `◆━━━━` | **Composition** | A strong "has-a" relationship where the child cannot exist without the parent. | `Aircraft` ◆━━ `Coordinates`<br/>(An Aircraft is *composed of* Coordinates; they live and die together.) |
| `◇━━━━` | **Aggregation** | A weak "has-a" relationship where the child can exist independently of the parent. | `Tower` ◇━━ `Flyable`<br/>(The Tower *contains* a list of Flyables, but their lifetimes are not tied.) |
| `━━━━>` | **Association** | A structural relationship showing that objects know of and use each other. | `WeatherTower` ━━━━▻ `Flyable`<br/>(The WeatherTower has a direct reference to notify Flyables of weather changes.) |
| `┅┅┅>` | **Dependency** | A "uses-a" relationship where a change to one element may affect another. | `JetPlane.updateConditions()` ┅┅┅▻ `WeatherProvider`<br/>(The method temporarily *depends on* the WeatherProvider.) |

## SOLID Principles in Avaj-Launcher

The SOLID principles are a set of design guidelines that help create more maintainable and scalable software.

| Principle | Description | Example in Avaj-Launcher |
|-----------|-------------|--------------------------|
| **S**<br/>Single Responsibility | A class should have one, and only one, reason to change. | **`Coordinates` class**: Its only responsibility is to hold and manage `longitude`, `latitude`, and `height` data. It doesn't handle flying logic or weather. |
| **O**<br/>Open/Closed | Software entities should be open for extension but closed for modification. | **`Aircraft` class hierarchy**: You can create a new aircraft type (e.g., `SeaPlane`) by extending `Aircraft` and implementing `Flyable` without modifying the existing `Simulator` or `WeatherTower` logic. |
| **L**<br/>Liskov Substitution | Subtypes must be substitutable for their base types without altering the correctness of the program. | **`Helicopter`, `JetPlane`, `Baloon`**: Any of these can be substituted for a `Flyable` or `Aircraft` in the `Simulator`'s list, and the `conditionsChanged()` method will work as expected. |
| **I**<br/>Interface Segregation | Clients should not be forced to depend on interfaces they do not use. | **`Flyable` interface**: It is small and specific (`updateConditions`, `registerTower`). An aircraft doesn't have to implement unrelated methods.|
| **D**<br/>Dependency Inversion | Depend on abstractions, not on concretions. | **`Simulator` & `WeatherTower`**: The `Simulator` depends on the abstract `Flyable` interface to manage all aircraft. |