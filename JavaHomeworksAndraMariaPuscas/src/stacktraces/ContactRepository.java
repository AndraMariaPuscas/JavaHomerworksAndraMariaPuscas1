/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacktraces;

/**
 *
 * @author andra
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ContactRepository {

    static class ContactRepository1 {

        String file;

        ContactRepository1(String file) {
            this.file = file;
        }

        void saveContact(String name, String email) {
            String row = name + "," + email + "\n";
            writeRow(row);
        }

        void writeRow(String row) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
                out.write(row);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ContactRepository1 repo = new ContactRepository1("/home/andra/Desktop/file.txt");
        repo.saveContact("Adrian", "123");
}


}
