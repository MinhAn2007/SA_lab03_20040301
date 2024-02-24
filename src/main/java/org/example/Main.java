package org.example;

import javax.swing.*;
import java.io.*;

import jdepend.xmlui.JDepend;
import org.w3c.dom.Element;

public class Main {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Project Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            try {
                PrintWriter out = new PrintWriter(new FileOutputStream("jdepend-results.xml"));
                JDepend jdepend = new JDepend(out);
                jdepend.addDirectory(selectedDirectory.getAbsolutePath());
                jdepend.analyze();
                out.close();
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
