import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Загальний клас BasicDataOperation координує роботу різних структур даних.
 *
 * <p>Цей клас служить центральною точкою для демонстрації операцій з різними
 * колекціями Java: List, Queue та Set. Він об'єднує функціональність всіх
 * спеціалізованих класів для комплексного аналізу даних LocalDate.</p>
 */
public class BasicDataOperation {
    static final String PATH_TO_DATA_FILE = "list/LocalDate.data";

    LocalDate localDateValueToSearch;
    LocalDate[] dateArray;

    private static final String SEPARATOR = "\n" + "=".repeat(80) + "\n";
    private static final String USAGE_MESSAGE =
            "Використання: java BasicDataOperation <дата>\n" +
            "Приклад:\n" +
            "  java BasicDataOperation \"2025-03-13\"";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(USAGE_MESSAGE);
            return;
        }

        String searchValue = args[0];

        // Перевірка формату (тільки LocalDate)
        try {
            LocalDate.parse(searchValue, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("Помилка: Невірний формат дати. Використовуйте формат yyyy-MM-dd (наприклад: 2025-03-13)");
            return;
        }

        BasicDataOperation coordinator = new BasicDataOperation();
        coordinator.executeOperations(args);
    }

    /**
     * Координує виконання операцій залежно від обраного типу.
     */
    private void executeOperations(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("🚀 РОЗПОЧАТО АНАЛІЗ ДАНИХ LocalDate 🚀");
        System.out.println("Пошуковий параметр: " + args[0]);
        System.out.println(SEPARATOR);

        // Конвертація в LocalDate
        localDateValueToSearch = LocalDate.parse(args[0], DateTimeFormatter.ISO_LOCAL_DATE);

        // Завантаження масиву з файлу
        dateArray = DataFileHandler.loadArrayFromFile(PATH_TO_DATA_FILE);

        runAllOperations();

        System.out.println(SEPARATOR);
        System.out.println("✅ АНАЛІЗ ЗАВЕРШЕНО ✅");
        System.out.println(SEPARATOR);
    }

    /** Запускає операції з колекцією List. */
    private void runListOperations() {
        System.out.println("📋 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ LIST");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingList listProcessor =
                    new BasicDataOperationUsingList(localDateValueToSearch, dateArray);
            listProcessor.executeDataOperations();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з List: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Запускає операції з Queue. */
    private void runQueueOperations() {
        System.out.println("🔄 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ QUEUE");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingQueue queueProcessor =
                    new BasicDataOperationUsingQueue(localDateValueToSearch, dateArray);
            queueProcessor.runDataProcessing();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з Queue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Запускає операції з Set. */
    private void runSetOperations() {
        System.out.println("🔍 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ SET");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingSet setProcessor =
                    new BasicDataOperationUsingSet(localDateValueToSearch, dateArray);
            setProcessor.executeDataAnalysis();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з Set: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Запускає всі колекції по черзі. */
    private void runAllOperations() {
        System.out.println("🎯 КОМПЛЕКСНИЙ АНАЛІЗ ВСІХ СТРУКТУР ДАНИХ");
        System.out.println("=".repeat(60));

        runListOperations();
        System.out.println("\n" + "~".repeat(60) + "\n");

        runQueueOperations();
        System.out.println("\n" + "~".repeat(60) + "\n");

        runSetOperations();
    }
}
