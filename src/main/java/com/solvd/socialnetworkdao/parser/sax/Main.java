package com.solvd.socialnetworkdao.parser.sax;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        try {
            xmlParser.parseXML("src/main/resources/users.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class XMLParser {
        public void parseXML(String filePath) throws Exception {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = factory.newSAXParser();
                saxParser.parse(new File(filePath), new SocialNetworkHandler());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
