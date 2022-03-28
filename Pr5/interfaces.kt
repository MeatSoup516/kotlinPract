import java.time.LocalDate
//Табличка
interface Table {
    fun getList() : Iterable<DataRow> // Создание записи
    fun add(dataRow: DataRow) // Добавление
    fun set(index : Int, dataRow: DataRow) // Номер
    fun delete(index: Int) // Удаление по номеру
}

interface Column {
    val name : String
    fun add(nullableDataRow: NullableDataRow)
    fun edit(editableRow : DataRow)
    fun sort(list : List<DataRow>) : List<DataRow>
    fun search(list : List<DataRow>) : List<DataRow>
}

//Ввод
interface RowIndex {
    fun getIndex(message: String, listSize : Int) : Int?
}

interface InputLooper {
    fun input(message : String, regex : Regex) : String?
}

interface Read {
    fun readString() : String?
}


interface ExecutorCommand { // интерфейс
    fun execute(dataBase: Table)
}

//Output
interface PrintMessage {
    fun printMessage(message : String)
}

interface PrintCommands {
    fun printCommands(commands: List<String>)
}

interface PrintColumns {
    fun printColumns(columns : List<String>)
}

//Validator
interface StringValidation {
    fun check(regex : Regex, inputString : String?) : Boolean
}

//Parsers
interface IntParser {
    fun parseToInt(inputString: String) : Int
}
