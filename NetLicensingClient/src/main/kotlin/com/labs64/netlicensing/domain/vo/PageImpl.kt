package com.labs64.netlicensing.domain.vo

import java.io.Serializable

class PageImpl<Entity : Any>(
    content: List<Entity>?,
    override val pageNumber: Int?,
    override val itemsNumber: Int?,
    override val totalPages: Int?,
    override val totalItems: Long?,
    private val hasNext: Boolean?
) : Page<Entity>, Serializable {

    override val content = ArrayList<Entity>()

    init {
        assert(content != null) { "Content must not be null!" }

        this.content.addAll(content!!)
    }

    override fun hasNext(): Boolean? {
        return hasNext
    }

    override fun iterator(): Iterator<Entity> {
        return content.iterator()
    }

    override fun hasContent(): Boolean {
        return !content.isEmpty()
    }

    override fun toString(): String {
        var contentType = "UNKNOWN"

        if (hasContent()) {
            contentType = content[0].javaClass.getName()
        }

        return String.format("Page %s of %d containing %s instances", pageNumber, totalPages, contentType)
    }

    companion object {
        fun <E : Any> createInstance(
            content: List<E>?,
            pageNumber: String?,
            itemsNumber: String?,
            totalPages: String?,
            totalItems: String?,
            hasNext: String?
        ): PageImpl<E> {
            try {
                return PageImpl(
                    content,
                    Integer.valueOf(pageNumber),
                    Integer.valueOf(itemsNumber),
                    Integer.valueOf(totalPages),
                    java.lang.Long.valueOf(totalItems),
                    java.lang.Boolean.valueOf(hasNext)
                )
            } catch (e: Exception) {
                return PageImpl(content, 0, 0, 0, 0, false)
            }
        }
    }
}
