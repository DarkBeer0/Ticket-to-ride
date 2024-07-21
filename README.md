# Ticket to Ride

## Features
- Calculate the price of the most optimal travel route between two towns.
- Purchase tickets if the traveler has enough money.
- Layered architecture with separation of concerns.
- Persistence of successfully bought tickets.
- Basic authentication for the ticket purchasing API.

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven
- JUnit 5
- Mockito

## Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/DarkBeer0/Ticket-to-ride.git
    cd ticket-to-ride
    ```

2. **Configure the database:**
    Update the `application.properties` file with your PostgreSQL database configuration.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ticket_to_ride
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Import the database schema:**
    Run the following command to import the database schema:
    ```bash
    psql -U yourusername -d ticket_to_ride -f db/schema.sql
    ```

4. **Build and run the application:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. **Access the application:**
    Open your browser and go to `http://localhost:8080`.

## API Endpoints

### Calculate Price

- **URL:** `/api/tickets/calculate`
- **Method:** `GET`
- **Query Parameters:**
  - `departure` (String): The departure town.
  - `arrival` (String): The arrival town.
- **Response:**
  ```json
  {
      "segments": 7,
      "price": 25,
      "currency": "GBP"
  }
