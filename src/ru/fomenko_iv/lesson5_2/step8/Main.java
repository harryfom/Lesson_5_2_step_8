package ru.fomenko_iv.lesson5_2.step8;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.

 Контрольная сумма данных вычисляется по следующему алгоритму:

 Контрольная сумма представляет собой число типа int. Контрольная сумма пустого набора данных равна нулю.
 Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле:
 Cn+1=rotateLeft(Cn) xor bn+1, где Cn — контрольная сумма первых n байт данных,
 rotateLeft — циклический сдвиг бит числа на один бит влево (чтобы не изобретать велосипед,
 используйте Integer.rotateLeft), bnbn — n-ный байт данных.

 Поскольку метод не открывал данный InputStream, то и закрывать его он не должен. Выброшенное из методов
 InputStream исключение должно выбрасываться из метода.

 Пример
 На вход подан InputStream, последовательно возвращающий три байта: 0x33 0x45 0x01. В качестве контрольной
 суммы должно быть возвращено число 71.

 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("5.2 Потоки байт – Шаг 8");
        byte[] test = new byte[]{0x33, 0x45, 0x01};
        int checkSum;
        try (ByteArrayInputStream is = new ByteArrayInputStream(test)){
            checkSum = checkSumOfStream(is);
        }
        System.out.println(checkSum);

    }
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int retVal=0;
        int byteFromStream;
        while ((byteFromStream = inputStream.read()) != -1){
            retVal = Integer.rotateLeft(retVal,1) ^ byteFromStream;
        }
        return retVal;
    }
}
