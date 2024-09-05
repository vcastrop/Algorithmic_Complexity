import java.io.*;
import java.util.*;

public class SortAlgorithms {

    public static void main(String[] args) throws IOException {
        int totalPalabras = 10000; 

        // Leer palabras del archivo
        List<String> palabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("palabras.es"))) {
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null && count < totalPalabras) {
                palabras.add(linea.trim()); 
                count++;
            }
        }

        // Convertir a array para los algoritmos de ordenamiento
        String[] palabrasArray = palabras.toArray(new String[0]);

        // Ordenar usando Bubble Sort y medir el tiempo
        String[] palabrasBubbleSort = Arrays.copyOf(palabrasArray, palabrasArray.length);
        long startTime = System.nanoTime(); // Tiempo en nanosegundos
        bubbleSort(palabrasBubbleSort);
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000; // Convertir a milisegundos
        double durationSeconds = durationMs / 1000.0; // Convertir a segundos
        System.out.println("Bubble Sort tomó: " + durationMs + " ms (" + durationSeconds + " segundos)");

        // Ordenar usando Bucket Sort y medir el tiempo
        String[] palabrasBucketSort = Arrays.copyOf(palabrasArray, palabrasArray.length);
        startTime = System.nanoTime();
        bucketSort(palabrasBucketSort);
        endTime = System.nanoTime();
        durationMs = (endTime - startTime) / 1_000_000;
        durationSeconds = durationMs / 1000.0;
        System.out.println("Bucket Sort tomó: " + durationMs + " ms (" + durationSeconds + " segundos)");
    }

    // Implementación de Bubble Sort
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Implementación de Bucket Sort
    public static void bucketSort(String[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Crear buckets y distribuir las palabras
        List<List<String>> buckets = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String s : arr) {
            char firstChar = s.toLowerCase().charAt(0);
            if (firstChar >= 'a' && firstChar <= 'z') {
                int bucketIndex = firstChar - 'a';
                buckets.get(bucketIndex).add(s);
            }
        }

        // Ordenar cada bucket y concatenar los resultados
        int index = 0;
        for (List<String> bucket : buckets) {
            Collections.sort(bucket);
            for (String s : bucket) {
                arr[index++] = s;
            }
        }
    }
}
