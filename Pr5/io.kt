class InputString : Read {
    override fun readString() : String? {
        return readLine()
    }
}

class LooperInput(
    private val stringValidation : StringValidation = ValidateString(),
    private val outputMessage : PrintMessage = Speaker(),
    private val readString : Read = InputString()
) : InputLooper {
    override fun input(message: String, regex: Regex): String? {
        var inputString: String? = ""
        while (!stringValidation.check(regex, inputString) && inputString != null) {
            outputMessage.printMessage(message)
            inputString = readString.readString()
        }
        return inputString
    }
}

class InputIndex(
    private val outputMessage : PrintMessage = Speaker(),
    private val intParser : IntParser = ParserToInt(),
    private val stringValidation : StringValidation = ValidateString(),
    private val inputString : Read = InputString()
) : RowIndex {
    override fun getIndex(message: String, listSize : Int): Int? {
        var indexInt : Int? = -1
        while (indexInt == -1) {
            outputMessage.printMessage(message)
            val indexString: String? = inputString.readString()
            if (indexString != null) {
                if (stringValidation.check("^[1-9]+\\d*".toRegex(), indexString)) {
                    indexInt = intParser.parseToInt(indexString)
                    if (indexInt !in 1..listSize)
                        indexInt = -1
                } else
                    outputMessage.printMessage("\nIncorrect row num, try again\n")
            }
            else {
                outputMessage.printMessage("\nExit\n")
                indexInt = null
            }
        }
        return indexInt
    }
}

class DeleteRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val outputMessage : PrintMessage = Speaker(),
    private val outputData : ExecutorCommand = OutputData()
) : ExecutorCommand {
    override fun execute(dataBase : Table) {
        if (dataBase.getList().toList().isNotEmpty()) {
            outputData.execute(dataBase)
            val index = inputIndex.getIndex("Enter row for deleting -> ", dataBase.getList().toList().size)
            if (index != null)
                dataBase.delete(index - 1)
            else
                outputMessage.printMessage("\nExit\n")
        }
        else {
            outputMessage.printMessage("\nEmpty db\n")
        }
    }
}

class InputDataRow(
    private val listColumns: List<Column> = listOf(FilmColumn(), HallColumn(), CinemaColumn(), DateColumn(), TimeColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        val nullableDataRow = NullableDataRow()
        listColumns.forEach {
            it.add(nullableDataRow)
        }
        if (nullableDataRow.cinema != null &&
            nullableDataRow.film != null &&
            nullableDataRow.hall != null &&
            nullableDataRow.date != null &&nullableDataRow.time != null)
            dataBase.add(nullableDataRow.asDataRow())
    }
}

class EditRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val outputMessage : PrintMessage = Speaker(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val outputData : ExecutorCommand = OutputData(),
    private val listColumns : List<Column> = listOf(FilmColumn(), HallColumn(), CinemaColumn(), DateColumn(), TimeColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        if (dataBase.getList().toList().isNotEmpty()) {
            outputData.execute(dataBase)
            val indexRow = inputIndex.getIndex("Enter row for editing -> ",
                dataBase.getList().toList().size)
            if (indexRow != null) {
                outputColumns.printColumns(listColumns.map { it.name })
                val indexColumns = inputIndex.getIndex(
                    "Enter column for editing -> ",
                    listColumns.size
                )
                if (indexColumns != null) {
                    val currentRow = dataBase.getList().toList()[indexRow - 1]
                    listColumns.getOrNull(indexColumns - 1)?.edit(currentRow)
                    dataBase.set(indexRow - 1, currentRow)
                }
                else
                    outputMessage.printMessage("\nExit\n")
            }
            else
                outputMessage.printMessage("\nExit\n")
        }
        else
            outputMessage.printMessage("\nDataBase is empty!")
    }
}

class SortTable(
    private val inputIndex : RowIndex = InputIndex(),
    private val printer: Printer = Printer(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val outputMessage: PrintMessage = Speaker(),
    private val listColumns : List<Column> = listOf(FilmColumn(), HallColumn(), CinemaColumn(), DateColumn(), TimeColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        outputColumns.printColumns(listColumns.map { it.name })
        val indexColumn = inputIndex.getIndex(
            "Enter column num for sorting -> ",
            listColumns.size
        )
        if (indexColumn != null) {
            val list = dataBase.getList().toList()
            printer.show(
                listColumns[indexColumn - 1].sort(list)
            )
        }
        else
            outputMessage.printMessage("\nExit\n")
    }
}

class SearchRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val printer: Printer = Printer(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val outputMessage: PrintMessage = Speaker(),
    private val listColumns : List<Column> = listOf(FilmColumn(), HallColumn(), CinemaColumn(), DateColumn(), TimeColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        outputColumns.printColumns(listColumns.map { it.name })
        val indexColumn = inputIndex.getIndex(
            "Enter column num for searching -> ",
            listColumns.size
        )
        if (indexColumn != null) {
            val list = dataBase.getList().toList()
            printer.show(
                listColumns[indexColumn - 1].search(list)
            )
        }
        else
            outputMessage.printMessage("\nExit\n")
    }
}

class Speaker : PrintMessage {
    override fun printMessage(message: String) { // Переопределение
        print(message)
    }
}

class OutputColumns(
    private val outputMessage : PrintMessage = Speaker()
) : PrintColumns {
    override fun printColumns(columns: List<String>) {
        outputMessage.printMessage("\nHeaders :\n")
        for (i in columns.indices) // внутренние значения возвращает
            println("${i + 1}. ${columns[i]}")
        println()
    }

}

class OutputCommands : PrintCommands {
    override fun printCommands(commands: List<String>) {
        commands.forEach{ // Действие для каждого элемента
            println(it)
        }
    }
}

class OutputData(
    private val printer: Printer = Printer()
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        printer.show(dataBase.getList())
    }
}

class Printer {
    fun show(list : Iterable<DataRow>) {
        if (list.toList().isEmpty())
            println("\nDataBase is empty!\n")
        else
            list.forEachIndexed { index, it -> println("${index + 1}. $it") }
    }
}