//Найдите второй символ в первом максимально длинном слове с чет-
//ным числом символов в строке (в строке указываются только слова,
// разделенные одним или несколькими пробелами).
fun main() {
    val s = readLine() // Читаем строку
    if(s!=null){ // Проверка на пустую строку
        var maxWordLen=0
        var maxWordChar=' '
        var count=0
        var nowLen=0
        var nowChar=' '
        for (n in s){
            count++
            if(n==' ' || count==s.length){
                if(count==s.length && n!=' ') nowLen++
                if(nowLen%2!=0){
                    nowLen=0
                    nowChar=' '
                }
                if(nowLen==2 && n!=' ' ) nowChar=n
                if(maxWordLen<nowLen) {
                    maxWordLen=nowLen
                    maxWordChar=nowChar
                }
                nowLen=0
            }
            else {
                nowLen++
                if(nowLen==2) nowChar=n
            }
        }
        println("символ:$maxWordChar") // Второй символ
    }
    else{
        println("вы ничего не ввели ")
    }
}