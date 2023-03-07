package com.example.warkopinapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warkopinapp.adapter.PersonAdapter
import com.example.warkopinapp.databinding.LocalFragmentBinding
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class LocalFragment: Fragment() {

    private lateinit var localBinding: LocalFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        localBinding = LocalFragmentBinding.inflate(layoutInflater, container, false)
        return localBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val adapter = PersonAdapter(loadJSONFromAsset())

            localBinding.rvPerson.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = activity?.assets!!.open("persons.json")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, "UTF-8")
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}