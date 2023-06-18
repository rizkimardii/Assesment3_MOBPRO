package org.d3if3145.assesment2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3145.assesment2.R
import org.d3if3145.assesment2.databinding.FragmentHomeBinding
import org.d3if3145.assesment2.ui.viewmodel.HitungViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HitungViewModel by lazy {
        ViewModelProvider(requireActivity())[HitungViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imageView2.setImageResource(R.drawable.ic_dollar)
        binding.imageView.setImageResource(R.drawable.ic_euro)

        binding.dollarBtn.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_homeFragment_to_hitungFragment2
            )
        }

        binding.euroBtn.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_homeFragment_to_hitEuroFragment
            )
        }

        binding.infoUangBtn.setOnClickListener{
            it.findNavController().navigate(
                R.id.action_homeFragment_to_informasiFragment
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_homeFragment_to_historiFragment)
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}