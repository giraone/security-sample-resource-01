# Sample project for protected JEE WebSphere Liberty Application #

This project is indented to show how a simple JEE application can be protected in **WebSphere Liberty** using
- SSL (server authentication)
- User authentication based on BASIC authentication (login/password) 
- User authentication based on SSL client certificates
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

- Copy `server.xml`
```
cp <PATH-TO-GITHUB-PROJECT>/.wlp/server.xml usr/servers/security01
```

- Create a key store with a self-sign certificate named "default" using `securityUtility` for your host (replace CN below!)
```
bin/securityUtility createSSLCertificate --server=security01 --password=changeit --subject=CN=your-machine,O=yourorg,C=DE
```
  In the success message you will see that you have to add the following XML code to `server.xml`
```
    <featureManager>
        <feature>ssl-1.0</feature>
    </featureManager>
    <keyStore id="defaultKeyStore" password="{xor}PDc+MTg6Nis=" />

```
  If you use the above password for the key store, then you do not have to change `server.xml`!

- Alternative 2: You can convert a PKCS12 file an store it in the JKS store:
```
cd usr/servers/security01
mkdir -p resources/security
cd resources/security
keytool -v -importkeystore -srckeystore <FILE>.p12 -srcstoretype PKCS12 -destkeystore key.jks -deststoretype JKS -srcalias <SRC-ALIAS> -destalias default
```
   
- Alternative 3: Due to the fact, that liberty is able to read PKCS12 files, you can specify a regular PKCS12 server key pair by changing `server.xml`.
```
	<keyStore id="defaultKeyStore" location="${server.config.dir}/resources/security/<filename>.p12" password="your-pass" type="PKCS12"/>
	<ssl clientAuthentication="true" clientAuthenticationSupported="true"
		id="mySSLSettings" keyStoreRef="defaultKeyStore" securityLevel="HIGH" serverKeyAlias="<name-of-key>"
		sslProtocol="TLS" trustStoreRef="defaultTrustStore"/>
```

- If you want to use SSL client authentication, you must populate the trust store, with a trusted root CA.
```
keytool -import -trustcacerts -keystore trust.jks -alias "default" -file CA.crt
```
 
- Build the war file 
```
mvn package
```

- Deploy the war file to the new Liberty server
```
cp target/ResourceServer.war <PATH-TO-WLP>/usr/servers/security01/dropins
```

- Start the server: ```bin/server start security01```

- Open HTTP URL in browser: ```http://<your-machine>:8080/ResourceServer```

- Open HTTPS URL in browser: ```https://<your-machine>:8443/ResourceServer```

## Run the project on Bluemix ##
- t.b.d.

## Open issues and TODOs ##
 
- Currently none
