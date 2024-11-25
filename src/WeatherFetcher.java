import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {

    private static final String API_URL = "https://api.weather.yandex.ru/v2/forecast";
    private final String apiKey;

    // Конструктор класса, принимающий API ключ
    public WeatherFetcher(String apiKey) {
        this.apiKey = apiKey;
    }

    // Метод для получения данных о погоде
    public String fetchWeather(double lat, double lon) throws Exception {
        // Формируем URL запроса с использованием введенных координат
        String urlString = API_URL + "?lat=" + lat + "&lon=" + lon;
        URL url = new URL(urlString);

        // Открываем соединение с сервером
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Yandex-Weather-Key", apiKey);

        // Получаем код ответа от сервера
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Чтение ответа от сервера
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Возвращаем ответ в виде строки
            return response.toString();
        } else {
            // Обрабатываем ошибку от сервера
            ErrorProcessor.handleError(connection);
            return null;
        }
    }
}