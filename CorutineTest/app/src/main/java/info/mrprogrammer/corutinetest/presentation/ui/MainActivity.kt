package info.mrprogrammer.corutinetest.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import info.mrprogrammer.corutinetest.presentation.viewmodel.AppViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            DisplayInListView()
        }
    }


    @Composable
    fun DisplayInListView(viewModel: AppViewModel = hiltViewModel()) {
        val uiStateList by viewModel.listState.collectAsStateWithLifecycle()
        Scaffold(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(uiStateList) {
                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                           Text(text = "Title")
                           Text(
                               text = it.title,
                               modifier = Modifier.padding(vertical = 5.dp),
                               color = Color.Black
                           )
                       }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Boxy")
                            Text(text = it.body, color = Color.Black)
                        }
                    }

                }
            }
        }
    }
}