package com.canaleal.sample2.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.canaleal.sample2.ui.FirstFragmentDirections
import com.canaleal.sample2.R
import com.canaleal.sample2.domain.Pet
import com.canaleal.sample2.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint



class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val entryViewModel: PetViewModel by activityViewModels()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).supportActionBar?.title = "First"
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.viewModel = entryViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.sendButton.setOnClickListener { onSend() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    private fun onSend(){

        val name = binding.petNameInput.text.toString()
        if(name == ""){
            val text = "Name cannot be empty!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
        else{

            //Get the selected pet text
            val petType = when (binding.messageGroup.checkedRadioButtonId) {
                R.id.cat_button -> getString(R.string.cat)
                R.id.dog_button -> getString(R.string.dog)
                else -> getString(R.string.undefined)
            }
            entryViewModel.update(Pet(name, petType))
            val action =
                FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            navController.navigate(action)
        }
    }
}