package com.stfalcon.chatkit.sample.features.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.stfalcon.chatkit.sample.R
import com.stfalcon.chatkit.sample.features.demo.custom.holder.CustomHolderDialogsActivity
import com.stfalcon.chatkit.sample.features.demo.custom.layout.CustomLayoutDialogsActivity
import com.stfalcon.chatkit.sample.features.demo.custom.media.CustomMediaMessagesActivity
import com.stfalcon.chatkit.sample.features.demo.def.DefaultDialogsActivity
import com.stfalcon.chatkit.sample.features.demo.styled.StyledDialogsActivity
import com.stfalcon.chatkit.sample.features.main.adapter.DemoCardFragment
import com.stfalcon.chatkit.sample.features.main.adapter.MainActivityPagerAdapter
import com.stfalcon.chatkit.sample.features.main.adapter.*

import kotlinx.android.synthetic.main.activity_main.*

/*
 * Created by troy379 on 04.04.17.
 */
class MainActivity : AppCompatActivity(), DemoCardFragment.OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = MainActivityPagerAdapter(this, supportFragmentManager)
        pager.pageMargin = resources.getDimension(R.dimen.card_padding).toInt() / 4
        pager.offscreenPageLimit = 3
        indicator.setViewPager(pager)
    }

    override fun onAction(id: Int) {
        when (id) {
            ID_DEFAULT -> DefaultDialogsActivity.open(this)
            ID_STYLED -> StyledDialogsActivity.open(this)
            ID_CUSTOM_LAYOUT -> CustomLayoutDialogsActivity.open(this)
            ID_CUSTOM_VIEW_HOLDER -> CustomHolderDialogsActivity.open(this)
            ID_CUSTOM_CONTENT -> CustomMediaMessagesActivity.open(this)
        }
    }
}
