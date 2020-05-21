package cache

import model.News
import kotlin.random.Random

// học từ anh Hien Nguyen: https://gurunh.com/2018/05/singleton-co-thuc-su-de/
open class AppCache private constructor() {

    /**
     * Tất cà các url lấy được sẽ lưu tạm vô đây
     * key: Url
     * value: has processed
     */
    var newsUrl: HashMap<String, Boolean>

    /**
     * Tất cả Bài viết lấy được sẽ lưu vô đây
     * Lưu để truy suất lại cho lẹ, đỡ phải đọc file thôi ^^
     */

    var news: ArrayList<News>

    /**
     * Field này để log ra xem instance có thực sự là singleton hay không thôi.
     * (xem thử constructor dc khởi tạo bao nhiêu lần)
     */
    var random: Int

    companion object {
        @Volatile
        private var self: AppCache? = null

        @get:Synchronized
        val instance: AppCache?
            get() {
                if (self == null) {
                    self = AppCache()
                }
                return self
            }
    }

    init {
        if (self != null) {
            throw UnsupportedOperationException("Use getInstance()")
        }
        random = Random.nextInt(0, 100)
        newsUrl = HashMap()
        news = arrayListOf()
        println("initialize ${newsUrl.hashCode()} -- random ${random}");
    }
}