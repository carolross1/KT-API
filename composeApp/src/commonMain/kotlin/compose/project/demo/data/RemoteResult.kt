package compose.project.demo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val page: Int,
    @SerialName("results") val result: List<RemoteMovie>,  // Cambiado de "result" a "results"
    @SerialName("total_pages") val totalPages: Int,        // Cambiado de "total_page" a "total_pages"
    @SerialName("total_results") val totalResults: Int     // Cambiado de "total_result" a "total_results"
)

@Serializable
data class RemoteMovie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,  // Hacerlo nullable
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double
)

