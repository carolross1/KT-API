package compose.project.demo.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val client: HttpClient
) {
    suspend fun fetchPopularMovies(): RemoteResult { // Corregido nombre del método
        return client
            .get("/3/discover/movie?sort_by=popularity.desc") // Corregido parámetro
            .body<RemoteResult>()
    }

    suspend fun fetchMovieById(id: Int): RemoteMovie { // Cambiado tipo de retorno
        return client
            .get("/3/movie/$id")
            .body<RemoteMovie>()
    }
}
