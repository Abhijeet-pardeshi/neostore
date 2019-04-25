package com.neosoft.neostoreapp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast

import com.neosoft.neostoreapp.R

class AddressFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.address, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        hideSearchAction(menu)
    }

    private fun hideSearchAction(menu: Menu?) {
        menu?.findItem(R.id.action_search)?.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_add -> {
                val addAddressFragment = AddAddressFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.container, addAddressFragment)?.addToBackStack(null)
                    ?.commit()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }


}
