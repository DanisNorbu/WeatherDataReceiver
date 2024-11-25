import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ErrorProcessor {

    // Метод для обработки ошибок от сервера
    public static void handleError(HttpURLConnection connection) {
        try {
            // Чтение ошибки от сервера
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String errorLine;
            StringBuilder errorResponse = new StringBuilder();

            while ((errorLine = errorReader.readLine()) != null) {
                errorResponse.append(errorLine);
            }
            errorReader.close();

            // Выводим ошибку от сервера
            System.out.println("Ошибка от сервера:");
            System.out.println(errorResponse.toString());
        } catch (Exception e) {
            // Выводим ошибку, если что-то пошло не так
            e.printStackTrace();
        }
    }
}