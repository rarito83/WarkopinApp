package com.example.warkopinapp.view

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warkopinapp.adapter.PersonAdapter
import com.example.warkopinapp.databinding.LocalFragmentBinding
import com.example.warkopinapp.model.Person
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

            val itemLocal = Gson().fromJson(activity?.assets?.readAssetsFile("persons.json"), Person::class.java)
            val adapter = PersonAdapter(arrayListOf(itemLocal))

            localBinding.rvPerson.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    private fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}