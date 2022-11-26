# Task:

## Практическое задание #1.

Нужно реализовать quicksort. От Вас требуется написать последовательную версию алгоритма (seq) и параллельную
версию (par). Взять случайный массив из 10^8 элементов и отсортировать. (Усреднить по 5 запускам) Сравнить время
работы par на 4 процессах и seq на одном процессе - у Вас должно быть раза в 3 быстрее.  (Если будет медленнее, то
выставление баллов оставляется на моё усмотрение.)


# Pre

Если появится желание запустить алгоритм, то лучше отключить сортировку рандомного массива, потому что он очень долго 
генерится

# Result

## ==== Quick sort Seq ====

### On generated incremental:

    All results: [9599, 9575, 9597, 9551, 9577]

#### ***Average: 9579.8***

### On generated randomly:

    All results: [9525, 9468, 9689, 9587, 9602]

#### ***Average: 9574.2***

## ==== Quick sort Par ForkJoinPool ====

### On generated incremental:

    All results: [1546, 1561, 1492, 1413, 1500]

#### ***Average: 1502.4***

### On generated randomly:

    All results: [1345, 1503, 1834, 1473, 1597]

#### ***Average: 1550.4***

