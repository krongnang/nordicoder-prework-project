package model

class News(var title: String = "", var url: String = "", var author: String = "", var createdAt: String = "") {
    override fun toString(): String {
        return "info(title='$title', url='$url', author='$author', createdAt='$createdAt')"
    }
}