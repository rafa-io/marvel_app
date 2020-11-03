package com.rafaelsoares.marvel.ui.superheroes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rafaelsoares.marvel.R
import com.rafaelsoares.marvel.adapter.SuperHeroesRecyclerViewAdapter
import com.rafaelsoares.marvel.databinding.FragmentSuperheroesBinding
import com.rafaelsoares.marvel.injection.component.superheroes.DaggerSuperHeroesFragmentComponent
import com.rafaelsoares.marvel.injection.module.superheroes.SuperHeroesFragmentModule
import com.rafaelsoares.marvel.model.Superhero
import com.rafaelsoares.marvel.model.data.SuperheroesData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_character_sheet.*
import kotlinx.android.synthetic.main.layout_character_sheet.view.*
import javax.inject.Inject

class SuperHeroesFragment : Fragment(), SuperHeroesContract.View {

    @Inject
    lateinit var presenter:SuperHeroesContract.Presenter<SuperheroesData>

    private lateinit var binding:FragmentSuperheroesBinding

    private var adapter: SuperHeroesRecyclerViewAdapter? = null

    var superheroesData: SuperheroesData? = null
    var superHeroes: MutableList<Superhero> = ArrayList()

    var limit = 20
    var offset = 0
    var maxResults = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependency()
        presenter.attach(this, context!!)
        presenter.subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuperheroesBinding.inflate(layoutInflater)

        adapter = SuperHeroesRecyclerViewAdapter(context!!, this.superHeroes)
        binding.recyclerView.layoutManager = LinearLayoutManager(context!!)
        binding.recyclerView.adapter = adapter

        adapter!!.setObjectTapListener(object : SuperHeroesRecyclerViewAdapter.ObjectTapListener {
            override fun onItemSelection(position: Int) {
                val item = superHeroes[position]
                showDetailModal(item)
            }
        })

        binding.paginationPrevious.setOnClickListener {
            this.offset-= this.limit
            if (this.offset <= 0)
                this.offset = 0

            this.superHeroes.clear()
            adapter!!.notifyDataSetChanged()
            requestSuperheroesData(this.limit,this.offset)
        }

        binding.paginationNext.setOnClickListener {
            val newOffset = this.offset + this.limit
            if (newOffset <= this.maxResults-1)
                this.offset = newOffset

            this.superHeroes.clear()
            adapter!!.notifyDataSetChanged()
            requestSuperheroesData(this.limit, this.offset)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        requestSuperheroesData(this.limit,this.offset)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
        presenter.detach()
    }

    private fun injectDependency() {
        val activityComponent = DaggerSuperHeroesFragmentComponent.builder()
            .superHeroesFragmentModule(SuperHeroesFragmentModule(this))
            .build()
        activityComponent.inject(this)
    }

    override fun requestSuperheroesData(limit: Int, offset: Int) {
        presenter.getSuperheroesData(limit,offset)
    }

    override fun confirmApiResponse(superheroesData: SuperheroesData?) {
        val superheroes = superheroesData!!.dataResult.results
        this.superheroesData = superheroesData
        this.maxResults = superheroesData.dataResult.total.toInt()
        this.superHeroes.clear()
        this.superHeroes.addAll(superheroes)
        adapter!!.notifyDataSetChanged()

        updatePagination()
    }

    @SuppressLint("SetTextI18n")
    fun updatePagination() {
        if(this.offset <= 0)
            binding.paginationPrevious.visibility = View.INVISIBLE
        else
            binding.paginationPrevious.visibility = View.VISIBLE
        binding.paginationDisplayed.text = "${this.offset+1}-${this.offset+this.limit} de ${this.maxResults}"
    }

    override fun progress(isActive: Boolean) {
        if (isActive) {
            binding.loadingView.show()
        } else {
            binding.loadingView.hide()
        }
    }

    override fun show(title: String, message: String) {
        val dialog = MaterialAlertDialogBuilder(context!!)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton("OK") { _, _ -> }
        dialog.show()
    }

    private fun showDetailModal(character: Superhero) {
        val bottomSheetDialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(context!!).inflate(
            R.layout.layout_character_sheet,
            view?.findViewById(R.id.modalContainer)
        )
        bottomSheetView.detailName.text = character.name
        var description = character.description
        if (description == "") description = "sem descrição"
        bottomSheetView.detailDescription.text = description
        val path = character.thumbnail.path+"/landscape_large.jpg"
        Picasso.get().load(path)
            .resize(1392,783)
            .into(bottomSheetView.detailImage)

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}