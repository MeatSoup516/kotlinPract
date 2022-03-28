// В программе, сделанной в задании No1, вынесите проверяемое условие в
//отдельную single-expression функцию.
fun bol(len:Int):Boolean = len>0 // тру если больш 0
fun summ(s:String):Int{
    var len = s.length-2
    var res=0
    while(bol(len)){
        res+=s[len].code-48 // Сложение результата
        len-=2 // Переход на другое число
    }
    return res // Вывод
}
fun main() {
    val s = readLine()
    if(s!=null){ // Проверка на пустую строку
        println(sumtail(s))
    }
    else  println("вы ничего не ввели")
}