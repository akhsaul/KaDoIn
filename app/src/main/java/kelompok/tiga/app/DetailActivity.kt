package kelompok.tiga.app

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import kelompok.tiga.app.data.Content
import kelompok.tiga.app.model.DetailViewModel
import kelompok.tiga.app.ui.theme.KaDoInTheme
import kelompok.tiga.app.view.DetailScreen

class DetailActivity : ComponentActivity() {
    private lateinit var detailViewModel: DetailViewModel
    companion object{
        const val TAG = "DetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = Content(
            intent.getIntExtra("id", -1),
            intent.getStringExtra("name")!!,
            intent.getStringExtra("title")!!,
            intent.getStringExtra("image")!!,
            intent.getStringExtra("sound")!!
        )

        val audioManager = (getSystemService(Context.AUDIO_SERVICE) as AudioManager?)!!
        setContent {
            KaDoInTheme {
                detailViewModel = viewModel()
                detailViewModel.setManager(audioManager).setData(data)
                DetailScreen(detailViewModel, onBack = {
                    Log.e(TAG, "onBack Pressed")
                    onBackPressed()
                })
            }
        }
    }

    override fun onDestroy() {
        detailViewModel.release()
        super.onDestroy()
    }
}