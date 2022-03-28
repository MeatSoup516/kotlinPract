class ValidateString : StringValidation {

    val a : Map<Int, String> = mapOf()
    override fun check(regex: Regex, inputString : String?): Boolean =
        inputString?.matches(regex) == true && inputString.isNotEmpty()
}

