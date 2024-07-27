import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int firstNumber = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int secondNumber = scanner.nextInt();

        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int product = firstNumber * secondNumber;

        double quotient;
        if (secondNumber != 0) {
            quotient = (double) firstNumber / secondNumber;
        } else {
            System.out.println("Ошибка: Деление на ноль невозможно.");
            quotient = Double.NaN;
        }

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + difference);
        System.out.println("Произведение: " + product);
        if (!Double.isNaN(quotient)) {
            System.out.println("Частное: " + quotient);
        }

        scanner.close();
    }
}