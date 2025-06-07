package com.example.formfgdmvvmprojects.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.formfgdmvvmprojects.R
import com.example.formfgdmvvmprojects.databinding.FragmentInputBinding
import com.example.formfgdmvvmprojects.model.AttendanceModel
import com.example.formfgdmvvmprojects.viewmodel.AttendanceViewModel
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    private val viewModel: AttendanceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        //pengisian spinner dengan item res array
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.kategori_peserta,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spCategory.adapter = adapter
        }

        //Pengambilan value dari field view
        binding.btnSubmit.setOnClickListener {
            val model = AttendanceModel(
                name = binding.edtNama.text.toString(),
                phone = binding.edtTelpon.text.toString(),
                email = binding.edtEmail.text.toString(),
                gender = if(binding.radLakilaki.isChecked) "Laki-laki" else "Perempuan",
                skillset = listOfNotNull(
                    if (binding.chkAlgo.isChecked) "Algoritma" else null,
                    if (binding.chkProblemSolving.isChecked) "Problem Solving" else null,
                    if (binding.chkCrticialthinking.isChecked) "Critical Thinking" else null,
                    if (binding.chkProgramming.isChecked) "Programming" else null,
                    if (binding.chkDesignthinking.isChecked) "Design Thinking" else null
                ).joinToString(),
                kategori = binding.spCategory.selectedItem?.toString() ?: "Tidak Diketahui"
            )

            viewModel.setAttendanceData(model)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OutputFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}