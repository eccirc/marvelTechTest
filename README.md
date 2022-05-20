# Marvel Characters API

---

## About

This project leverages the official Marvel API to get information about Marvel characters.
Via different API endpoint, one can access:
- A list of 1500 Marvel character Id's:  **/characters**
- Basic information about a single Marvel character (name, description, thumbnail image link)
  **/characters/{id}**
- The same as above but with the description translated to a language of your choice
(specified in the URL path):   **/characters/{id}/{lang}**
 *See [here](https://www.labnol.org/code/19899-google-translate-languages) for a list of language codes*

## Technologies

**Java**, **Spring Boot**, **Googgle Cloud API's (Translate)**

---

### To Run / use

- Clone down this repo and build with Maven
- Package with Maven (e.g in IntelliJ navigate to Maven / Execute Maven Goal / mvn package)
- In your terminal of choice, navigate to the build directory  - ``cd /marvelTechTest/target``
- Run ``java -jar demo-0.0.1-SNAPSHOT.jar`` (Ensuring you have Java 11+ SDK's installed on your machine first)
- Try the test URL to confirm that the API is working: ``curl http://localhost:8080/test``
- *Optionally* import MarvelApiRef.yml into a programme like Postman to try all of the available endpoints from there.

---

## Credits / Attributions

**Marvel API**
- Marvel character info from the official [Marvel API](https://developer.marvel.com/) ( © 2014 Marvel)

**Google**
- Google Cloud [Translation AI](https://cloud.google.com/translate)

---

**Author** David Williams

**Date** May 2022

**Last Updated** May 2022



