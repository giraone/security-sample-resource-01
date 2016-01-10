# Sample project for protected JEE WebSphere Liberty Application #

This project is indented to show how a simple JEE application can be protected using
- SSL
- User authentication (login/password) based on BASIC authentication
- Authorization based on roles for JAX-RS services
- Using simple groups for authorization

## Prerequisites to develop locally and run the project ##
- Maven 3
- An up-to-date WebSphere Liberty Server
- Any suitable IDE, e.g. Eclipse

## Run the project locally ##

- Create a new liberty server named "security01"
```
cd <PATH-TO-WLP>
bin/server create security01
```

- Copy ```server.xml```
```
cp <PATH-TO-GITHUB-PROJECT>/.wlp/server.xml usr/servers/security01
```

- Create a key store with "default" entry using ```securityUtility``` for your host (replace CN)
```
bin/securityUtility createSSLCertificate --server=defaultServer --password=changeit --subject=CN=yourhost,O=yourorg,C=DE
```

- Alternative: Create a key store with "default" entry using ```keytool```
```
cd usr/servers/security01
mkdir -p resources/security
cd resources/security
keytool -genkey -alias default -keystore key.jks
```

- Build the war file 
```
mvn package
```

- Deploy the war file to the new Liberty server
```
cp target/ResourceServer.war <PATH-TO-WLP>/usr/servers/security01/dropins
```

- Start the server: ```bin/server create security01```

- Open HTTP URL in browser: ```http://<your-machine>:8080/ResourceServer```

- Open HTTPS URL in browser: ```https://<your-machine>:8443/ResourceServer```

## Run the project on Bluemix ##
- t.b.d.

## Open issues and TODOs ##
 
- Currently none
