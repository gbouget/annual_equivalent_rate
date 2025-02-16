
# Intérêts simples et composés

**InterestTools** est un projet personnel non commercial qui combine :

- un **document PDF**, généré depuis du code LaTeX, pour comprendre le fonctionnement des intérêts ;
- une **application interactive** (développée en Kotlin) pour mettre en pratique et expérimenter les notions abordées dans le PDF ;
- une **API REST** pour proposer un interfaçage avec des systèmes logiciels externes ;
- un **espace web** pour consulter le PDF, interagir avec l'application et accéder à la documentation de l'API REST.

Le projet a pour but de fournir des outils éducatifs accessibles et intuitifs pour mieux comprendre et appliquer des concepts financiers et du développement logiciel.

## Technologies Utilisées
- **Backend** : Kotlin, Spring Boot, Gradle.
- **Frontend** : HTML, CSS, JavaScript.
- **PDF Generation** : LaTeX, pdflatex.
- **API Documentation** : Swagger/OpenAPI.

## Prérequis

- **Java 21** (nécessaire pour Kotlin).
- **Gradle** – ou utiliser le wrapper Gradle inclus (`./gradlew`).
- **pdflatex** (ou xelatex) – pour compiler le document LaTeX.

## Installation et exécution
Lancer la commande depuis une console :
```bash
   ./gradlew run
```
Exemple de sortie :
```bash
gbouget@DELL-XPS-15 MINGW64 ~/lab/interest-tools (main)
$ ./gradlew run

> Task :run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.2)

2025-02-16T18:21:39.248+01:00  INFO 17800 --- [interest-tools] [           main] d.g.i.InterestToolsApplicationKt         : Starting InterestToolsApplicationKt using Java 21 with PID 17800 (C:\Users\gaeta\lab\interest-tools\build\classes\kotlin\main started by gbouget in C:\Users\gaeta\lab\interest-tools)
2025-02-16T18:21:39.256+01:00  INFO 17800 --- [interest-tools] [           main] d.g.i.InterestToolsApplicationKt         : No active profile set, falling back to 1 default profile: "default"
2025-02-16T18:21:42.147+01:00  INFO 17800 --- [interest-tools] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2025-02-16T18:21:42.173+01:00  INFO 17800 --- [interest-tools] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-02-16T18:21:42.173+01:00  INFO 17800 --- [interest-tools] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.34]
2025-02-16T18:21:42.248+01:00  INFO 17800 --- [interest-tools] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-02-16T18:21:42.249+01:00  INFO 17800 --- [interest-tools] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2838 ms
2025-02-16T18:21:42.459+01:00  INFO 17800 --- [interest-tools] [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
2025-02-16T18:21:43.087+01:00  INFO 17800 --- [interest-tools] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-02-16T18:21:43.100+01:00  INFO 17800 --- [interest-tools] [           main] d.g.i.InterestToolsApplicationKt         : Started InterestToolsApplicationKt in 4.565 seconds (process running for 5.156)
2025-02-16T18:22:12.705+01:00  INFO 17800 --- [interest-tools] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2025-02-16T18:22:12.705+01:00  INFO 17800 --- [interest-tools] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2025-02-16T18:22:12.711+01:00  INFO 17800 --- [interest-tools] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 6 ms
<==========---> 83% EXECUTING [9m 20s]
> :run
```
Accéder à l'URL http://localhost:8080/.

