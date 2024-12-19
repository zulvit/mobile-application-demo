package ru.zulvit.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import ru.zulvit.R
import ru.zulvit.utils.checkIfFileExists
import ru.zulvit.utils.deleteFile
import ru.zulvit.utils.restoreFile

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val fileName = "characters_page"  // Пример имени файла

        val fileInfo: Preference? = findPreference("file_info")
        val deleteFilePreference: Preference? = findPreference("delete_file")
        val restoreFilePreference: Preference? = findPreference("restore_file")

        // Проверка наличия файла
        if (checkIfFileExists(requireContext(), fileName)) {
            fileInfo?.summary = "File exists in Documents"
        } else {
            fileInfo?.summary = "No file present"
        }

        // Удаление файла
        deleteFilePreference?.setOnPreferenceClickListener {
            val deleted = deleteFile(requireContext(), fileName)
            if (deleted) {
                fileInfo?.summary = "No file present"
                Toast.makeText(requireContext(), "File deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "File not found", Toast.LENGTH_SHORT).show()
            }
            true
        }

        // Восстановление файла
        restoreFilePreference?.setOnPreferenceClickListener {
            val restored = restoreFile(requireContext(), fileName)
            if (restored) {
                fileInfo?.summary = "File restored from internal storage"
                Toast.makeText(requireContext(), "File restored", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "No file available to restore", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}
