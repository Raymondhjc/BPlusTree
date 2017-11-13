package bplustree;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import javafx.util.Pair;

public class BPlusTreeTest {

    public static void main(String[] args) {
        operateFile("bplustree/input_file.txt");

    }
    // public static void printResult(ArrayList<Pair<Double, String>> res) {
	// 	for (Pair<Double, String> p : res) {
	// 		System.out.println("("+p.getKey()+","+p.getValue()+")");
	// 	}
	// }

    public static void printResult(BufferedWriter w, ArrayList<Pair<Double, String>> res, String type) {
        try {
            if (0 == res.size()) {
                w.write("Null");
                return;
            }
            if ("single" == type) {
                w.write(res.get(0).getValue());
            } else {
                w.write("(" + res.get(0).getKey() + "," + res.get(0).getValue() + ")");
            }
            for (int i = 1; i < res.size(); i++) {
                if ("single" == type) {
                    w.write("," + res.get(i).getValue());
                } else {
                    w.write(",(" + res.get(i).getKey() + "," + res.get(i).getValue() + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* visualize the whole tree in console (not called) */
    public static void visualize(TreeNode node) {
        if (node.getType() == "leaf") {
            for (int i = 0; i < ((LeafNode) node).getPairs().size(); i++) {
                System.out.print("(" + ((LeafNode) node).getPairs().get(i).getKey() + ")");
            }
            System.out.print("|");
            return;
        } else {
            for (int i = 0; i <= ((IndexNode) node).getNumber(); i++) {
                if (i < ((IndexNode) node).getNumber()) {
                    System.out.print("<" + ((IndexNode) node).getKeys().get(i) + ">");
                }
            }
            System.out.println("$");
            for (int i = 0; i <= ((IndexNode) node).getNumber(); i++) {
                visualize(((IndexNode) node).getAs().get(i));
            }
        }
    }

    /* read and write the files */
    public static void operateFile(String fileName) {
        File inFile = new File(fileName);
        File outFile = new File("bplustree/output_file.txt");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inFile));
            writer = new BufferedWriter(new FileWriter(outFile));
            final int inputOrder = Integer.parseInt(reader.readLine());
            BPlusTree tree = new BPlusTree(inputOrder);
            String input;
            String[] in;
            while ((input = reader.readLine()) != null) {
                in = input.split("\\(|,|\\)");
                switch (in[0]) {
                case "Insert":
                    tree.Insert(Double.parseDouble(in[1]), in[2]);
                    break;
                case "Search":
                    if (in.length == 2) {
                        printResult(writer, tree.Search(Double.parseDouble(in[1])), "single");
                    } else {
                        if(Double.parseDouble(in[1]) == -3.91)
                         visualize(tree.getRoot());
                        printResult(writer, tree.Search(Double.parseDouble(in[1]), Double.parseDouble(in[2])), "range");
                    }
                    writer.newLine();
                    break;
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}