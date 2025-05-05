package com.aks.hotnews.data.model.other

data class LanguageCode(
    val Language : String,
    val Code : String
)

val languages = listOf(
    LanguageCode("Afar", "aa"), LanguageCode("Amharic", "am"), LanguageCode("Arabic", "ar"),
    LanguageCode("Bengali", "bn"), LanguageCode("Bosnian", "bs"), LanguageCode("Bulgarian", "bg"),
    LanguageCode("Burmese", "my"), LanguageCode("Chinese", "zh"), LanguageCode("Croatian", "hr"),
    LanguageCode("Czech", "cs"), LanguageCode("Danish", "da"), LanguageCode("Dutch", "nl"),
    LanguageCode("English", "en"), LanguageCode("Estonian", "et"), LanguageCode("Finnish", "fi"),
    LanguageCode("French", "fr"), LanguageCode("German", "de"), LanguageCode("Greek", "el"),
    LanguageCode("Hebrew", "he"), LanguageCode("Hindi", "hi"), LanguageCode("Hungarian", "hu"),
    LanguageCode("Icelandic", "is"), LanguageCode("Indonesian", "id"), LanguageCode("Italian", "it"),
    LanguageCode("Japanese", "ja"), LanguageCode("Korean", "ko"), LanguageCode("Lao", "lo"),
    LanguageCode("Latvian", "lv"), LanguageCode("Lithuanian", "lt"), LanguageCode("Macedonian", "mk"),
    LanguageCode("Malay", "ms"), LanguageCode("Maltese", "mt"), LanguageCode("Marathi", "mr"),
    LanguageCode("Māori", "mi"), LanguageCode("Nepali", "ne"), LanguageCode("Norwegian", "nb"),
    LanguageCode("Norwegian", "no"), LanguageCode("Persian", "fa"), LanguageCode("Polish", "pl"),
    LanguageCode("Portuguese", "pt"), LanguageCode("Romanian", "ro"), LanguageCode("Russian", "ru"),
    LanguageCode("Serbian", "sr"), LanguageCode("Sinhalese", "si"), LanguageCode("Slovak", "sk"),
    LanguageCode("Slovene", "sl"), LanguageCode("Somali", "so"), LanguageCode("Spanish", "es"),
    LanguageCode("Swedish", "sv"), LanguageCode("Tajik", "tg"), LanguageCode("Tamil", "ta"),
    LanguageCode("Telugu", "te"), LanguageCode("Thai", "th"), LanguageCode("Turkish", "tr"),
    LanguageCode("Ukrainian", "uk"), LanguageCode("Urdu", "ur"), LanguageCode("Uzbek", "uz"),
    LanguageCode("Vietnamese", "vi")
)