//В строке указано несколько неотрицательных целых чисел, разделенных пробелами (по одному пробелу между числами).
// В каком количестве чисел присутствуют все цифры от 0 до 9?
fun main() {
    val s="1242 12 456 1234567890 1234567098"
    val f1=s.split(" ").map{it.toInt()}.mapNotNull{ it -> generateSequence(it) {it/10}.takeWhile { it != 0 }.map { it % 10}.toSet().count().takeIf { it>9 }}.count()
    println(f1)
}