package com.example.nutritiontrack.ui.meals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.MealFragmentBinding
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex

class meal : Fragment() {

    private lateinit var viewModel: MealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : MealFragmentBinding = DataBindingUtil.inflate(layoutInflater
            ,R.layout.meal_fragment, container, false)

        val args = mealArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        viewModel.assignMeal(args.meaId)
        binding.meal = viewModel.displayMeal


        binding.amountText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (binding.amountText.text?.isNotEmpty() == true){
                    val text  = binding.amountText.text.toString()
                    if(text.toDouble() >= 1.0 && text.isNotEmpty()){
                        viewModel.displayMeal?.amount = text.toDouble()
                        binding.amountValue.text = text
                    }
                }else{
                    viewModel.displayMeal?.amount = 1.0
                    binding.amountValue.text = 1.0.toString()
                }

                //TODO send the text to the firestore to get data according to the text


            }
        })
        return binding.root
    }


}