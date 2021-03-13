# A Calculus of Context-Aware Systems Editor

A new generation editor for modelling Context-Aware Systems (Development Environment)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### You Need

1. To install on your local machine Java JDK 1.8 or greater.
2. To install Java Development Environment (IDE) - Eclipse IDE or IntelliJ IDEA.
3. To install Maven on your local machine, but it's not required to run the application.

### Project Set Up

1. Pull the source code from the GitHub Repository.
2. Import it to the IDE (Eclipse IDE or IntelliJ IDEA).
3. Configure your build path and where your local Java is located.
4. This is a Maven project. So, run maven clean and/or maven build if it's neccessary.
5. All project configurations are part from the repository in GitHub except the credentials for email sending functionality, because of the security reasons.
6. Ask other team member to give you the `email_config.json` file.
7. Once you have the file, you need to place it into the following folder: `src/main/resources/`
8. The email configurations file has the following format (this is example data)

```json
{
	"senderEmail":"test_sender_email@example.com",
	"senderEmailPass":"TestPassword",
	"recipientEmail":"test_recipient_email@example.com",
	"recipientBccEmail":"test_bcc_recipient_email@example.com",
	"smtpServerHost":"mail.server",
	"smtpServerTls":"true/false",
	"smtpServerAuth":"true/false",
	"smtpServerPort":"80"
}
```

## Used Technologies

* [Java 8](https://www.oracle.com/java/technologies/java8.html)
* [Java Swing/AWT](https://docs.oracle.com/javase/tutorial/uiswing/)
* [Java Mail API 1.6.2](https://javadoc.io/doc/javax.mail/javax.mail-api/latest/index.html)
* [Logback 1.2.3](http://logback.qos.ch/)
* [SLF4J 1.7.30](http://www.slf4j.org/)
* [OpenCSV 5.3](http://opencsv.sourceforge.net)
* [Jackson 2.12.2](https://en.wikipedia.org/wiki/Jackson_(API))
* [JUnit 4.13.1](https://junit.org/junit4)
* [Maven](https://maven.apache.org/)
* [Eclipse](https://www.eclipse.org/)
* [SonarLint](https://www.sonarlint.org/)
* CCA paradigm
* ccaPL language

## Resources

* [Images](https://freeicons.io)
* [Icons](https://www.flaticon.com)

## Java Doc

You can view full Java [documentation](doc/index.html) for all classes and interfaces for this application.

`./<project_folder>/doc/index.html`

You can update Java documentation from Eclipse: `Project -> Generate javadoc...`

Reference: [here](https://stackoverflow.com/questions/4468669/how-to-generate-javadoc-html-files-in-eclipse)
