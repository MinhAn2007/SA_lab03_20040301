package org.example;

import javax.swing.*;
import java.io.*;

import jdepend.xmlui.JDepend;
import org.w3c.dom.Element;

public class Main {

    public static void main(String[] args) {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Project Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected directory
            File selectedDirectory = fileChooser.getSelectedFile();

            try {
                // Create a PrintWriter to write the output to an XML file
                PrintWriter out = new PrintWriter(new FileOutputStream("jdepend-results.xml"));

                // Create a JDepend instance with XMLUI mode
                JDepend jdepend = new JDepend(out);

                // Add the selected directory for analysis
                jdepend.addDirectory(selectedDirectory.getAbsolutePath());

                // Analyze the project
                jdepend.analyze();

                // Close the PrintWriter
                out.close();

                // Convert XML to TXT
                Element rootElement = ParseXML.parseXMLFile("jdepend-results.xml").getDocumentElement();
                Report.generateReport(rootElement, "jdepend.txt");

                System.out.println("JDepend analysis completed. Results written to jdepend-results.xml and jdepend.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Project Directory selected");
        }
    }
}
