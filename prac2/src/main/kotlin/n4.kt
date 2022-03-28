// передавайте lambda, имеющую смысл – способ сравнения двух чисел;
//изменяя данную lambda, пользователь вашей функции должен иметь возможность
// получить информацию либо о самых длинных, либо о самых коротких словах.
// Аналогичным образом передавайте в вашу функцию lambda, которая будет определять условие отбора слов.
fun secondCharInWord(s:String, lamb1:(Int)->Boolean, lamb2:(Int, Int)->Boolean ):Char{
    var maxWordLen=0
    var maxWordChar=' '
    var count=0
    var nowLen=0
    var nowChar=' '
    for (n in s){
        count++
        if(n==' ' || count==s.length){
            if(maxWordLen==0&&lamb1(nowLen)) {
                maxWordLen = nowLen
                maxWordChar=nowChar
            }
            if(count==s.length && n!=' ') nowLen++
            if(nowLen==2 && n!=' ' ) nowChar=n
//            println("maxWordLen:$maxWordLen,nowLen:$nowLen ")
//            println("maxWordChar:$maxWordChar,nowChar:$nowChar ")
            if(lamb1(nowLen)&&lamb2(maxWordLen,nowLen)) {
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
    return maxWordChar
}
fun main() {
    val s = readLine()
    if(s!=null){
        val c= secondCharInWord(s, { nowLen: Int -> nowLen % 2 == 0 }, { maxWordLen:Int, nowLen:Int-> maxWordLen > nowLen })
        println(c)
    }
    else{
        println("вы ничего не ввели ")
    }
}