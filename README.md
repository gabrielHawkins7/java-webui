# Java WebUI

## WebUI wrapper for Java

---

#### JAVA ON THE DESKTOP IS NOT DEAD

Check it out - [Github Java-Webui](https://github.com/gabrielHawkins7/java-webui)

This project serves as a learning experience to learn the in's and out's of calling native libraries from Java. Right now its using JNA, however, I am planning to switch it over to the new Foreign Function Interface soon.

### Current Status

- Bindings written for all functions
- Java friendly abstraction
- Simple demo showcasing bridge between js->java java<-js working
- Linux & Windows support confirmed
- Integrated web-server with Javalin

### Current Goals

- [ ] Improve API Abstraction
- [ ] Fix WebView support in Linux (WebUI doesn't currently support Linux WebView with shared library and JNA only supports shared libraries)
- [ ] Create a deployable library

| Platform | WebView | Browser View |
| -------- | ------- | ------------ |
| Windows  | ✅      | ✅           |
| Linux    | ✅      | ✅           |
| OSX      | ⚠️      | ⚠️           |
