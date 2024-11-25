import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Создаем объект Scanner для чтения ввода пользователя
            Scanner scanner = new Scanner(System.in);

            // Ввод координат
            System.out.print("Введите широту (lat): ");
            double lat = scanner.nextDouble();
            System.out.print("Введите долготу (lon): ");
            double lon = scanner.nextDouble();

            // Ввод периода для вычисления средней температуры
            System.out.print("Введите период для вычисления средней температуры (количество дней): ");
            int limit = scanner.nextInt();

            // Получение API ключа
            String apiKey = "ключ_API"; // Впиши свой API ключ здесь

            // Создаем объект WeatherFetcher и передаем ему API ключ
            WeatherFetcher weatherFetcher = new WeatherFetcher(apiKey);

            // Получаем данные о погоде
            String jsonResponse = weatherFetcher.fetchWeather(lat, lon);

            // Выводим полный ответ от сервиса
            System.out.println("Полный ответ от сервиса:");
            System.out.println(jsonResponse);

            // Парсим JSON и выводим текущую температуру
            int temp = WeatherAnalyzer.getCurrentTemperature(jsonResponse);
            System.out.println("Текущая температура: " + temp + "°C");

            // Вычисляем среднюю температуру за определенный период
            double averageTemp = WeatherAnalyzer.getAverageTemperature(jsonResponse, limit);
            System.out.println("Средняя температура за " + limit + " дней: " + averageTemp + "°C");

        } catch (Exception e) {
            // Выводим ошибку, если что-то пошло не так
            e.printStackTrace();
        }
    }
}