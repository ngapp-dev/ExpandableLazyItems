package com.ngapps.expandablelazyitems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ngapps.expandablelazyitems.model.Player
import com.ngapps.expandablelazyitems.ui.theme.ExpandableLazyItemsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val players = listOf(
            Player("Lionel Messi", "Paris Saint-Germain", "Forward", "Football"),
            Player("Cristiano Ronaldo", "Manchester United", "Forward", "Football"),
            Player("Neymar Jr.", "Paris Saint-Germain", "Forward", "Football"),
            Player("Kylian Mbappé", "Paris Saint-Germain", "Forward", "Football"),
            Player("LeBron James", "Los Angeles Lakers", "Forward", "Basketball"),
            Player("Kevin Durant", "Brooklyn Nets", "Forward", "Basketball"),
            Player("Giannis Antetokounmpo", "Milwaukee Bucks", "Forward", "Basketball"),
            Player("Stephen Curry", "Golden State Warriors", "Guard", "Basketball"),
            Player("Luka Dončić", "Dallas Mavericks", "Guard", "Basketball"),
            Player("Connor McDavid", "Edmonton Oilers", "Forward", "Hokey"),
            Player("Alex Ovechkin", "Washington Capitals", "Forward", "Hokey"),
            Player("Nathan MacKinnon", "Colorado Avalanche", "Forward", "Hokey"),
            Player("Victor Hedman", "Tampa Bay Lightning", "Defense", "Hokey"),
            Player("Auston Matthews", "Toronto Maple Leafs", "Forward", "Hokey"),
        )

        setContent {
            ExpandableLazyItemsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(players)
                }
            }
        }
    }
}