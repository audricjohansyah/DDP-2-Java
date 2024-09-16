import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MergePlaylist {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        System.out.print("File playlist pertama: ");
        String fileOne = input.next();
        ArrayList<String> merged = new ArrayList<String>();
        ArrayList<String> finalMerged = new ArrayList<String>();

       // merge isi file satu
        try (BufferedReader reader1 = new BufferedReader(new FileReader(fileOne))){
            String line = reader1.readLine();
            while (line != null) {
                check(line);
                merged.add(line);
                line = reader1.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan!");
            input.close();
            System.exit(0);
            
        }  catch (InvalidPlaylistException e){
            System.err.println("Playlist tidak valid!");
            input.close();
            System.exit(0);
            
        } catch (IOException e) {
            System.err.println("File tidak ditemukan!");
            input.close();
            System.exit(0);
        } 
        
        // merge isi file 2
        System.out.print("File playlist kedua: ");
        String fileTwo = input.next();
        try(BufferedReader reader2 = new BufferedReader(new FileReader(fileTwo))){
            String line = reader2.readLine();
            while (line != null) {
                check(line);
                merged.add(line);
                line = reader2.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan!");
            input.close();
            System.exit(0);
            
        }  catch (InvalidPlaylistException e){
            System.err.println("Playlist tidak valid!");
            input.close();
            System.exit(0);
            
        } catch (IOException e) {
            System.err.println("File tidak ditemukan!");
            input.close();
            System.exit(0);
        } 

        // cetak output
        System.out.print("File playlist output: ");
        String output = input.next();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
            for (String lines : merged){
                boolean duplicate = false;
                for (String uniqueLines : finalMerged){
                    if (uniqueLines.equals(lines)){
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate){
                    finalMerged.add(lines);
                    writer.write(lines);
                    writer.newLine();
                }
            }
            System.out.println("Berhasil menimpa playlist, jumlah lagu adalah: " + finalMerged.size());

       } catch (IOException e) {
        System.err.println("File tidak ditemukan!");
        input.close();
        System.exit(0);
    }
       
    }

    //validasi playlist
    public static void check(String line) throws InvalidPlaylistException {
        String[] arrLine = line.split("\\|\\|");
        if (arrLine.length >= 3 || arrLine.length <= 1) {
            throw new InvalidPlaylistException("Playlist tidak valid");
        }
        else if (arrLine[0].substring(0, 1).equals("|") 
        || arrLine[0].substring(arrLine[0].length() - 1, arrLine[0].length()).equals("|") 
        || arrLine[1].substring(0, 1).equals("|") 
        || arrLine[1].substring(arrLine[1].length() - 1, arrLine[1].length()).equals("|")){
            throw new InvalidPlaylistException("Playlist tidak valid");
        }
    }
}