This project provides the background on how to test spring boot application using unit test v5 seamlessly.

This project covers:

- Unit Testing
- Integration Testing (@DataJpaTest, @MockBean, @TestConfiguration, @SpringBootTest and @MockMvcTest)
- Jacoco
- Sonarqube

Within the project is a docker compose file for spinning up sonarCude. - docker-compose-sonarqube.yml

The test case covers from the basic Model Classes to the controllers.

Also, the test implementation has a seperate application.properties file for managing it's environment without
interference from the main application configuration.

I hope you find this piece interesting and helpful.

Happy coding.

timadeshola@gmail.com
https://github.com/timadeshola/spring-boot-unit-test-demo.git