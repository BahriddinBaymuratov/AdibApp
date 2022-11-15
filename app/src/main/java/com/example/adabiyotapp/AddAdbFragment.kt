package com.example.adabiyotapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.adabiyotapp.databinding.FragmentAddAdbBinding
import com.example.adabiyotapp.model.Adib
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddAdbFragment : Fragment() {
    private var _binding: FragmentAddAdbBinding? = null
    private val binding get() = _binding!!
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val list = listOf("Mumtoz Adabiyoti", "O'zbek Adabiyoti", "Jahon Adabiyoti")
    private lateinit var autoCompleteList: String
    private lateinit var photoUrl: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAdbBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoComplete()
        binding.btnPhoto.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.nameUN.text.toString().trim()
            val wasBorn = binding.wasBorn.text.toString().trim()
            val dateDead = binding.dateDead.text.toString().trim()
            val about = binding.aboutAdib.text.toString().trim()

            if (name.isNotBlank() && about.isNotBlank() && ::autoCompleteList.isInitialized) {
                saveToDatabase(name, wasBorn, dateDead, about)
                Toast.makeText(requireContext(), "SuccessFully saved", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun saveToDatabase(name: String, wasBorn: String, dateDead: String, about: String) {
        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/$fileName")
        ref.putFile(photoUrl)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    fireStore.collection("adiblar").document().set(
                        Adib(
                            name,
                            wasBorn,
                            dateDead,
                            autoCompleteList,
                            about,
                            it.toString(),
                        )
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            findNavController().popBackStack()
                            Toast.makeText(requireContext(), "Successfully created", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("@@@", task.exception?.message.toString())
                        }
                    }
                }
            }
    }

    private fun autoComplete() {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        binding.autoComplete.setAdapter(adapter)
        binding.autoComplete.setOnItemClickListener { adapterView, view, pos, l ->
            autoCompleteList = list[pos]
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { imageUri ->
            photoUrl = imageUri
            binding.imageView.setImageURI(imageUri)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}