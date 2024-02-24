package org.example;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseXML {

    public static Document parseXMLFile(String xmlFilePath) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file to create a Document object
            Document document = builder.parse(new File(xmlFilePath));

            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
