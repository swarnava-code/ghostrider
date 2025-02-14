![Logo](src/main/resources/static/logo.png)

# GHOST RIDER APP

## Module-1: 
- Server side Backend Project, also called `Ghost Rider App`
- Java
- Spring Boot

## Module-2: (Action item)
- Client side Frontend Projects, also called `Angel Three` or `Angel AAA`
  1. Angular
  2. Android
  3. Apple ioS


## Description
Demo Rider Project for Spring Boot. It will help passengers and rider to find each other.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/ghostrider.git
   ```
2. Navigate to the project directory:
   ```sh
   cd ghostrider
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Setup PostgreSQL db


## Responsibilities of the Client App (to minimize server load)
1. They should keep data locally and check for differences in PINCODE and CITY
   - make http call to update PINCODE : [PATCH]`rider/pincode/{new_pincode}`
   - make http call to update CITY : [PATCH]`rider/city/{new_city}`
2. They should update their live coordinates every 10 second if it changed (This will not make any change to db)
   - make http call to send coordinates : [PATCH]`/coordinates/{latitude,longitude}`


## Usage
Need to deploy in a server and should be running, It will help passengers and rider to find each other

## Contributing
- Owner can apply changes directly in main
- Others should create a branch, and add owner as reviewer

## License
It's a demo app [Free]

## Credits
- [Security](https://medium.com/code-with-farhan/spring-security-jwt-authentication-authorization-a2c6860be3cf)

### TO-DO List
- replace runtime exception handler, create more custom exception
- replace native query
- impl security
- add more constraint to db
- add more validation to dto and entity class
- improve entity relationship and foreign key

