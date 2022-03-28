//Побитовое исключающее ИЛИ второй цифры всех чисел
fun main() {
    val s=readLine()
    if(!s.isNullOrEmpty()){
        val f= s.split(" ").filter{it.length>=2}.map{ it[11].code-48 }.reduce{ total, next-> next xor total }
        println(f)
    }
    else
        println("вы ничего не ввели")
    println((-3)xor -1)
}