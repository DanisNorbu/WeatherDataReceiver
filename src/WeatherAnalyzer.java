import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAnalyzer {

    // Метод для извлечения текущей температуры из JSON-ответа
    public static int getCurrentTemperature(String jsonResponse) throws Exception {
        // Создаем объект ObjectMapper для работы с JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // Парсим JSON-строку в объект JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        // Получаем "fact" и извлекаем температуру
        JsonNode factNode = jsonNode.get("fact");
        return factNode.get("temp").asInt();
    }

    // Метод для вычисления средней температуры за определенный период
    public static double getAverageTemperature(String jsonResponse, int limit) throws Exception {
        // Создаем объект ObjectMapper для работы с JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // Парсим JSON-строку в объект JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        // Получаем "forecasts"
        JsonNode forecastsNode = jsonNode.get("forecasts");

        int sum = 0;
        int count = 0;

        // Перебираем прогнозы погоды
        for (JsonNode forecastNode : forecastsNode) {
            if (count >= limit) break;

            // Получаем "parts" и "day_short"
            JsonNode partsNode = forecastNode.get("parts");
            JsonNode dayShortNode = partsNode.get("day_short");

            // Извлекаем температуру и добавляем ее к сумме
            int temp = dayShortNode.get("temp").asInt();
            sum += temp;
            count++;
        }

        // Возвращаем среднюю температуру
        return count > 0 ? (double) sum / count : 0;
    }
}