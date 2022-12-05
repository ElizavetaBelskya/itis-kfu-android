package ru.kpfu.itis.hometask6

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionsRequestHandler(private val activity: AppCompatActivity,
                                var singlePermissionCallback: (() -> Unit)? = null) {

    private val requestPermissionsLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it.values.contains(true)) {
            singlePermissionCallback?.invoke()
        }
    }

    fun requestPermissions(permissions: Array<String>) {
        requestPermissionsLauncher.launch(permissions)
    }
}
