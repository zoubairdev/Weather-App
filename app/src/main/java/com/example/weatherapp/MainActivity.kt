// Add the necessary dependencies in your app's build.gradle file:
// implementation "androidx.compose.material:material:1.0.0"
// implementation "androidx.compose.ui:ui:1.0.0"
// implementation "androidx.compose.foundation:foundation:1.0.0"
// implementation "androidx.lifecycle:lifecycle-runtime:2.3.1"

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherApp()
                }
            }
        }
    }
}

@Composable
fun WeatherApp() {
    var searchText by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        SearchBar(
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onSearchButtonClick = { isSearching = !isSearching }
        )

        // Weather Card
        if (isSearching) {
            // Simulate weather data
            WeatherCard(city = searchText)
        }
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .background(Color.Gray.copy(0.1f))
                .clip(MaterialTheme.shapes.medium)
                .padding(8.dp)
        )

        IconButton(
            onClick = onSearchButtonClick,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .clip(MaterialTheme.shapes.medium)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun WeatherCard(city: String) {
    // Simulated weather data
    val weatherData = generateWeatherData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Current Weather
        CurrentWeatherCard(weatherData.currentWeather)

        // Upcoming Days
        UpcomingDays(weatherData.upcomingDays)
    }
}

@Composable
fun CurrentWeatherCard(currentWeather: WeatherInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Weather Icon
            Image(
                painter = painterResource(id = R.drawable.wb_sunny), // Replace with actual weather icon
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Weather Info
            Text(
                text = currentWeather.weatherState,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            // Temperature
            Text(
                text = "${currentWeather.temperature}°C",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun UpcomingDays(upcomingDays: List<WeatherInfo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(upcomingDays) { day ->
            WeatherRow(day = day)
        }
    }
}

@Composable
fun WeatherRow(day: WeatherInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Day of the week
        Text(
            text = day.dayOfWeek,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

        // Weather Icon
        Image(
            painter = painterResource(id = R.drawable.wb_sunny), // Replace with actual weather icon
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 8.dp)
        )

        // Temperature
        Text(
            text = "${day.temperature}°C",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

@Composable
fun generateWeatherData(): WeatherData {
    // Simulated weather data
    val currentWeather = WeatherInfo(
        weatherState = "Sunny",
        temperature = 25,
        dayOfWeek = "Today"
    )

    val upcomingDays = listOf(
        WeatherInfo(weatherState = "Sunny", temperature = 27, dayOfWeek = "Monday"),
        WeatherInfo(weatherState = "Cloudy", temperature = 23, dayOfWeek = "Tuesday"),
        WeatherInfo(weatherState = "Rainy", temperature = 18, dayOfWeek = "Wednesday"),
        WeatherInfo(weatherState = "Thunderstorm", temperature = 20, dayOfWeek = "Thursday"),
        WeatherInfo(weatherState = "Snowy", temperature = 15, dayOfWeek = "Friday")
    )

    return WeatherData(currentWeather, upcomingDays)
}

data class WeatherData(
    val currentWeather: WeatherInfo,
    val upcomingDays: List<WeatherInfo>
)

data class WeatherInfo(
    val weatherState: String,
    val temperature: Int,
    val dayOfWeek: String
)

@Preview(showBackground = true)
@Composable
fun PreviewWeatherApp() {
    WeatherAppTheme {
        WeatherApp()
    }
}
