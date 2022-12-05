package ru.kpfu.itis.hometask7

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionsRequestHandler(private val activity: AppCompatActivity,
                                var singlePermissionCallback: (() -> Unit)? = null) {

    private val requestSinglePermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { when (it) {
        true -> singlePermissionCallback?.invoke()
        else -> null
    }
    }

    fun requestSinglePermission(permission: String) {
        requestSinglePermissionLauncher.launch(permission)
    }

}
