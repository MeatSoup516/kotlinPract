//Создайте функцию, которая по данному массиву значений типа T воз-
//вращает функцию, которая при каждом вызове последовательно воз-
//вращает элементы массива, а когда элементы кончатся – null. Здесь  T – любой тип.

fun<T> return_arrel(arr:Array<T>): () -> T? { // Пустое значение может быть 0
    var n=0
    return { if (n<=arr.lastIndex) arr[n++] else null }
}
fun main() { // Функция main
    val arr = arrayOf(1,"cnhj"," ") // Массив значений
    val count= return_arrel(arr) // Для вывода
    println(count()) // Вывод
    println(count())
    println(count())
    println(count())
    println(count())
    println(count())
}