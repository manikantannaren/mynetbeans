package org.pr;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.pr.xml.Zone;
import org.pr.xml.Zones;

public class TZMappingGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, IOException, JAXBException {

        Zones zones = new Zones();

        URL names = TZMappingGenerator.class.getResource("iso3166.tab");
        Path path = Paths.get(names.toURI());
        List<String> lines = Files.readAllLines(path);
        String pattern = "[a-zA-Z]+";
        Pattern p = Pattern.compile(pattern);
        lines = lines.subList(25, 274);
        lines.forEach(line -> addZone(line, zones, p));

        //we have the zones, now we need the time zones
        URL tzsURL = TZMappingGenerator.class.getResource("zone1970.tab");
        path = Paths.get(tzsURL.toURI());
        lines = Files.readAllLines(path);
        System.out.println(lines.size());
        pattern = "[a-zA-Z,/]+";
        Pattern tzp = Pattern.compile(pattern);
        lines = lines.subList(35, 383);
        lines.forEach(line -> addTZToZone(zones, line, tzp));
        JAXBContext context = JAXBContext.newInstance(Zones.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Path outPath = Paths.get("out.xml").normalize();
        System.out.println(outPath);
        marshaller.marshal(zones, Files.newBufferedWriter(outPath, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.WRITE));
    }

    private static void addZone(String line, Zones zones, Pattern pattern) {
//        Matcher matcher = pattern.matcher(line);
        String [] values = line.split("\\t");
        Zone zone = new Zone();
        zone.setIso31662LetterCode(values[0]);
        zone.setIso3166Name(values[1]);

//        if(matcher.find()){
//            zone.setIso31662LetterCode(matcher.group().trim());
//        }
//        if(matcher.find()){
//            zone.setIso3166Name(matcher.group().trim());
//        }
        if (zone.isOk()) {
            zones.addZone(zone);
        }
    }

    private static void addTZToZone(Zones zones, String line, Pattern tzp) {
        Matcher matcher = tzp.matcher(line);
        List<Zone> zonesToSet = new ArrayList<>();

        if (matcher.find()) {
            String codeString = matcher.group().trim();
            String[] codes = codeString.split(",");
            for (String code : codes) {
                zonesToSet.add(zones.getZone(code));
            }
        }
        if (matcher.find()) {
            String timeZoneId = matcher.group();
            zonesToSet.forEach((zone) -> {
                zone.setTimeZoneId(timeZoneId);
            });
        }

    }
}
