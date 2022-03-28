import java.time.LocalDate
import java.time.LocalTime

data class DataRow(
    var cinema: String,
    var film: String,
    var hall: Int,
    var date: String,
    var time: String
)

data class NullableDataRow(
    var cinema: String? = null,
    var film: String? = null,
    var hall: Int? = null,
    var date: String? = null,
    var time: String? = null
) {
    fun asDataRow() : DataRow = DataRow(cinema!!, film!!, hall!!, date!!, time!!)
}

class CinemaColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "cinema"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        nullableDataRow.cinema = looperInput.input("Write a name of cinema -> ", "\\w+".toRegex())
    }

    override fun edit(editableRow: DataRow) {
        val cinema = looperInput.input("Write a name of cinema -> ", "\\w+".toRegex())
        if (cinema != null)
            editableRow.cinema = cinema
    }

    override fun sort(list : List<DataRow>) : List<DataRow> = list.sortedBy { it.cinema }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nWrite a name of cinema -> ")
        return list.filter {
            it.cinema.contains(inputString.readString()!!)
        }
    }


}

class FilmColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "film"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        val film = looperInput.input("Write  a name of film -> ", "\\w+".toRegex())
        if (film != null)
            nullableDataRow.film = film
    }

    override fun edit(editableRow: DataRow) {
        val newfilm = looperInput.input("Write  a name of film -> ", "\\w+".toRegex())
        if (newfilm != null)
            editableRow.film = newfilm
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.film }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nWrite  a name of film -> ")
        return list.filter {
            it.film.toString().contains(inputString.readString()!!)
        }
    }


}

class HallColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val intParser : IntParser = ParserToInt(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "hall"
) : Column{
    override fun add(nullableDataRow: NullableDataRow) {
        val hall = looperInput.input("Write hall  -> ", "^[1-9]+\\d*?".toRegex())
        if (hall != null)
            nullableDataRow.hall = intParser.parseToInt(hall)
    }

    override fun edit(editableRow: DataRow) {
        val newHall = looperInput.input("Write hall -> ", "^[1-9]+\\d*?".toRegex())
        if (newHall != null)
            editableRow.hall = intParser.parseToInt(newHall)
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.hall }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nWrite hall -> ")
        return list.filter { // Возвращает фильтрованный лист
            it.hall.toString().contains(inputString.readString()!!)
        }
    }


}

class DateColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val intParser : IntParser = ParserToInt(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "date"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        val date = looperInput.input("Write date (format dd.mm.yyyy) -> ", "(0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](\\d{4}|\\d{2})".toRegex())
        if (date != null)
            nullableDataRow.date = date
    }

    override fun edit(editableRow: DataRow) {
        val newDate = looperInput.input(
            "Write date (format dd.mm.yyyy) -> ",
            "(0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](\\d{4}|\\d{2})".toRegex()
        )
        if (newDate != null)
            editableRow.date = newDate
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.date }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nWrite date  (format dd.mm.yyyy) -> ")
        return list.filter {
            it.date.contains(inputString.readString()!!)
        }
    }


}

class TimeColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val outputMessage: PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "time"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        val time = looperInput.input("Write time (format hh.mm) -> ", "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]\$".toRegex())
        if (time != null)
            nullableDataRow.time = time
    }

    override fun edit(editableRow: DataRow) {
        val newtime = looperInput.input("Write time (format hh.mm) -> ", "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]\$".toRegex())
        if (newtime != null)
            editableRow.date = newtime
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.time }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nWrite time (format hh.mm) -> ")
        return list.filter {
            it.time.contains(inputString.readString()!!)
        }
    }
}


class DataTable : Table {
    private val list : MutableList<DataRow> = mutableListOf()
    override fun getList() : Iterable<DataRow> {
        return list.toList()
    }
    override fun add(dataRow: DataRow) {
        this.list.add(dataRow)
    }
    override fun set(index : Int, dataRow: DataRow) {
        this.list[index] = dataRow
    }
    override fun delete(index : Int) {
        this.list.removeAt(index)
    }
}
