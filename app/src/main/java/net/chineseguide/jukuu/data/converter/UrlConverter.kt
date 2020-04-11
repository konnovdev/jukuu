package net.chineseguide.jukuu.data.converter

import java.net.URLEncoder
import javax.inject.Inject

class UrlConverter @Inject constructor() {

    fun convert(unformattedString: String): String =
        URLEncoder.encode(unformattedString, "utf-8")
}