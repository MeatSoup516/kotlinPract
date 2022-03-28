import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ParserToInt : IntParser {
    override fun parseToInt(inputString: String): Int = inputString.toInt()
}

