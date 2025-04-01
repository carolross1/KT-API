import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import compose.project.demo.details.DetailViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {
    val state = vm.state

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
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .background(DeepPurple, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { padding ->
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = DeepPurple,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            state.movie?.let { movie ->
                Box(modifier = Modifier.fillMaxSize()) {
                    // Fondo con imagen y gradiente
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = movie.poster,
                            contentDescription = movie.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            DarkSurface.copy(alpha = 0.9f)
                                        ),
                                        startY = 0f,
                                        endY = 500f
                                    )
                                )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(padding)
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(180.dp))

                        // Título con estilo moderno
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        // Tarjeta principal con información
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(elevation = 16.dp, shape = RoundedCornerShape(24.dp)),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = LightSurface
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp)
                            ) {
                                // Mini póster con detalles
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                ) {
                                    AsyncImage(
                                        model = movie.poster,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(150.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column {
                                        InfoItem(
                                            label = "Título original",
                                            value = movie.title,
                                            iconColor = DeepPurple
                                        )

                                        Spacer(modifier = Modifier.height(12.dp))

                                        InfoItem(
                                            label = "Idioma",
                                            value = movie.originalLanguage,
                                            iconColor = Teal
                                        )
                                    }
                                }

                                // Sección de detalles adicionales
                                Column(
                                    modifier = Modifier
                                        .background(Teal.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "Detalles",
                                        color = Teal,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    Text(
                                        text = "Aquí puedes incluir información adicional como:\n" +
                                                "• Fecha de lanzamiento\n" +
                                                "• Duración\n" +
                                                "• Director\n" +
                                                "• Género",
                                        color = Color.DarkGray,
                                        lineHeight = 24.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botón de acción (ejemplo)
                        Button(
                            onClick = { /* Acción */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Coral,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Añadir a favoritos",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String, iconColor: Color = DeepPurple) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label.uppercase(),
            color = iconColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Text(
            text = value,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}