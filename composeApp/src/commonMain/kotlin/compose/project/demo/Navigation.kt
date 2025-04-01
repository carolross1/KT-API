import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import compose.project.demo.data.MovieService
import compose.project.demo.data.MoviesRepository
import compose.project.demo.details.DetailViewModel
import compose.project.demo.home.HomeViewModel
import compose.project.demo.movies
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import peliculaskmp.composeapp.generated.resources.Res
import peliculaskmp.composeapp.generated.resources.api_key

@Composable
fun Navigation(){
    val navController= rememberNavController()
    val repository= rememberMovieRepository()


//
//    val client = remember {
//        HttpClient{
//            install(ContentNegotiation){
//                json(Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
//    }
//    val apiKey= stringResource(Res.string.api_key)
//    val viewModel= aviewModel{
//        HomeViewModel(MovieService(apiKey,client))
//    }
    val viewModel=

    NavHost(navController= navController, startDestination = "home"){
        composable("home"){
            HomeScreen (
                onMovieClick = { movie ->
                    navController.navigate("details/${movie.id}")
                },
                vm= viewModel{
                    HomeViewModel(repository)
                }
            )
        }

        composable(
            "details/{movieId}",
            arguments = listOf(navArgument("movieId"){type= NavType.IntType})
        ){
                backStackEntry ->
            val movieId= checkNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                vm= viewModel { DetailViewModel(movieId, repository) },
                onBack = {navController.popBackStack()}
            )
        }
    }
}

@Composable
private fun rememberMovieRepository(): MoviesRepository {
val apiKey = stringResource(Res.string.api_key)
val client =
    HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest){
            url{
                protocol= URLProtocol.HTTPS
                host= "api.themoviedb.org"
                parameters.append("api_key",apiKey)
            }
        }
    }
    val repository= MoviesRepository(MovieService(client))
    return remember { repository }
}