public class Main {
    public static void main(String[] args) {
        // Пример создания и использования объектов
        String logLine = "37.231.123.209 - - [25/Sep/2022:06:25:04 +0300] \"GET /engine.php?rss=1&json=1&p=156&lg=1 HTTP/1.0\" 200 61096 \"https://nova-news.ru/search/?rss=1&lg=1\" \"Mozilla/5.0 (Linux; Android 6.0.1; SM-J500M Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36\"";

        LogEntry entry = new LogEntry(logLine);
        Statistics stats = new Statistics();
        stats.addEntry(entry);

        System.out.printf("Средний объём трафика за час: %.2f байт%n", stats.getTrafficRate());
    }
}