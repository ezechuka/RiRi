package com.tech.riri.shared.data.local

import com.tech.riri.shared.cache.TextObjectDatabaseDriverFactory
import com.tech.riri.shared.cache.TextObjectSqlDelightDatabase
import com.tech.riri.shared.data.models.TextObjectDatabaseModel

class TextSqlDelightDatabase(textObjectDatabaseDriverFactory: TextObjectDatabaseDriverFactory) :
    TextObjectInterface {
    val database =
        TextObjectSqlDelightDatabase.invoke(textObjectDatabaseDriverFactory.createDriver())
    val textObjectQueries = database.textObjectSqlDelightDatabaseQueries
    override fun addText(text: String) {
        textObjectQueries.insertText(text)
    }

    override fun deleteText(id: Long) {
        textObjectQueries.deleteText(id)
    }

    override fun getTexts(): List<TextObjectDatabaseModel> {
        return textObjectQueries.getText().executeAsList().map {
            TextObjectDatabaseModel(it.text, it.id)
        }
    }
}