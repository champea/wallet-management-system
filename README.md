# Wallet Management System

This project is a simple Wallet Management System implemented using Spring Boot. It provides basic bookkeeping (accounting) services, including account creation, balance retrieval, transaction listing, and fund transfer between accounts. The API documentation is generated using Springdoc OpenAPI.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)

## Features

- Create a new account
- Retrieve account balance
- List transactions for an account
- Transfer funds between accounts
- Interactive API documentation using Springdoc OpenAPI

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6.3 or later

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/champea/wallet-management-system.git
    cd wallet-management-system
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

2. The application will start and run on `http://localhost:8080`.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html` after you run the application.

## Endpoints

### Create a New Account

- **URL**: `/api/wallet/account`
- **Method**: `POST`
- **Parameters**:
    - `owner` (String): The name of the account owner.
- **Response**:
    - `201 Created` with the created account details.

### Get Account Balance

- **URL**: `/api/wallet/account/{id}/balance`
- **Method**: `GET`
- **Parameters**:
    - `id` (Long): The ID of the account.
- **Response**:
    - `200 OK` with the account balance.
    - `404 Not Found` if the account does not exist.

### Add/remove Funds to/from an Account

- **URL**: `/api/wallet/createTransaction`
- **Method**: `POST`
- **Parameters**:
    - `accountId` (Long): The ID of the account.
    - `amount` (BigDecimal): The amount to be transferred.
- **Response**:
    - `201 Created` with the transaction details.
    - `404 Not Found` if the account does not exist.

### List Transactions of an Account

- **URL**: `/api/wallet/account/{id}/transactions`
- **Method**: `GET`
- **Parameters**:
    - `id` (Long): The ID of the account.
- **Response**:
    - `200 OK` with the list of transactions.
    - `404 Not Found` if the account does not exist.

### Transfer Funds Between Accounts

- **URL**: `/api/wallet/transfer`
- **Method**: `POST`
- **Parameters**:
    - `fromAccountId` (Long): The ID of the account to transfer funds from.
    - `toAccountId` (Long): The ID of the account to transfer funds to.
    - `amount` (BigDecimal): The amount to be transferred.
- **Response**:
    - `200 OK` if the transfer is successful.
    - `404 Not Found` if any of the accounts do not exist.

## Testing

1. Run the tests:
    ```sh
    mvn test
    ```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
