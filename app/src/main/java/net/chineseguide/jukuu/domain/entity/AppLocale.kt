package net.chineseguide.jukuu.domain.entity

enum class AppLocale(val id: Int, val languageCode: String, val countryCode: String) {

    ENGLISH(0,"en", ""),
    RUSSIAN(1,"ru", ""),
    CHINESE(2, "zh", ""),
    TRADITIONAL_CHINESE(3,"zh", "TW");

    companion object {

        fun default(): AppLocale = ENGLISH

        fun getById(id: Int): AppLocale = values().first { it.id == id }
    }
}
