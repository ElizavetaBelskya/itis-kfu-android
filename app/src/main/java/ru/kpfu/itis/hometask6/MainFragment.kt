package ru.kpfu.itis.hometask6

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
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import ru.kpfu.itis.hometask6.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {

            btnCreate.setOnClickListener {
                val intent = Intent(requireContext(), LocationService::class.java)
                requestPermission {
                    startForegroundService(requireContext(), intent)
                }

            }

            btnCancel.setOnClickListener {
                val intent = Intent(requireContext(), LocationService::class.java)
                requireContext().stopService(intent)
            }

        }

    }

    private fun requestPermission(openAppCallback: () -> Unit) {
        (requireActivity() as MainActivity).permissionsRequestHandler?.singlePermissionCallback = openAppCallback
        when {
            ((ContextCompat.checkSelfPermission(requireContext(), permissions[0])) == PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission(requireContext(), permissions[1])) == PackageManager.PERMISSION_GRANTED -> {
                openAppCallback.invoke()
            }

            (shouldShowRequestPermissionRationale(permissions[0]) && shouldShowRequestPermissionRationale(permissions[1]))
            -> showOpenSettingsAlert()

            else -> {
                (requireActivity() as MainActivity).permissionsRequestHandler?.requestPermissions(permissions)
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
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message_location))
                .setPositiveButton("Settings") { _, _ ->
                    startActivity(settingsIntent)
                }
                .create()
                .show()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null;
    }

    companion object {
        val permissions = arrayOf<String>(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
        @JvmStatic
        fun getInstance() = MainFragment()
    }

}
