package cg.tutorials.tutionflow.app_fragmants

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cg.tutorials.tutionflow.BatchData
import cg.tutorials.tutionflow.MainActivity
import cg.tutorials.tutionflow.R
import cg.tutorials.tutionflow.appDatabase.TutionDatabase
import cg.tutorials.tutionflow.databinding.FragmentBatchAddBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BatchAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BatchAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentBatchAddBinding
    lateinit var mainActivity: MainActivity
    lateinit var tutionDatabase:TutionDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity=activity as MainActivity
        tutionDatabase=TutionDatabase.getInstance(mainActivity)
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        // Inflate the layout for this fragment
        binding=FragmentBatchAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDone.setOnClickListener {
            when {
                binding.etBatchName.text.toString().isEmpty() -> {
                    binding.etBatchName.error = "Enter Batch name"
                }

                binding.etClass.text.toString().isEmpty() -> {
                    binding.etClass.error = "Enter Class"
                }

                binding.etStartTime.text.toString().isEmpty() -> {
                    binding.etStartTime.error = "Enter Start Time"
                }

                binding.etEndTime.text.toString().isEmpty() -> {
                    binding.etEndTime.error = "Enter Ending Time"
                }

                else -> {
                    val batchInfo =BatchData(
                        batchName = binding.etBatchName.text.toString(),
                        batchClass = binding.etClass.text.toString(),
                        startTime = binding.etStartTime.text.toString(),
                        endTime = binding.etEndTime.text.toString()
                    )
                    tutionDatabase.batchInterface().addBatch(batchInfo)

                    binding.animationSaved.visibility = View.VISIBLE
//  rem.    this is the way to show the whole saved animation before popBackStack()
                    binding.animationSaved.playAnimation()
                    binding.animationSaved.addAnimatorListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            findNavController().popBackStack()
                        }
                    })
//  else    it will popBackStack before showing the animation
                }

            }
        }
        binding.btnBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BatchAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatchAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}