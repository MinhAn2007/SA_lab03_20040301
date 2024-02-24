package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Report {

    public static void generateReport(Element root, String txtFilePath) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(txtFilePath))) {
            NodeList packagesNodeList = root.getElementsByTagName("Package");

            for (int i = 0; i < packagesNodeList.getLength(); i++) {
                Element packageElement = (Element) packagesNodeList.item(i);
                String packageName = packageElement.getAttribute("name");

                out.println("Package: " + packageName);
                NodeList errorNodes = packageElement.getElementsByTagName("error");
                NodeList statsNodes = packageElement.getElementsByTagName("Stats");

                if (errorNodes.getLength() > 0) {
                    Element errorElement = (Element) errorNodes.item(0);
                    String errorMessage = errorElement.getTextContent();
                    out.println("\tError: " + errorMessage);
                } else if (statsNodes.getLength() > 0) {
                    Element statsElement = (Element) statsNodes.item(0);
                    appendStatsToReport(statsElement, out);
                } else {
                    out.println("\tNo statistics available for this package.");
                }

                NodeList classNodes = packageElement.getElementsByTagName("Class");
                for (int j = 0; j < classNodes.getLength(); j++) {
                    Element classElement = (Element) classNodes.item(j);
                    String classSourceFile = classElement.getAttribute("sourceFile");
                    out.println("\t- Class Source File: " + classSourceFile);
                }
                out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendStatsToReport(Element statsElement, PrintWriter reportWriter) {
        String totalClasses = getElementValue(statsElement, "TotalClasses");
        String concreteClasses = getElementValue(statsElement, "ConcreteClasses");
        String abstractClasses = getElementValue(statsElement, "AbstractClasses");
        String ca = getElementValue(statsElement, "Ca");
        String ce = getElementValue(statsElement, "Ce");
        String a = getElementValue(statsElement, "A");
        String i = getElementValue(statsElement, "I");
        String d = getElementValue(statsElement, "D");
        String v = getElementValue(statsElement, "V");

        reportWriter.println("\tStats:");
        reportWriter.println("\t\tTotal Classes: " + totalClasses);
        reportWriter.println("\t\tConcrete Classes: " + concreteClasses);
        reportWriter.println("\t\tAbstract Classes: " + abstractClasses);
        reportWriter.println("\t\tCa: " + ca);
        reportWriter.println("\t\tCe: " + ce);
        reportWriter.println("\t\tA: " + a);
        reportWriter.println("\t\tI: " + i);
        reportWriter.println("\t\tD: " + d);
        reportWriter.println("\t\tV: " + v);
    }

    private static String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "**";
    }

}
