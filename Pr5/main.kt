fun main() {
    val listCommands : List<Triple<Int, String, ExecutorCommand>> = //ExecutorCommand - паттерн для обработки метода
        listOf(Triple(0, "add", InputDataRow()),
            Triple(1, "edit", EditRow()),
            Triple(2 , "delete", DeleteRow()),
            Triple(3, "sort", SortTable()),
            Triple(4 ,"search", SearchRow()),
            Triple(5, "print all", OutputData()))
    val outputMessage : PrintMessage = Speaker()
    val outputCommands : PrintCommands = OutputCommands()
    val stringValidation : StringValidation = ValidateString()
    val intParser : IntParser = ParserToInt()
    val inputString : Read = InputString()
    val dataBase : Table = DataTable()
    Menu(outputMessage,
        outputCommands,
        stringValidation,
        intParser,
        inputString,
        dataBase,
        listCommands).run() // Запуск меню
}
