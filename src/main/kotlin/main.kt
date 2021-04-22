import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val articles = mutableListOf<Article>()


fun readLineTrim() = readLine()!!.trim()

fun getArticleById(id:Int):Article?{
    for(article in articles){
        if(article.id == id){
            return article
        }
    }

    return null
}

fun main() {
    println(" == 게시판 관리 프로그램 ==")

    var articlesLastId = 0

//    val articles = mutableListOf<Article>() 함수 사용을 위에 밖에다 선언
    while(true){
        print("명령어 )")
        val command = readLine()
        println("입력한 명령어 : $command")
        val commandArr = command!!.split(" ")
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
//            var update = regDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            var update = ""
            val article = Article(id, title, body,regdate,update )
            println("${id}번 게시물이 작성 되었습니다.")
            articles.add(article)
            articlesLastId = id
        }else if(command == "article list"){
            println("번호 / 제목 / 작성 일자")
            for (article in articles) println("${article.id} / ${article.title} - ${article.regDate}")
        } else if(command.startsWith("article delete")){
            val id = command.trim().split(" ")[2].toInt()
//            var articleToDelete:Article?= null
//
//            for( article in articles){
//                if(article.id == id){
//                    articleToDelete = article
//                }else{
//                    articleToDelete = null
//                }
//            }      이것을 자주 사용하기 위해 위에 만든 함수로 사용
            var articleToDelete:Article? = getArticleById(id)

            if(articleToDelete == null){
                println("${id}번 글이 존재하지 않습니다.")
            }else{
                articles.remove(articleToDelete)
                println("${id}번글이 삭제되었습니다.")
            }


        }


//        else if(commandArr.size == 3){
//            var ck = commandArr[0]+" "+commandArr[1]
//            var num = commandArr[2].toInt()
//            var bol = 0
//            if(ck=="article delete"){
//                for(i in articles.indices){
//                    if(articles[i].id == num){
//                        bol = 1
//                    }
//                }
//
//                if(bol ==0){
//                    println("${num}번글이 존재하지 않습니다.")
//                }else{
//                    articles.removeAt(num-1)
//                    println("${num}번글이 삭제되었습니다.")
//                }
//
//            }
//        }

        else if(command.startsWith("article modify")){
            var id:Int = command.trim().split(" ")[2].toInt()
            var articleToModify = getArticleById(id)

            if(articleToModify == null){
                println("${id}번 게시글이 존재하지 않습니다.")
            }else{
                println("${id}번 게시물을 수정합니다.")
                print("새 제목 : ")
                articleToModify.title = readLineTrim()
                print("새 내용 : ")
                articleToModify.body = readLineTrim()
                var mDate = LocalDateTime.now()
                var regdate = mDate!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

                articleToModify.updateDate = regdate
                articles[id-1] = articleToModify
            }
        }else if(command.startsWith("article detail")){
            var id:Int = command.trim().split(" ")[2].toInt()
            var articleToDetail = getArticleById(id)
            
            if(articleToDetail == null){
                println("${id}번 게시글이 존재하지 않습니다.")
            }else{
                println("번호 : ${articleToDetail.id}")
                println("작성 날짜 : ${articleToDetail.regDate}")
                if(articleToDetail.updateDate==""){
                    println("갱신 날짜 : 없음")
                }else{
                    println("갱신 날짜 : ${articleToDetail.updateDate}")
                }
                println("제목 : ${articleToDetail.title}")
                println("내용 : ${articleToDetail.body}")

            }
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
    var updateDate:String,
){}
