package ru.kpfu.itis.hometask7

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import ru.kpfu.itis.hometask7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    val permissionsRequestHandler: PermissionsRequestHandler = PermissionsRequestHandler(this)

    val openCameraHandler = registerForActivityResult(ActivityResultContracts.TakePicture()) {
    }

    val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, getString(R.string.cancelled_message), Toast.LENGTH_LONG).show()
        } else {
            val uri = Uri.parse(result.contents)
            val openLinkIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(openLinkIntent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            val fragment = RecyclerFragment.getInstance()
            supportFragmentManager
                .beginTransaction()
                .add(
                    containerId, fragment,
                    RecyclerFragment.RECYCLER_FRAGMENT_TAG
                )
                .commit()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val containerId = R.id.fragment_container
    }

}
