package cg.tutorials.tutionflow.app_fragmants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cg.tutorials.tutionflow.BatchData
import cg.tutorials.tutionflow.MainActivity
import cg.tutorials.tutionflow.R
import cg.tutorials.tutionflow.RecyclerAdapter.BatchRecyclerAdapter
import cg.tutorials.tutionflow.appDatabase.TutionDatabase
import cg.tutorials.tutionflow.databinding.FragmentBatchesBinding
import cg.tutorials.tutionflow.interfaces.BatchRecyclerInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BatchesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BatchesFragment : Fragment(), BatchRecyclerInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding: FragmentBatchesBinding?=null
    var batchArr = ArrayList<BatchData>()
    lateinit var recyclerAdapter: BatchRecyclerAdapter
    lateinit var tutionDatabase: TutionDatabase
    private lateinit var mainActivity: MainActivity
    private lateinit var linearLayoutManager: LinearLayoutManager
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBatchesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(mainActivity)
        recyclerAdapter = BatchRecyclerAdapter(mainActivity, batchArr,this)
        binding?.recyclerView?.layoutManager = linearLayoutManager
        binding?.recyclerView?.adapter = recyclerAdapter

        binding?.addBatch?.setOnClickListener {
            findNavController().navigate(R.id.action_batchesFragment_to_batchAddFragment)
        }
        getBatches()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BatchesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatchesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun update(position: Int) {
        val bundle = Bundle()
        bundle.putInt("key", batchArr[position].batchId)

    }

    override fun delete(position: Int) {
        AlertDialog.Builder(mainActivity).apply {
            setTitle("DELETE")
            setMessage("Really want to delete the Member information")
            setCancelable(false)
            setPositiveButton("No") { _, _ ->
                setCancelable(true)
            }
            setNegativeButton("Yes") { _, _ ->
                tutionDatabase.batchInterface().deleteBatch(batchArr[position])
                batchArr.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()
            }
            show()
        }
    }
    private fun getBatches() {
        batchArr.clear()
        batchArr.addAll(tutionDatabase.batchInterface().getBatches())
        batchArr.addAll(batchArr)
        recyclerAdapter.notifyDataSetChanged()
    }
}