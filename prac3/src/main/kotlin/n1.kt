//сумма цифр, стоящих на четных позициях в числе (если нумеровать
//цифры с конца): для числа 1234 ответ 4
fun main() {
    val num="12345678"
    val f = num.reversed().map { c ->  c.code - 48 }.filterIndexed { index, _ ->index % 2 != 0 }.sum()
    println(f)

}