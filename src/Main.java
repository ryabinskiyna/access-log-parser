import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fileCounter = 0;

        while (true) {
            System.out.println("Введите путь к файлу:");
            String path = scanner.nextLine();
            File file = new File(path);

            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists || isDirectory) {
                System.out.println("Указанный файл не существует или путь ведет к папке.");
                continue;
            }

            fileCounter++;
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + fileCounter);
        }
    }
}
