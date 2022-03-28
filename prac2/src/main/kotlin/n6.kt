//Создайте функцию, которая по данному массиву целых чисел возвра-
//щает функцию, которая при каждом вызове последовательно возвра-
//щает элементы массива, а когда элементы кончатся – null.
fun returnarrel(arr:Array<Int>): () -> Int? { // Перевод пустого значения в 0
    var n=0
    return { if (n<=arr.lastIndex) arr[n++] else null }
}
fun main() {
    val arr = arrayOf(1,2,3,4) // Массив чисел для вывода
    val count= returnarrel(arr) // Что и откуда выводить
    println(count())
    println(count())
    println(count())
    println(count())
    println(count())
}