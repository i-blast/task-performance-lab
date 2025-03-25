# Задание НТ Performance Lab

---

# Задание 1

**Круговой массив** - массив из элементов, в котором по достижению конца массива следующим элементом будет снова первый. Массив задается числом `n`, то есть представляет собой числа от `1` до `n`.

Напишите программу, которая выводит путь, по которому, двигаясь интервалом длины `m` по заданному массиву, концом будет являться первый элемент.

**Началом одного интервала является конец предыдущего.**

Путь - массив из начальных элементов полученных интервалов.

### Пример 1
`n = 4`, `m = 3`

**Решение:**  
Круговой массив: `1234`.  
При длине обхода `3` получаем интервалы: `123`, `341`. Полученный путь: `13`.

### Пример 2
`n = 5`, `m = 4`

**Решение:**  
Круговой массив: `12345`.  
При длине обхода `4` получаем интервалы: `1234`, `4512`, `2345`, `5123`, `3451`.  
Полученный путь: `14253`.

**Параметры передаются в качестве аргументов командной строки!**

Например, для последнего примера на вход подаются аргументы: `5 4`, ожидаемый вывод в консоль: `14253`.

# Задание 2

Напишите программу, которая рассчитывает положение точки относительно окружности.

- Координаты центра окружности и его радиус считываются из файла 1
- Координаты точек считываются из файла 2

### Пример:
**Файл 1:**
<br>
1 1<br>
5

**Файл 2:**
<br>
0 0<br>
1 6<br>
6 6

**Вывод для данных примеров файлов:**
<br>
1<br>
0<br>
2

**Пути к файлам передаются программе в качестве аргументов**

- Файл с координатами и радиусом окружности - 1 аргумент;
- Файл с координатами точек - 2 аргумент;
- Координаты - рациональные числа в диапазоне от `10^-38` до `10^38`;
- Количество точек от 1 до 100;
- Вывод каждого положения точки заканчивается символом новой строки;
- Соответствия ответов:
    - `0` - точка лежит на окружности;
    - `1` - точка внутри;
    - `2` - точка снаружи.

Вывод программы в консоль.  
