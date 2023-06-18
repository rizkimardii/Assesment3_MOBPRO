package org.d3if3145.assesment2.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3145.assesment2.R
import org.d3if3145.assesment2.databinding.FragmentHitungEuroBinding
import org.d3if3145.assesment2.db.MoneyDb
import org.d3if3145.assesment2.model.HasilEuro
import org.d3if3145.assesment2.ui.viewmodel.HitungViewModel
import org.d3if3145.assesment2.ui.viewmodel.HitungViewModelFactory


class HitEuroFragment : Fragment() {
    private lateinit var binding: FragmentHitungEuroBinding

    private val viewModel: HitungViewModel by lazy {
        val db = MoneyDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungEuroBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { hitungEuro() }
        binding.shareButtonEuro.setOnClickListener { shareData() }
        viewModel.getHasilEuro().observe(requireActivity(), { showResult(it) })

    }

    private fun hitungEuro() {

        val berat = binding.euroEditText.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungEuro(
            berat.toFloat())


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_histori) {
            findNavController().navigate(
                R.id.action_hitungFragment2_to_historiFragment)
            return true
        }

        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitEuroFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.IdrTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun showResult(result: HasilEuro?) {
        if (result == null) return
        binding.IdrTextView.text = getString(R.string.Idr_x, result.jumlahEuro)
        binding.shareButtonEuro.visibility = View.VISIBLE
    }


}