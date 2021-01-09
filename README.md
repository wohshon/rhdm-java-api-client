## Drools Client with Kie Server Java API 

#### scaffolding codes to communicate with a Red Hat Decision Server over SSL

(WIP : RHDM is deployed using Operator, documentation on the way ..... )

#### For test env, extract the generated keystore from the pod and create a truststore

keystore password is in deploymentconfig parameters

`oc rsync rhpam-production-immutable-kieserver-1-c8tgc:/etc/kieserver-secret-volume .`

`keytool -exportcert -alias jboss -keypass password -keystore keystore1.jks -rfc -file mycert.pem`

`keytool -import -alias mytrust -file mycert.pem -keystore truststore.jks`
