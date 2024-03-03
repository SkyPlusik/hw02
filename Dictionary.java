import java.io.*;
import java.util.Scanner;

public class Dictionary {
    private FileInputStream inFile;
    private FileOutputStream outFile;
    private String in, out;
    private static int max_size = 52;
    private int cur_size = 0;
    private int[][] dictionary = new int[max_size][2];

    public Dictionary()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя входного файла: ");
        in = scanner.nextLine();
        System.out.println("Введите имя выходного файла: ");
        out = scanner.nextLine();
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
            inFile = new FileInputStream(in);
            int c;
            while ((c = inFile.read()) != -1)
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
            inFile.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Файла не существует!");
        }
        catch (IOException ex)
        {
            System.out.println("Ошибка чтения файла!" );
        }
    }
    private void write()
    {
        try
        {
            outFile = new FileOutputStream(out, false);
            for(int i = 0; i < cur_size; i++)
            {
                outFile.write(((char)dictionary[i][0] + " : " + dictionary[i][1] + "\n").getBytes());
            }
            outFile.close();
        }
        catch (IOException ex)
        {
            System.out.println("Ошибка записи в файл!" );
        }

    }
}
