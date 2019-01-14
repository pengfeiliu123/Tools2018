package com.lpf.utilscreenadaptation;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by liupengfei on 2019/1/14 16:01.
 */
public class Generator {

    private static final int[] DP_LIST = {240, 320, 360, 384, 392, 400, 410, 480};
    private Set<FileItem> fileItemsSet = new HashSet<>();

    private static String[] paths = {"./app/src/main/res/values/dimens.xml"};

    private static class FileItem {
        String fileName;
        List<Item> itemList;
    }

    private static class Item {
        final String name;
        final String value;

        public Item(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {

        File file = new File("./");
        System.out.println(file.getAbsolutePath());

        Generator generator = new Generator();
        for (int i = 0; i < paths.length; i++) {
            generator.clear();
            generator.parse(paths[i]);
            for (FileItem fileItem : generator.fileItemsSet) {
                generator.generate(fileItem);
            }
        }
    }

    /**
     * parse dimens file and assign dimen list to generator object
     *
     * @param fileName
     */
    private void parse(String fileName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            document.getDocumentElement().normalize();
            NodeList dimenNodeList = document.getElementsByTagName("dimen");

            LinkedList<Item> generatedNodeList = new LinkedList<>();
            for (int i = 0; i < dimenNodeList.getLength(); i++) {
                Node item = dimenNodeList.item(i);
                Node firstChild = item.getFirstChild();
                if (firstChild == null) {
                    continue;
                }

                NamedNodeMap attributes = item.getAttributes();
                Node node = attributes.getNamedItem("name");
                String nodeValue = firstChild.getNodeValue();
                String nodeName = node.getNodeValue();

                generatedNodeList.add(new Item(nodeName, nodeValue));
            }

            FileItem fileItem = new FileItem();
            fileItem.fileName = fileName;
            fileItem.itemList = generatedNodeList;

            fileItemsSet.add(fileItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generate(FileItem item) throws IOException {
        List<FileItem> list = new ArrayList<>();
        for (int dp : DP_LIST) {
            Processor processor = new Processor(dp);
            FileItem fileItem = processor.process(item);
            list.add(fileItem);
        }
        for (FileItem fileItem : list) {
            output(fileItem);
        }
    }

    private void output(FileItem item) throws IOException {
        FileWriter fileWriter = new FileWriter(item.fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        bufferedWriter.newLine();
        bufferedWriter.write("<!-- Generated by ScreenAdaptation Module-->");
        bufferedWriter.newLine();
        bufferedWriter.write("<resources>");
        bufferedWriter.newLine();

        for (Item listItem : item.itemList) {
            bufferedWriter.write("<dimen name=\"");
            bufferedWriter.write(listItem.name);
            bufferedWriter.write("\">");
            bufferedWriter.write(listItem.value);
            bufferedWriter.write("</dimen>");
            bufferedWriter.newLine();
        }

        bufferedWriter.write("</resources>");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void clear() {
        fileItemsSet.clear();
    }

    private static class Processor {

        final int dpSize;

        public Processor(int dpSize) {
            this.dpSize = dpSize;
        }

        private FileItem process(FileItem origin) {
            List<Item> items = origin.itemList;
            ArrayList<Item> list = new ArrayList<>();
            for (Item item : items) {
                Item newItem = processItem(item);
                if (newItem != null)
                    list.add(newItem);
            }

            FileItem fileItem = new FileItem();
            fileItem.fileName = processName(origin.fileName);
            fileItem.itemList = list;

            return fileItem;
        }

        // calculate new value
        private Item processItem(Item item) {
            String value = item.value;
            boolean dp = true;
            int index = value.indexOf("dp");
            if (index == -1) {
                index = value.indexOf("sp");
                dp = false;
            }

            if (index == -1) return null;

            float v = Float.valueOf(value.substring(0, index));
            v = dpSize * v / 360;
            DecimalFormat df = new DecimalFormat("#.#");
            String formatted = df.format(v);
            return new Item(item.name, formatted + (dp ? "dp" : "sp"));
        }

        private String processName(String originName) {
            File file = new File(originName);
            File parentFile = file.getParentFile().getParentFile();
            File dir = new File(parentFile, "values-sw" + dpSize + "dp");
            dir.mkdirs();
            return new File(dir, file.getName()).getAbsolutePath();
        }
    }

}