# Building NBM
Building NBM does the following
1. clean and compile
2. Build jar
3. Build nb
4. Sign NBM
5. Install nbm in local m2 repo
```
mvn clean package install nbm:nbm  -Dkeystore=<jks location> -Dkeystorepass=<key store pass> -Dkeystorealias=<keystore alias> -DgeneratePom=true -DgroupId=<group id from pom> -DartifactId=<artifact id from pom> -Dversion=<nbm version from pom> -Dpackaging=nbm -Dfile=target/<filename.nbm>
```

#Prepare NBM
1. Create tmp directory
2. Create inside tmp directory the directory structure for group id/artifactid/version 
   ex structure will look like ./tmp/com/example/nb/plugin/myplugin/1.0
   ensure that the directory names and version match exactly as group id and artifact id and version values.
3. navigate to the directory ./tmp/group id/artifactid/version   
4. Copy NBM and generated pom file from install location (typically local m2 repository)
5. Sign NBM and pom file using GnuPG
   ```
   gpg -ab <pluginfile>-<version>.nbm
   gpg -ab <pluginfile>-<version>.pom
   ```
6. Generate MD5 and SHA1 signatures
   ```
   md5 <filename>.nbm | awk -F "= " '{print $2}' > <filename>.nbm.md5 
   md5 <filename>.pom | awk -F "= " '{print $2}' > <filename>.pom.md5 
   sha1 <filename>.nbm | awk -F "= " '{print $2}' > <filename>.nbm.sha1
   sha1 <filename>.pom | awk -F "= " '{print $2}' > <filename>.pom.sha1 
   ```
7. Package for uploading to sonatype central
   zip the contents making sure that the file paths are maintained
   ```
   cd to tmp directory
   jar cvfM <pluginfile>-<version>.zip <root folder of group id>
   ex. jar cvfM <pluginfile>-<version>.zip com

#Upload to sonatype central
1. Login to sonatype central https://central.sonatype.com/
2. Publish component
3. Fix publish errors
4. Hit publish

#Publish plugin in NetBeans plugin portal

