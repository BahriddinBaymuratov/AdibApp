package com.example.adabiyotapp.fragment

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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.example.adabiyotapp.databinding.FragmentAddAdbBinding
import com.example.adabiyotapp.model.Adib
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddAdbFragment : Fragment() {
    private var _binding: FragmentAddAdbBinding? = null
    private val binding get() = _binding!!
    private val firebaseStorage by lazy { FirebaseStorage.getInstance().getReference("images") }
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
        initViews()

    }

    private fun initViews() {
        binding.btnPhoto.setOnClickListener {
            launcher.launch("image/*")
        }
        autoComplete()

        binding.btnSave.setOnClickListener {
            val fullName = binding.nameUN.text.toString().trim()
            val wasBorn = binding.wasBorn.text.toString().trim()
            val dateDead = binding.dateDead.text.toString().trim()
            val about = binding.aboutAdib.text.toString().trim()
            if (fullName.isNotBlank() && about.isNotBlank() && ::photoUrl.isInitialized && ::autoCompleteList.isInitialized) {
                saveToDatabase(fullName, wasBorn, dateDead, about)
                Toast.makeText(requireContext(), "SuccessFully saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Enter a data!", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun saveToDatabase(fullName: String, wasBorn: String, dateDead: String, about: String) {
        fireStore.collection("${autoCompleteList.subSequence(0, 6)}")
            .add(Adib(fullName, wasBorn, dateDead, autoCompleteList, about, photoUrl.toString()))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Successfully saved", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        it.exception?.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            val random = UUID.randomUUID().toString()
            binding.imageView.setImageURI(uri)
            firebaseStorage.child("image$random").putFile(uri).addOnSuccessListener {
                firebaseStorage.downloadUrl.addOnSuccessListener {
                    photoUrl = it
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}