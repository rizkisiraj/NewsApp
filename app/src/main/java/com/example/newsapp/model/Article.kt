package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.UUID

@Entity(tableName = "Articles")
data class Article(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val author: String? = null,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)

class ArticleDeserializer : JsonDeserializer<Article> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Article {
        val jsonObject = json.asJsonObject

        val id = if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull) {
            UUID.fromString(jsonObject.get("id").asString)
        } else {
            UUID.randomUUID()
        }
        val author = if (jsonObject.has("author") && !jsonObject.get("author").isJsonNull) jsonObject.get("author").asString else null
        val title = jsonObject.get("title").asString  // Assuming 'title' is always present and not null
        val description = if (jsonObject.has("description") && !jsonObject.get("description").isJsonNull) jsonObject.get("description").asString else null  // Assuming 'description' is always present and not null
        val url = jsonObject.get("url").asString  // Assuming 'url' is always present and not null
        val urlToImage = if (jsonObject.has("urlToImage") && !jsonObject.get("urlToImage").isJsonNull) jsonObject.get("urlToImage").asString else null
        val publishedAt = jsonObject.get("publishedAt").asString  // Assuming 'publishedAt' is always present and not null
        val content = if (jsonObject.has("content") && !jsonObject.get("content").isJsonNull) jsonObject.get("content").asString else null

        return Article(id, author, title, description, url, urlToImage, publishedAt, content)
    }
}

