import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Объявление переменной для полученной от юзера строки
        String toCalc;

        Scanner in = new Scanner(System.in);
        // Создание сообщения для юзера в консоли
        System.out.print("Привет! Введите два числа, и я их посчитаю: ");
        // Инициализация переменной строкой, полученной от юзера
        toCalc = in.nextLine();
        // Вызов функции calc с получением результата вычисления
        String result = calc(toCalc);
        // Вывод результата вычислений функции calc
        System.out.println("Ответ: "+result);
    }

    public static String calc(String input) throws Exception {
        // Предполагается, что строка юзера будет иметь пробелы, поэтому сплит строки в массив по наличию пробелов
        String [] splitInput = input.split(" ");

        // Валидация формата математической операции
        if(splitInput.length > 3) {
            throw new Exception("\nФормат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // Объявление и инициализация переменных из разделённых на строке 21 данных
        String a = splitInput[0];
        String b = splitInput[2];
        String operation = splitInput[1];

        // Создание булиевой переменной, которая будет равна true, если числа римские
        boolean isRome = false;
        // Объявление переменных для арабских чисел
        int aInt, bInt;

        // Массивы с цифрами
        String [] integers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String [] rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        // Валидация полученных данных:
        // Если a и b являются целыми числами
        if(Arrays.asList(integers).contains(a) && Arrays.asList(integers).contains(b)) {
        // Конвертация их в целые числа
            aInt = Integer.parseInt(a);
            bInt = Integer.parseInt(b);
        // Если a и b не являются целыми числами:
        }else {
            // Проверка, если a и b являются римскими числами
            if(Arrays.asList(rome).contains(a) && Arrays.asList(rome).contains(b)) {
                isRome = true;
                // Перевод римского числа в целое посредством нахождения индекса в массиве
                // Добавление + 1 к индексу, чтобы число соответствовало указанному
                aInt = Arrays.asList(rome).indexOf(a) + 1;
                bInt = Arrays.asList(rome).indexOf(b) + 1;
            } else {
                throw new Exception("\nОба введёных числа должны:\n 1. Относиться либо к арабским, либо к римским системам \n 2. Быть меньше 10\n 3. Быть целыми");
            }


        }

        // Объявление переменной для хранения результата
        int result;

        // Выполнение операций вычисления
        if(operation.contains("+")) {
            result = aInt + bInt;
        } else if(operation.contains("-")) {
            result = aInt - bInt;
        } else if(operation.contains("*")) {
            result = aInt * bInt;
        } else if(operation.contains("/")) {
            result = aInt / bInt;
        } else {
            throw new Exception("\nНекорректный оператор\nКорректные операторы: +, -, *, /");
        }

        // Объявление переменной для конечного результата
        String resultString = null;

        String [] keys  =  { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int [] vals = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder ret = new StringBuilder();
        int index = 0;

        // Если числа были римскими:
        // Валидация ответа (не меньше 1, иначе ошибка)
        if(isRome) {
            if(result < 1) {
                throw new Exception("\nРезультат вычислений меньше единицы");
            } else {
                // Перевод в римское число
                // Итерация по массиву с римскими числами
                while(index < keys.length && result != 0) {
                    // Находит значение, которое меньше или равно результату вычислений
                    while (result >= vals[index]) {
                        // Вычисляет количество повторений vals[index] в result
                        // Чтобы потом можно было добавить нужное количество римских символов
                        // В конечный результат
                        int d = result / vals[index];
                        // Вычисляет остаток, который нужно будет конвертировать в римское число при следующей итерации
                        result = result % vals[index];
                        // Добавляет d римских символов в StringBuilder
                        for(int i = 0; i < d; i++) {
                            ret.append(keys[index]);
                        }
                    }
                    // Увеличивает индекс, чтобы дальше итерировать по массиву с римскими числами
                    index++;
                }
                // Конвертация StringBuilder в строку для вывода
                resultString = ret.toString();

            }
        }


        // Если числа были арабскими:
        // Конвертация ответа в строку
        if(!isRome) {
            resultString = Integer.toString(result);
        }


        return resultString;
    }
}