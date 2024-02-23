package org.example;

import java.io.*;

import jdepend.xmlui.JDepend;

public class Main {

    public static void main(String[] args) {
        try {
            // Create a PrintWriter to write the output to an XML file
            PrintWriter out = new PrintWriter(new FileOutputStream("jdepend-results.xml"));

            // Create a JDepend instance with XMLUI mode
            JDepend jdepend = new JDepend(out);

            // Add the directory of the project you want to analyze
            // Change the directory path as per your project
            jdepend.addDirectory("D:\\An\\Y4S2\\KienTruc\\SA_lab03_2004031");

            // Analyze the project
            jdepend.analyze();

            // Close the PrintWriter
            out.close();

            System.out.println("JDepend analysis completed. Results written to jdepend-results.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
