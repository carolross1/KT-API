package compose.project.demo

// En Movie.kt (data class)
data class Movie(
    val id:Int,
    val title:String,
    val overview: String,
    val poster: String,
    val originalLanguage: String // Corregido de originalLanguge
)

val movies=(1..100 ).map{
    Movie(
        id= it,
        title = "Movie $it",
        overview = "",
        poster = "http://picsum.photos/200/300?id=$it",
        originalLanguage = " " // Corregido aquí
    )
}
