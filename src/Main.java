import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fileCounter = 0;

        while (true) {
            System.out.println("Введите путь к файлу:");
            String path = scanner.nextLine();
            File file = new File(path);

            if (!file.exists() || file.isDirectory()) {
                System.out.println("Указанный файл не существует или путь ведет к папке.");
                continue;
            }

            fileCounter++;
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + fileCounter);

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                int totalLines = 0;
                int googlebotCount = 0;
                int yandexbotCount = 0;

                Pattern logPattern = Pattern.compile("^(\\S+)\\s+-\\s+-\\s+\\[(.+?)\\]\\s+\"(\\S+)\\s+(\\S+)\\s+HTTP/\\S+\"\\s+(\\d{3})\\s+(\\d+)\\s+\"(.*?)\"\\s+\"(.*?)\"$");

                while ((line = reader.readLine()) != null) {
                    if (line.length() > 1024) {
                        throw new LineTooLongException("Ошибка: Строка длиннее 1024 символов в файле: " + path);
                    }
                    totalLines++;

                    Matcher matcher = logPattern.matcher(line);
                    if (matcher.find()) {
                        String userAgent = matcher.group(8);
                        String botType = extractBotType(userAgent);

                        if ("Googlebot".equals(botType)) {
                            googlebotCount++;
                        } else if ("YandexBot".equals(botType)) {
                            yandexbotCount++;
                        }
                    }
                }

                double googlebotPercentage = (double) googlebotCount / totalLines * 100;
                double yandexbotPercentage = (double) yandexbotCount / totalLines * 100;

                System.out.printf("Общее количество строк в файле: %d%n", totalLines);
                System.out.printf("Запросы от Googlebot: %.2f%%%n", googlebotPercentage);
                System.out.printf("Запросы от YandexBot: %.2f%%%n", yandexbotPercentage);
            } catch (LineTooLongException ex) {
                System.out.println(ex.getMessage());
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String extractBotType(String userAgent) {
        int startIdx = userAgent.indexOf('(');
        int endIdx = userAgent.indexOf(')');

        if (startIdx != -1 && endIdx != -1) {
            String firstBrackets = userAgent.substring(startIdx + 1, endIdx);
            String[] parts = firstBrackets.split(";");

            if (parts.length >= 2) {
                String fragment = parts[1].trim();
                int slashIdx = fragment.indexOf('/');

                if (slashIdx != -1) {
                    return fragment.substring(0, slashIdx).trim();
                }
                return fragment.trim();
            }
        }
        return "";
    }
}