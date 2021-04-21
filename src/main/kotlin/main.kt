import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun readLineTrim() = readLine()!!.trim()

fun main() {
    println(" == 게시판 관리 프로그램 ==")

    var articlesLastId = 0

    val articles = mutableListOf<Article>()
    while(true){
        print("명령어 )")
        val command = readLine()
        println("입력한 명령어 : $command")

        if(command == "system exit"){
            println("프로그램 종료")
            break
        }else if(command == "article write"){
            val id = articlesLastId+1
            print("제목 : ")
            val title = readLineTrim()
            print("내용 : ")
            val body = readLineTrim()
            val regDate: LocalDateTime? = LocalDateTime.now()
            var regdate = regDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            var update = regDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val article = Article(id, title, body,regdate,update )
            println("${id}번 게시물이 작성 되었습니다.")
            articles.add(article)
            articlesLastId = id
        }else if(command == "article list"){
            println("번호 / 제목")
            for (article in articles) println("${article.id} / ${article.title} - ${article.regDate}")
        }else{
            println("$command 은(는) 존재하지 않는 명령어 입니다.")
        }
    }
}


data class Article(
    var id:Int,
    var title:String,
    var body:String,
    val regDate:String,
    val updateDate:String,
){}
