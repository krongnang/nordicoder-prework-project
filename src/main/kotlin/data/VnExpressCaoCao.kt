package data

import model.News
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import repo.CaoCao
import utils.FileUtils

class VnExpressCaoCao : CaoCao {
    private val store = StoreImpl();
    override fun getAndSaveNewsInfoToMemory(url: String): News {
        val news = News()
        val doc: Document = Jsoup.connect(url).get()
        var author: String = ""
        try {
//            author = doc.getElementsByTag("b").last().text()
            author = doc.getElementsByClass("section page-detail top-detail")[0]
                .getElementsByClass("Normal").last()
                .text()
        } catch (ex: java.lang.Exception) {
            author = "buồn ngủ quá"
        }
        val createdAt = doc.getElementsByClass("time-now").text()
        val title = doc.getElementsByTag("title").text()
        news.url = url
        news.author = author
        news.title = title
        news.createdAt = createdAt

        store.saveNewsInfo(news)

        FileUtils.writeLog(news);
        return news;
    }

    override fun getUrlsFromHome(url: String): List<String> {
        val urls = mutableListOf<String>();
        val doc: Document = Jsoup.connect(url).get()
        val newsHeadlines: Elements = doc.select(".title-news")
        for (headline in newsHeadlines) {
            val newsUrl = headline.getElementsByAttribute("href").attr("href")
            urls.add(newsUrl);
        }
        return urls;
    }

    override fun getUrlsFromNewsDetail(url: String): List<String> {
        try {
            val urls = mutableListOf<String>();
            val doc: Document = Jsoup.connect(url).get()
            val newsHeadlines: Elements = doc.getElementsByTag("article")[0].getElementsByAttribute("data-id")
            for (headline in newsHeadlines) {
                val newsUrl = headline.getElementsByAttribute("href").attr("href")
                urls.add(newsUrl);
            }
            println("Kiểm tra post url $url Có ${urls.size} Bài khác")
            return urls;
        } catch (ex: Exception) {
            // nothing to to
            return emptyList();
        }
    }

}