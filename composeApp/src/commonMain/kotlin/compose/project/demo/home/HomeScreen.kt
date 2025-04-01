import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import compose.project.demo.Movie
import compose.project.demo.home.HomeViewModel
import org.jetbrains.compose.resources.stringResource
import peliculaskmp.composeapp.generated.resources.Res
import peliculaskmp.composeapp.generated.resources.app_name

// Misma paleta de colores que en DetailScreen
val DeepPurple = Color(0xFF673AB7)
val LightPurple = Color(0xFF9575CD)
val Coral = Color(0xFFFF7B6F)
val Teal = Color(0xFF26A69A)
val DarkSurface = Color(0xFF121212)
val LightSurface = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onMovieClick: (Movie) -> Unit, vm: HomeViewModel) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = DeepPurple,
            onPrimary = Color.White,
            primaryContainer = DeepPurple.copy(alpha = 0.2f),
            secondary = LightPurple,
            onSecondary = Color.White,
            secondaryContainer = LightPurple.copy(alpha = 0.2f),
            tertiary = Coral,
            onTertiary = Color.White,
            tertiaryContainer = Coral.copy(alpha = 0.2f),
            background = LightSurface,
            onBackground = Color.Black,
            surface = LightSurface,
            onSurface = Color.Black,
            surfaceVariant = LightSurface.copy(alpha = 0.8f),
            onSurfaceVariant = Color.Black.copy(alpha = 0.6f),
            error = Color.Red,
            onError = Color.White,
            errorContainer = Color.Red.copy(alpha = 0.2f),
            outline = Color.Gray,
            outlineVariant = Color.LightGray,
        )
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(Res.string.app_name),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DeepPurple,
                        titleContentColor = Color.White
                    )
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = LightSurface
        ) { padding ->
            val state = vm.state

            if(state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = DeepPurple,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            // Fondo con gradiente sutil
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                LightSurface,
                                LightSurface.copy(alpha = 0.9f)
                            )
                        )
                    )
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(padding)
                ) {
                    items(state.movies, key = { it.id }) {
                        MovieItem(movie = it, onClick = { onMovieClick(it) })
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = DeepPurple.copy(alpha = 0.2f)
                ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column {
                AsyncImage(
                    model = movie.poster,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 3f)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}