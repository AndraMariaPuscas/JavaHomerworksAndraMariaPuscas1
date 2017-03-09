package filesmanagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/*Write a class named FilesManagement having four methods: 

    First one to write a string into a file specified as parameter. 
    Second one to read into a string, if exists, the whole content of a specified file as parameter.
    The third one to add a string content into an empty file specified as parameter 
    The final one to delete a content of a specified file as parameter. */


public class FilesManagement {
    
    String file;

//constructor with parameters for the class
    FilesManagement(String file) {
        this.file = file;
    }

//method that writes in a file
    void writeInFile(String file) {
        try {
            FileWriter ourFile = new FileWriter(file);
            try (BufferedWriter out = new BufferedWriter(ourFile)) {
                out.write("aaa");
                out.newLine();
                out.append("bbb");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//method that deletes the content of a file

    void deleteFromFile(String file) {
        try {
            BufferedWriter del = new BufferedWriter(new FileWriter(file));
            del.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String filePath = "/home/andra/Desktop/file1.txt";
        String filePath1 = "/home/andra/Desktop/file.txt";
        
        FilesManagement newFile = new FilesManagement(filePath1);
//calling the method for writing in a file
        newFile.writeInFile(filePath1);

// calling the method for deleting the contents
        newFile.deleteFromFile(filePath1);

//reading the content of a file
        try {
            FilesManagementMethods read = new FilesManagementMethods(filePath);
            String finalArray[] = read.readFile(filePath);
            int i;
            for (i = 0; i < finalArray.length; i++) {
                System.out.println(finalArray[i]);
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
