//Найдите второй символ в первом максимально длинном слове с четным числом символов в строке (в строке указываются только слова,
// разделенные одним или несколькими пробелами).
fun main() {
    val s= readLine().toString()
    val f= s.replace("\\s+".toRegex(), " ").split(" ").filter {it.length % 2 == 0}.maxByOrNull { it.length}?.getOrNull(1)
    println(f)
}