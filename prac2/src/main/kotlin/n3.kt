//Функции, созданные в задании No1, модифицируйте таким образом, что-
//бы условие, по которому происходит отбор, можно было передавать как ар-
//гумент (один из аргументов функции должен быть lambda со значением по
//умолчанию – условием, что указано было в вашем варианте).
// tailrec-функция

//tailrec fun sumtail(s:String, len:Int=(s.length-2), resI:Int=0): Int {
//    var res=resI
//    return if(len>=0){
//        res+=s[len].code-48
//        sumtail(s,len-2,res)
 //   } else res
//}
//обычная функция
//fun n2.sum(s:String):Int{
//    var len = s.length-2
//    var res=0
 //  while(len>=0){
//        res+=s[len].code-48
 //       len-=2
 //      }
  //  return res
//}
//fun n2.main() {
//    val s = readLine()
 //  if(s!=null){
 //      println(sumtail(s))
 //   }
 //  else  println("вы ничего не ввели")
//}