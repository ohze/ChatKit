package com.stfalcon.chatkit.sample.features.main.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.stfalcon.chatkit.sample.R

/*
 * Created by troy379 on 11.04.17.
 */
class DemoCardFragment : Fragment(), View.OnClickListener {
    private var _id: Int = 0
    private var title: String? = null
    private var description: String? = null
    private var actionListener: OnActionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            this._id = arguments!!.getInt(ARG_ID)
            this.title = arguments!!.getString(ARG_TITLE)
            this.description = arguments!!.getString(ARG_DESCRIPTION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_demo_card, container, false)

        val tvTitle = v.findViewById<View>(R.id.tvTitle) as TextView
        val tvDescription = v.findViewById<View>(R.id.tvDescription) as TextView
        val button = v.findViewById<View>(R.id.button) as Button

        tvTitle.text = title
        tvDescription.text = description
        button.setOnClickListener(this)

        return v
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> onAction()
        }
    }

    private fun onAction() {
        if (actionListener != null) {
            actionListener!!.onAction(this._id)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnActionListener) {
            actionListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnActionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        actionListener = null
    }

    interface OnActionListener {
        fun onAction(id: Int)
    }

    companion object {

        private const val ARG_ID = "id"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(id: Int, title: String, description: String): DemoCardFragment {
            val fragment = DemoCardFragment()
            val args = Bundle()
            args.putInt(ARG_ID, id)
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}