package com.ckh.viewpagerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckh.viewpagerview.databinding.ActivityMainBinding
import com.ckh.viewpagerview.databinding.ItemViewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.Hold

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //1. 데이터를 로드
        val list = listOf<String>("월","화","수","목","금")
        //2. 어댑터를 생성
        val pagerAdapter = CustomPagerAdapter(list)
        //3. 어댑터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdapter
        //4. 탭 타이틀 목록생성 -> 1의 데이터 list 이용
        //5. 탭 레이아웃 뷰 페이저 연결
        TabLayoutMediator(binding.tabLayout,binding.viewPager) { tab,position ->
            tab.text = list.get(position)
        }.attach()
    }
}

class CustomPagerAdapter(val textList: List<String>) :
    RecyclerView.Adapter<CustomPagerAdapter.Holder>() {

    class Holder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(text: String) {
            binding.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(textList.get(position))
    }

    override fun getItemCount() = textList.size

}