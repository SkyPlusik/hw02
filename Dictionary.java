import java.io.*;
import java.util.Scanner;

public class Dictionary {
    private FileInputStream input;
    private PrintWriter output;
    private String in_filepath;
    private String out_filepath;
    private static int size = 52;
    private int cur_size = 0;
    private int[][] dictionary = new int[size][2];

    public Dictionary()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the input file path: ");
        in_filepath = in.nextLine();
        System.out.println("Enter the output file path: ");
        out_filepath = in.nextLine();
        read();
        write();
    }
    private int find_letter(int c)
    {
        for(int i = 0; i < cur_size; i++)
        {
            if (dictionary[i][0] == c) return i;
        }
        return -1;
    }
    private void read()
    {
        try
        {
            input = new FileInputStream(in_filepath);
            int c;
            while ((c = input.read()) != -1)
            {
                if (c >= 'A' && c <= 'Z' || c >='a' && c <= 'z')
                {
                    int i = find_letter(c);
                    if (i != -1)
                    {
                        dictionary[i][1]++;
                    }
                    else
                    {
                        dictionary[cur_size][0] = c;
                        dictionary[cur_size][1] = 1;
                        cur_size++;
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File does not Exists");
        }
        catch (IOException ex)
        {
            System.out.println("File reading error" );
        }
    }

    private void write()
    {
        try
        {
            FileWriter fileWriter = new FileWriter(out_filepath);
            output = new PrintWriter(fileWriter);
            for(int i = 0; i < cur_size; i++)
            {
                output.print((char)dictionary[i][0] + " : " + dictionary[i][1] + "\n");
            }
            output.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File does not Exists");
        }
        catch (IOException ex)
        {
            System.out.println("File writing error" );
        }

    }
}
