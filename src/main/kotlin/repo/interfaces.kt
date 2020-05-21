package repo

import model.News

interface CaoCao {
    fun getUrlsFromNewsDetail(url: String): List<String>
    fun getUrlsFromHome(url: String): List<String>
    fun getAndSaveNewsInfoToMemory(url: String): News
}

interface Store {
    fun saveUrlToHashIfNeeded(url: String): Boolean
    fun markUrlAsDone(url: String)
    fun saveNewsInfo(news: News)
}
