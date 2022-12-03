package hu.bme.aut.android.trackio.fragments.currentchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.trackio.R
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.databinding.ChallangeRowitemBinding
import hu.bme.aut.android.trackio.network.InternetConnectivityChecker
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ListAdapter(private val listeners: ChallengeItemClickListener):
    RecyclerView.Adapter<ListAdapter.ChallengeViewHolder>() {

    private var challengeList = mutableListOf<Challenge>()

    inner class ChallengeViewHolder(val binding: ChallangeRowitemBinding) :
        RecyclerView.ViewHolder(binding.root)


    interface ChallengeItemClickListener {
        fun onItemRemoved(item: Challenge)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChallengeViewHolder (
        ChallangeRowitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val currentItem = challengeList[position]
        holder.binding.sporticon.setImageResource(getImageResource(currentItem.sportType))
        holder.binding.typeofsport.text=getSportType(currentItem.sportType)
        holder.binding.rowdistance.text= currentItem.distance.toString().plus(" km")
        holder.binding.rowduration.text= getSportDuration(currentItem.duration)
        holder.binding.rowstartdate.text= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentItem.startDate)
        val leftInmillies =Calendar.getInstance().getTime().time-currentItem.startDate;
        val daysleft = TimeUnit.MILLISECONDS.toDays(leftInmillies).toString()+" days"
        holder.binding.rowdaysleft.text= daysleft
        holder.binding.removebutton.setOnClickListener{
            if(InternetConnectivityChecker.isOnline()){
                deleteData(currentItem)
                listeners.onItemRemoved(currentItem)
            }
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

    private fun getSportDuration(duration : Challenge.SportDuration) : String {
        return when(duration)
        {
            Challenge.SportDuration.DAILY -> "Daily"
            Challenge.SportDuration.WEEKLY -> "Weekly"
        }
    }

    fun setData(challenges: List<Challenge>){
        this.challengeList=challenges.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteData(challenge: Challenge){
        val index = challengeList.indexOf(challenge)
        challengeList.removeAt(index)
        notifyItemRemoved(index)
    }
}