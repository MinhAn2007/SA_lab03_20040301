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
                File outputFileXML = new File(System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "output.xml");
                PrintWriter out = new PrintWriter(new FileOutputStream(outputFileXML.getAbsolutePath()));
                JDepend jdepend = new JDepend(out);
                jdepend.addDirectory(selectedDirectory.getAbsolutePath());
                jdepend.analyze();
                out.close();

                File outputFileTXT = new File(System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "output.txt");
                Element rootElement = ParseXML.parseXMLFile(outputFileXML.getAbsolutePath()).getDocumentElement();
                Report.generateReport(rootElement, outputFileTXT.getAbsolutePath());

                JOptionPane.showMessageDialog(null, "Report saved to: " + outputFileTXT.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Project Directory selected");
        }
    }
}
