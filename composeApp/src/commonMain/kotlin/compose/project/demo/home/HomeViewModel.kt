package compose.project.demo.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import compose.project.demo.Movie
import compose.project.demo.data.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MoviesRepository
):ViewModel() {
    var state by mutableStateOf(UiState())
    private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading =  false, movies = repository.fetchPopularMovies())
        }
    }
    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

//    private fun RemoteMovie.toDomainMovie() = Movie(
//        id= id,
//        title= title,
//        poster = "https://image.tmdb.org/t/p/w500$posterPath"
//    )
}