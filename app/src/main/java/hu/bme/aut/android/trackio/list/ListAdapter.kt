package hu.bme.aut.android.trackio.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.data.Challenge
import hu.bme.aut.android.trackio.databinding.ChallangeRowitemBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

//private val listeners: ChallengeItemClickListener
class ListAdapter():
    RecyclerView.Adapter<ListAdapter.ChallengeViewHolder>() {

    private var challengeList = emptyList<Challenge>()

    inner class ChallengeViewHolder(val binding: ChallangeRowitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    /*
    interface ChallengeItemClickListener {
        fun onItemChanged(item: ChallengeItem)
        fun onItemRemoved(item: ChallengeItem)
    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChallengeViewHolder (
        ChallangeRowitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val currentItem = challengeList[position]
        holder.binding.sporticon.setImageResource(getImageResource(currentItem.sportType))
        holder.binding.typeofsport.text=getSportType(currentItem.sportType)
        holder.binding.rowdistance.text= currentItem.distance.toString().plus(" km")
        holder.binding.rowduration.text= currentItem.duration.toString().plus(" days")
        holder.binding.rowstartdate.text= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentItem.startdate)
        val leftInmillies =Calendar.getInstance().getTime().time-currentItem.startdate;
        val daysleft = TimeUnit.MILLISECONDS.toDays(leftInmillies).toString()+" days"
        holder.binding.rowdaysleft.text= daysleft
        holder.binding.removebutton.setOnClickListener{

        }

    }

    override fun getItemCount(): Int {
        return challengeList.size
    }

    private fun getSportType(category: Challenge.SportType): String {
        return when (category) {
            Challenge.SportType.WALKING ->"Walking"
            Challenge.SportType.RUNNING ->"Running"
            Challenge.SportType.CYCLING ->"Cycling"
        }
    }

    @DrawableRes()
    private fun getImageResource(category: Challenge.SportType): Int {
        return when (category) {
            Challenge.SportType.WALKING -> R.drawable.ic_walking
            Challenge.SportType.RUNNING -> R.drawable.ic_running
            Challenge.SportType.CYCLING -> R.drawable.ic_cycling
        }
    }

    fun setData(challenges: List<Challenge>){
        this.challengeList=challenges
        notifyDataSetChanged()

    }
}