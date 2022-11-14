package ru.kpfu.itis.hometask6

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ru.kpfu.itis.hometask6.databinding.FragmentMainBinding
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null
    private var lastIntent: PendingIntent? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        binding?.apply {

            if (isAirplaneModeOn(requireContext())) {
                etTitle.isEnabled = false
                etShortMessage.isEnabled = false
                etLongMessage.isEnabled = false
                etTime.isEnabled = false
                checkBox.isEnabled = false
                btnCreate.isEnabled = false
                btnCancel.isEnabled = false
                btnShowTime.isEnabled = false
            }

            checkBox.setOnCheckedChangeListener {
                    _, isChecked ->
                etLongMessage.isEnabled = isChecked
            }

            etTitle.addTextChangedListener {
                isReadyToCreateNotification()
            }

            etShortMessage.addTextChangedListener {
                isReadyToCreateNotification()
            }

            etTime.addTextChangedListener {
                isReadyToCreateNotification()
            }

            btnCreate.setOnClickListener {
                val title = etTitle.text.toString()
                val shortText = etShortMessage.text.toString()
                val time = etTime.text.toString().toLong()
                val longText = if (etLongMessage.text.isEmpty() || !checkBox.isChecked) {
                    null
                } else {
                    etLongMessage.text.toString()
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val now = System.currentTimeMillis()
                    setAlarm(title, shortText, longText, now + time*1000)
                }
            }

            btnCancel.setOnClickListener {
                if (lastIntent != null) {
                    val alarmManager = requireActivity().getSystemService(FragmentActivity.ALARM_SERVICE) as AlarmManager
                    alarmManager.cancel(lastIntent)
                }
            }

            btnShowTime.setOnClickListener {
                val sinceUpdateTime = SystemClock.uptimeMillis()
                val minutes = TimeUnit.MILLISECONDS.toMinutes(sinceUpdateTime)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(sinceUpdateTime) - TimeUnit.MINUTES.toSeconds(minutes)
                Toast.makeText(context, "$minutes:$seconds from last reboot",
                    Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun isReadyToCreateNotification() {
        binding?.apply {
            btnCreate.isEnabled =
                !(etTitle.text.isNullOrEmpty() || etShortMessage.text.isNullOrEmpty() || etTime.text.isNullOrEmpty())
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }


    private fun setAlarm(title: String, shortText: String, longText: String?, time: Long) {

        val resultIntent = Intent(requireContext(), MyBroadcastReceiver::class.java).apply {
            putExtra(TITLE, title)
            putExtra(SHORT_TEXT, shortText)
            putExtra(LONG_TEXT, longText)
        }

        val pendingResultIntent =
            PendingIntent.getBroadcast(requireContext(), 0, resultIntent, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            } )

        val alarmManager = requireActivity().getSystemService(FragmentActivity.ALARM_SERVICE) as AlarmManager

        lastIntent = pendingResultIntent

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingResultIntent)


    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
        const val TITLE = "TITLE"
        const val SHORT_TEXT = "SHORT_TEXT"
        const val LONG_TEXT = "LONG_TEXT"
        @JvmStatic
        fun getInstance() = MainFragment()
    }

}
