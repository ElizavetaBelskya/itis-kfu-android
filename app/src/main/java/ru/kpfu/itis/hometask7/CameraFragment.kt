package ru.kpfu.itis.hometask7

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.ScanOptions
import ru.kpfu.itis.hometask7.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    private var binding: FragmentCameraBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnOpenScanner?.setOnClickListener {
            requestPermission {
                val options = ScanOptions().setOrientationLocked(false)
                (requireActivity() as MainActivity).barcodeLauncher.launch(options)
            }
        }

        binding?.btnOpenCamera?.setOnClickListener {
            requestPermission {
                (requireActivity() as MainActivity).openCameraHandler.launch("".toUri())
            }
        }

    }


    private fun requestPermission(openAppCallback: () -> Unit) {
        (requireActivity() as MainActivity).permissionsRequestHandler.singlePermissionCallback = openAppCallback
        when {
            (ContextCompat.checkSelfPermission(requireContext(), permission)) == PackageManager.PERMISSION_GRANTED -> {
                openAppCallback.invoke()
            }

            (shouldShowRequestPermissionRationale(permission)) -> showOpenSettingsAlert()

            else -> {

                (requireActivity() as MainActivity).permissionsRequestHandler.requestSinglePermission(permission)
            }
        }
    }



    private fun showOpenSettingsAlert() {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireContext().packageName, null)
        )

        if (requireActivity().packageManager.resolveActivity(settingsIntent, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            Toast.makeText(requireContext(), getString(R.string.acces_denied), Toast.LENGTH_LONG).show()
        } else {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.cam_dialog_title))
                .setMessage(getString(R.string.cam_dialog_message))
                .setPositiveButton("Settings") { _, _ ->
                    startActivity(settingsIntent)
                }
                .create()
                .show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    companion object {

        const val permission = android.Manifest.permission.CAMERA
        const val CAMERA_FRAGMENT_TAG = "CAMERA_FRAGMENT_TAG"

        fun getInstance() = CameraFragment()
    }
}
